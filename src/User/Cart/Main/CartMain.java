package User.Cart.Main;

import LoginRegister.Model.ModelUser;
import User.Cart.Component.EditMain;
import User.Cart.Component.EditPopup;
import User.Cart.Service.ServiceCart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import raven.alerts.MessageAlerts;

public class CartMain extends javax.swing.JPanel {

    private final ModelUser user;
    private ServiceCart serviceCart;
    private String transactionSig;
    private EditPopup editPopup;
    private EditMain editMain;

    public CartMain(ModelUser user) {
        this.user = user;
        this.serviceCart = new ServiceCart();
        initComponents();

        transactionSig = generateTransactionSig();

        setOpaque(false);

        tableCart.addTableStyle(jScrollPane1);

        serviceCart.fetchCartOrders((DefaultTableModel) tableCart.getModel(), user.getUserName());

        calculateTotal();

        tableCart.getModel().addTableModelListener((TableModelEvent e) -> {
            if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 5) {
                calculateTotal(); // Hitung total saat status centang berubah
            }
        });

        cbSelect.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    // Jika cbSelect dipilih (checked), tandai semua item di tableCart
                    for (int i = 0; i < tableCart.getRowCount(); i++) {
                        tableCart.setValueAt(true, i, 5); // Kolom 5 adalah kolom status (checkbox)
                    }
                } else if (evt.getStateChange() == java.awt.event.ItemEvent.DESELECTED) {
                    // Jika cbSelect tidak dipilih (unchecked), hapus tanda pilihan dari semua item di tableCart
                    for (int i = 0; i < tableCart.getRowCount(); i++) {
                        tableCart.setValueAt(false, i, 5); // Kolom 5 adalah kolom status (checkbox)
                    }
                }
            }
        });

        cmdEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Mendapatkan daftar baris yang dipilih berdasarkan status yang tercentang
                DefaultTableModel model = (DefaultTableModel) tableCart.getModel();
                List<Integer> selectedRows = new ArrayList<>();
                for (int i = 0; i < model.getRowCount(); i++) {
                    boolean isChecked = (boolean) model.getValueAt(i, 5); // Ambil nilai status centang
                    if (isChecked) {
                        selectedRows.add(i);
                    }
                }

                // Jika lebih dari satu baris dipilih, tampilkan pesan peringatan
                if (selectedRows.size() > 1) {
                    MessageAlerts.getInstance().showMessage("Warning", "Multiple items selected.", MessageAlerts.MessageType.WARNING);
                } else if (selectedRows.size() == 1) { // Jika hanya satu baris dipilih
                    int selectedRow = selectedRows.get(0); // Ambil baris yang dipilih

                    // Membuat EditMain jika belum ada yang dibuat sebelumnya
                    if (editMain == null) {
                        editMain = new EditMain();
                    }

                    // Menampilkan EditMain untuk item yang dipilih
                    String transactionNumber = (String) tableCart.getValueAt(selectedRow, 0);
                    String productName = (String) tableCart.getValueAt(selectedRow, 1);
                    String level = (String) tableCart.getValueAt(selectedRow, 2);
                    String amount = (String) tableCart.getValueAt(selectedRow, 4);
                    editMain.showEditPopup(transactionNumber, productName, level, amount);

                    // Menampilkan EditMain
                    editMain.setVisible(true);
                } else { // Jika tidak ada baris yang dipilih
                    MessageAlerts.getInstance().showMessage("Warning", "Please Select Item.", MessageAlerts.MessageType.WARNING);
                }
            }
        });

        editPopup = new EditPopup();
        editMain = new EditMain();

    }

    // Metode untuk menghitung jumlah total amount dari baris yang dicentang
    private void calculateTotal() {
        DefaultTableModel model = (DefaultTableModel) tableCart.getModel();
        double total = 0.0;
        for (int i = 0; i < model.getRowCount(); i++) {
            boolean isChecked = (boolean) model.getValueAt(i, 5); // Ambil nilai status centang
            if (isChecked) {
                Object amountObj = model.getValueAt(i, 4); // Ambil nilai amount
                if (amountObj instanceof String amountStr) {
                    // Menghapus simbol "$" dari nilai amount
                    String amountValueStr = amountStr.replace("$", "");
                    try {
                        double amount = Double.parseDouble(amountValueStr);
                        total += amount;
                    } catch (NumberFormatException ex) {
                        // Handle jika nilai tidak dapat di-parse sebagai double
                        System.err.println("Non-numeric value found in amount column at row " + i);
                    }
                } else {
                    // Handle jika nilai tidak berupa String
                    System.err.println("Non-string value found in amount column at row " + i);
                }
            }
        }
        // Format jumlah total sebagai mata uang dengan dua desimal
        String totalPrice = String.format("$%.2f", total);
        lbPrice.setText(totalPrice); // Update nilai lbPrice
    }

    private String generateTransactionSig() {
        if (transactionSig == null) {
            String prefix = "CRT-";
            int randomNumber = (int) (Math.random() * 10000); // Menghasilkan nomor acak antara 0 dan 9999

            // Menambahkan angka nol di depan jika nomor acaknya memiliki tiga digit atau kurang
            String transactionType = String.format("%05d", randomNumber);

            return prefix + transactionType;
        } else {
            return transactionSig;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel2 = new User.Cart.Swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCart = new User.Cart.Swing.Table();
        roundPanel1 = new User.Cart.Swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        cmdCheckout = new User.Cart.Swing.Button();
        cbSelect = new javax.swing.JCheckBox();
        cmdDelete = new User.SeeOrder.Swing.ButtonDash();
        cmdEdit = new User.SeeOrder.Swing.ButtonDash();

        roundPanel2.setBackground(new java.awt.Color(0, 0, 0, 50));

        tableCart.setBackground(new java.awt.Color(60, 60, 60));
        tableCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "transaction", "product_name", "level", "designer", "amount", "status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableCart.getColumnModel().getColumn(0).setMinWidth(0); // Atur lebar kolom menjadi 0 piksel
        tableCart.getColumnModel().getColumn(0).setMaxWidth(0);
        tableCart.getColumnModel().getColumn(0).setWidth(0);
        jScrollPane1.setViewportView(tableCart);

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Total Price :");

        lbPrice.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(255, 255, 255));
        lbPrice.setText("Price");

        cmdCheckout.setBackground(new java.awt.Color(120, 124, 214));
        cmdCheckout.setForeground(new java.awt.Color(255, 255, 255));
        cmdCheckout.setText("Checkout");
        cmdCheckout.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmdCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCheckoutActionPerformed(evt);
            }
        });

        cbSelect.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbSelect.setForeground(new java.awt.Color(255, 255, 255));
        cbSelect.setText("Select All");

        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard/icon/delete.png"))); // NOI18N
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });

        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard/icon/edit.png"))); // NOI18N
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(cbSelect)
                .addGap(18, 18, 18)
                .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 583, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lbPrice)
                .addGap(37, 37, 37)
                .addComponent(cmdCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lbPrice)
                        .addComponent(cmdCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbSelect)))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(168, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCheckoutActionPerformed
        ServiceCart serviceCart = new ServiceCart();
        DefaultTableModel model = (DefaultTableModel) tableCart.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            boolean isChecked = (boolean) model.getValueAt(i, 5);
            if (isChecked) {
                String productName = (String) model.getValueAt(i, 1);
                String designer = (String) model.getValueAt(i, 3);

                // Cek apakah desainer tersedia
                if (serviceCart.isDesignerAvailable(designer)) {
                    if (serviceCart.updateTransactionStatus(productName, transactionSig)) {
                        System.out.println("Product '" + productName + "' checked out successfully.");
                        MessageAlerts.getInstance().showMessage("Success", "Checkout Success", MessageAlerts.MessageType.SUCCESS);
                    } else {
                        System.out.println("Failed to check out product '" + productName + "'.");
                        MessageAlerts.getInstance().showMessage("Failed", "Checkout Failed", MessageAlerts.MessageType.ERROR);
                    }
                } else {
                    // Desainer tidak tersedia, munculkan popup
                    System.out.println("Designer for product '" + productName + "' is unavailable.");
                    MessageAlerts.getInstance().showMessage("Error", "Designer Unavailable", MessageAlerts.MessageType.ERROR);
                }
            }
        }
        // Refresh tampilan setelah checkout
        serviceCart.fetchCartOrders((DefaultTableModel) tableCart.getModel(), user.getUserName());
        calculateTotal();
    }//GEN-LAST:event_cmdCheckoutActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        int[] selectedRows = tableCart.getSelectedRows(); // Dapatkan baris yang dipilih dalam tabel
        DefaultTableModel model = (DefaultTableModel) tableCart.getModel();

        // Loop melalui setiap baris yang dipilih
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            String productName = (String) model.getValueAt(selectedRows[i], 1); // Dapatkan nama produk dari baris yang dipilih
            String transactionNumber = (String) model.getValueAt(selectedRows[i], 0);
            if (serviceCart.deleteCartItem(transactionNumber)) { // Panggil metode deleteCartItem dengan nomor transaksi
                // Jika penghapusan berhasil, hapus baris dari tabel
                model.removeRow(selectedRows[i]);
                System.out.println("Product '" + productName + "' deleted successfully.");
                MessageAlerts.getInstance().showMessage("Success", "Delete Success", MessageAlerts.MessageType.SUCCESS);
            } else {
                // Jika penghapusan gagal, tampilkan pesan kesalahan
                System.out.println("Failed to delete product '" + productName + "'.");
                MessageAlerts.getInstance().showMessage("Failed", "Delete Failed", MessageAlerts.MessageType.ERROR);
            }
        }
        // Refresh tampilan setelah penghapusan
        calculateTotal();
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdEditActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbSelect;
    private User.Cart.Swing.Button cmdCheckout;
    private User.SeeOrder.Swing.ButtonDash cmdDelete;
    private User.SeeOrder.Swing.ButtonDash cmdEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbPrice;
    private User.Cart.Swing.RoundPanel roundPanel1;
    private User.Cart.Swing.RoundPanel roundPanel2;
    private User.Cart.Swing.Table tableCart;
    // End of variables declaration//GEN-END:variables
}
