package Admin.AddAdmin;

import Admin.Swing.SearchBar.SearchOptinEvent;
import Admin.Swing.SearchBar.SearchOption;
import connection.DatabaseConnection;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AddAdminMain extends javax.swing.JPanel {

    public AddAdminMain() {
        initComponents();
        txtSearch.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                txtSearch.setHint("Search by " + option.getName() + "...");
            }
        });
        tableAdmin.addTableStyle(jScrollPane1);
        tableDesigner.addTableStyle(jScrollPane2);
        txtSearch.addOption(new SearchOption("Admin", new ImageIcon(getClass().getResource("/Admin/icon/admin.png"))));
        txtSearch.addOption(new SearchOption("Designer", new ImageIcon(getClass().getResource("/Admin/icon/designer.png"))));
        txtSearch.setSelectedIndex(0);
        loadDataAdmin("");
        loadDataDesigner("");

        // Di dalam constructor AddAdminMain(), tambahkan ListSelectionListener ke tableAdmin
        tableAdmin.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Cek apakah ada baris yang dipilih
                    if (tableAdmin.getSelectedRow() != -1) {
                        boolean enableToAdd = true;
                        DefaultTableModel modelAdmin = (DefaultTableModel) tableAdmin.getModel();
                        // Iterasi melalui baris untuk mengecek nilai boolean di kolom "Action"
                        for (int i = 0; i < modelAdmin.getRowCount(); i++) {
                            if ((boolean) modelAdmin.getValueAt(i, 3)) {
                                // Jika ada satu baris atau lebih dengan nilai true, nonaktifkan tombol
                                enableToAdd = false;
                                break;
                            }
                        }
                        // Set enable tombol berdasarkan hasil pengecekan
                        cmdToAdmin.setEnabled(enableToAdd);
                        // Ubah warna latar belakang dan latar depan tombol cmdToAdmin sesuai kondisi
                        if (enableToAdd) {
                            cmdToAdmin.setBackground(new Color(132, 132, 215)); // Warna aktif
                            cmdToAdmin.setForeground(Color.WHITE); // Warna aktif
                        } else {
                            cmdToAdmin.setBackground(Color.GRAY); // Warna nonaktif
                            cmdToAdmin.setForeground(Color.DARK_GRAY); // Warna nonaktif
                        }
                    } else {
                        // Aktifkan kembali tombol cmdToAdmin jika tidak ada baris yang dipilih
                        cmdToAdmin.setEnabled(true);
                        cmdToAdmin.setBackground(new Color(132, 132, 215)); // Warna awal
                        cmdToAdmin.setForeground(Color.WHITE); // Warna awal
                    }
                }
            }
        });

// Di dalam ActionListener untuk cmdToAdmin
        cmdToAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ambil baris yang dipilih dari tableDesigner
                int[] selectedRows = tableDesigner.getSelectedRows();

                // Periksa apakah ada baris yang dipilih
                if (selectedRows.length > 0) {
                    try {
                        // Persiapkan query untuk menyisipkan data ke tabel admin
                        String insertQuery = "INSERT INTO admin (username, email, password) VALUES (?, ?, ?)";
                        PreparedStatement insertStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(insertQuery);

                        // Iterasi melalui baris yang dipilih
                        for (int i = 0; i < selectedRows.length; i++) {
                            // Ambil nilai username dari baris yang dipilih
                            String password = (String) tableDesigner.getValueAt(selectedRows[i], 0); // Anggap kolom password ada di indeks 2
                            String username = (String) tableDesigner.getValueAt(selectedRows[i], 1); // Anggap kolom username ada di indeks 1
                            // Ambil nilai password dari baris yang dipilih (misalnya dari kolom lain)
                            String email = (String) tableDesigner.getValueAt(selectedRows[i], 2); // Anggap kolom password ada di indeks 2

                            // Sisipkan nilai username dan password ke dalam tabel admin
                            insertStatement.setString(1, username);
                            insertStatement.setString(2, email);
                            insertStatement.setString(3, password);
                            insertStatement.executeUpdate();
                        }

                        // Tutup pernyataan penyisipan
                        insertStatement.close();

                        // Refresh data di tabelAdmin setelah penyisipan
                        loadDataAdmin("");
                        loadDataDesigner("");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Di dalam constructor AddAdminMain()
        cmdDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ambil baris yang dipilih dari tableAdmin
                int[] selectedRows = tableAdmin.getSelectedRows();

                // Periksa apakah ada baris yang dipilih
                if (selectedRows.length > 0) {
                    try {
                        // Persiapkan query untuk menghapus data dari tabel admin
                        String deleteQuery = "DELETE FROM admin WHERE adminID = ?";
                        PreparedStatement deleteStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(deleteQuery);

                        // Iterasi melalui baris yang dipilih
                        for (int i = 0; i < selectedRows.length; i++) {
                            // Ambil nilai boolean dari kolom "Action"
                            boolean actionValue = (boolean) tableAdmin.getValueAt(selectedRows[i], 3); // Anggap kolom "Action" ada di indeks 3

                            // Periksa apakah nilai boolean adalah true
                            if (actionValue) {
                                // Ambil nilai adminID untuk baris yang dipilih
                                String adminID = (String) tableAdmin.getValueAt(selectedRows[i], 0); // Anggap kolom "adminID" ada di indeks 0

                                // Set parameter query
                                deleteStatement.setString(1, adminID);
                                // Eksekusi query untuk menghapus data dari database
                                deleteStatement.executeUpdate();

                                // Hapus baris dari tabel
                                DefaultTableModel modelAdmin = (DefaultTableModel) tableAdmin.getModel();
                                modelAdmin.removeRow(selectedRows[i] - i); // Perlu dikurangi i karena indeks akan bergeser setelah penghapusan
                            }
                        }

                        // Tutup pernyataan penghapusan
                        deleteStatement.close();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }

    private void loadDataAdmin(String where, Object... search) {
        try {
            DefaultTableModel model = (DefaultTableModel) tableAdmin.getModel();
            model.setRowCount(0); // Clear existing data

            String sql = "SELECT adminID, username, email FROM admin";
            if (where != null && !where.isEmpty()) {
                sql += " WHERE " + where;
            }

            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            for (int i = 0; i < search.length; i++) {
                p.setObject(i + 1, search[i]);
            }

            ResultSet r = p.executeQuery();

            while (r.next()) {
                String id = r.getString("adminID");
                String userName = r.getString("username");
                String email = r.getString("email");
                model.addRow(new Object[]{id, userName, email, false});
            }

            r.close();
            p.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataDesigner(String where, Object... search) {
        try {
            DefaultTableModel model = (DefaultTableModel) tableDesigner.getModel();
            model.setRowCount(0);

            String sql = "SELECT designer_id, username, email FROM designer";
            if (where != null && !where.isEmpty()) {
                sql += " WHERE " + where;
            }

            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            for (int i = 0; i < search.length; i++) {
                p.setObject(i + 1, search[i]);
            }

            ResultSet r = p.executeQuery();

            while (r.next()) {
                String id = r.getString("designer_id");
                String userName = r.getString("username");
                String email = r.getString("email");
                model.addRow(new Object[]{id, userName, email, false});
            }

            r.close();
            p.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkSelectedRows() {
        if (tableAdmin.getSelectedRow() == -1) {
            // Tidak ada baris yang dipilih, kembalikan warna tombol cmdToAdmin ke warna semula
            cmdToAdmin.setEnabled(true);
            cmdToAdmin.setBackground(new Color(132, 132, 215)); // Warna awal
            cmdToAdmin.setForeground(Color.WHITE); // Warna awal
        } else {
            // Ada baris yang dipilih, nonaktifkan tombol cmdToAdmin dan ubah warnanya
            cmdToAdmin.setEnabled(false);
            cmdToAdmin.setBackground(Color.GRAY);
            cmdToAdmin.setForeground(Color.DARK_GRAY);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAdmin = new Dashboard.Swing.Table();
        roundPanel2 = new Dashboard.Swing.RoundPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDesigner = new Dashboard.Swing.Table();
        txtSearch = new Admin.Swing.SearchBar.TextFieldSearchOption();
        cmdToAdmin = new Dashboard.Swing.Button();
        cmdDelete = new Dashboard.Swing.Button();

        setOpaque(false);

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        tableAdmin.setBackground(new java.awt.Color(60, 60, 60));
        tableAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "password", "Username", "Email", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableAdmin);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        roundPanel2.setBackground(new java.awt.Color(0, 0, 0, 50));

        tableDesigner.setBackground(new java.awt.Color(60, 60, 60));
        tableDesigner.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "password", "Username", "Email", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableDesigner);

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1342, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        cmdToAdmin.setBackground(new java.awt.Color(132, 132, 215));
        cmdToAdmin.setForeground(new java.awt.Color(255, 255, 255));
        cmdToAdmin.setText("Add To Admin");
        cmdToAdmin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        cmdDelete.setBackground(new java.awt.Color(215, 131, 131));
        cmdDelete.setForeground(new java.awt.Color(255, 255, 255));
        cmdDelete.setText("Delete");
        cmdDelete.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdToAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdToAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (txtSearch.isSelected()) {
            int option = txtSearch.getSelectedIndex();
            String text = "%" + txtSearch.getText().trim() + "%";
            if (option == 0) {
                // Search Admin
                loadDataAdmin("username LIKE ? OR email LIKE ?", text, text);
            } else if (option == 1) {
                // Search Designer
                loadDataDesigner("username LIKE ? OR email LIKE ?", text, text);
            }
        }
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboard.Swing.Button cmdDelete;
    private Dashboard.Swing.Button cmdToAdmin;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private Dashboard.Swing.RoundPanel roundPanel1;
    private Dashboard.Swing.RoundPanel roundPanel2;
    private Dashboard.Swing.Table tableAdmin;
    private Dashboard.Swing.Table tableDesigner;
    private Admin.Swing.SearchBar.TextFieldSearchOption txtSearch;
    // End of variables declaration//GEN-END:variables
}
