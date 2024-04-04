package User.SeeOrder.Main;

import LoginRegister.Model.ModelUser;
import Test.DisplayImage.Main.DisplayImageFromDatabase;
import User.SeeOrder.Service.ServiceMyOrder;
import User.SeeOrder.Swing.Cell.CellActionRenderer;
import User.SeeOrder.Swing.Cell.TableActionCellEditor;
import User.SeeOrder.Swing.Cell.TableActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import video.MainVideoJ;

public class SeeOrderMain extends javax.swing.JPanel {

    private MainVideoJ videoPlayer;
    private final ModelUser user;

    public SeeOrderMain(ModelUser user) {
        initComponents();

        this.user = user;

        setOpaque(false);
        tableActive.addTableStyle(jScrollPane7);
        tablePending.addTableStyle(jScrollPane8);
        tableFinished.addTableStyle(jScrollPane9);
        tableCancelled.addTableStyle(jScrollPane10);

        ServiceMyOrder.fetchActiveOrders((DefaultTableModel) tableActive.getModel(), user.getUserName());
        ServiceMyOrder.fetchPendingOrders((DefaultTableModel) tablePending.getModel(), user.getUserName());

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onPlay(int row) {
                int selectedRow = tablePending.getSelectedRow();
                if (selectedRow != -1) {
                    Object transactionNumber = tablePending.getValueAt(selectedRow, 0);
                    Object product = tablePending.getValueAt(selectedRow, 2); // Mengambil nilai kolom "Product"

                    if (product != null && product.toString().equalsIgnoreCase("Video Editing")) {
                        // Memainkan video dengan MainVideoJ
                        MainVideoJ videoPlayer = new MainVideoJ(transactionNumber.toString());
                        videoPlayer.playVideoFromDatabase();
                    } else if (product != null && (product.toString().equalsIgnoreCase("Design Graphic") || product.toString().equalsIgnoreCase("3D Modelling"))) {
                        // Menampilkan gambar dengan DisplayImageFromDatabase
                        DisplayImageFromDatabase imageViewer = new DisplayImageFromDatabase(transactionNumber.toString());
                        imageViewer.displayImageFromDatabase();
                    } else {
                        JOptionPane.showMessageDialog(SeeOrderMain.this, "Unsupported product type.");
                    }
                } else {
                    JOptionPane.showMessageDialog(SeeOrderMain.this, "Please select a row first.");
                }
            }

            @Override
            public void onRevision(int row) {
                int selectedRow = tablePending.getSelectedRow();
                if (selectedRow != -1) {
                    Object transactionNumber = tablePending.getValueAt(selectedRow, 0);
                    System.out.println("Revision on transaction number: " + transactionNumber);
                } else {
                    JOptionPane.showMessageDialog(SeeOrderMain.this, "Please select a row first.");
                }
            }

            @Override
            public void onRate(int row) {
                int selectedRow = tablePending.getSelectedRow();
                if (selectedRow != -1) {
                    Object transactionNumber = tablePending.getValueAt(selectedRow, 0);
                    System.out.println("Rate on transaction number: " + transactionNumber);
                } else {
                    JOptionPane.showMessageDialog(SeeOrderMain.this, "Please select a row first.");
                }
            }
        };
        tablePending.getColumnModel().getColumn(8).setCellRenderer(new CellActionRenderer());
        tablePending.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(event));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new User.SeeOrder.Swing.RoundPanel();
        materialTabbed1 = new User.SeeOrder.Swing.MaterialTabbed();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableActive = new User.SeeOrder.Swing.Table();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablePending = new User.SeeOrder.Swing.Table();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tableFinished = new User.SeeOrder.Swing.Table();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tableCancelled = new User.SeeOrder.Swing.Table();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        materialTabbed1.setForeground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0, 0));

        tableActive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "Level", "Designer", "Due On", "Amount", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tableActive);
        if (tableActive.getColumnModel().getColumnCount() > 0) {
            tableActive.getColumnModel().getColumn(1).setResizable(false);
            tableActive.getColumnModel().getColumn(2).setResizable(false);
            tableActive.getColumnModel().getColumn(3).setResizable(false);
            tableActive.getColumnModel().getColumn(4).setResizable(false);
            tableActive.getColumnModel().getColumn(5).setResizable(false);
            tableActive.getColumnModel().getColumn(6).setResizable(false);
            tableActive.getColumnModel().getColumn(6).setPreferredWidth(10);
            tableActive.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        materialTabbed1.addTab("Active", jScrollPane1);

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0, 0));

        tablePending.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "Level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tablePending);
        if (tablePending.getColumnModel().getColumnCount() > 0) {
            tablePending.getColumnModel().getColumn(0).setResizable(false);
            tablePending.getColumnModel().getColumn(1).setResizable(false);
            tablePending.getColumnModel().getColumn(2).setResizable(false);
            tablePending.getColumnModel().getColumn(3).setResizable(false);
            tablePending.getColumnModel().getColumn(4).setResizable(false);
            tablePending.getColumnModel().getColumn(5).setResizable(false);
            tablePending.getColumnModel().getColumn(6).setResizable(false);
            tablePending.getColumnModel().getColumn(6).setPreferredWidth(10);
            tablePending.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel3);

        materialTabbed1.addTab("Pending", jScrollPane2);

        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0, 0));

        tableFinished.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "Level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tableFinished);
        if (tableFinished.getColumnModel().getColumnCount() > 0) {
            tableFinished.getColumnModel().getColumn(0).setResizable(false);
            tableFinished.getColumnModel().getColumn(1).setResizable(false);
            tableFinished.getColumnModel().getColumn(2).setResizable(false);
            tableFinished.getColumnModel().getColumn(3).setResizable(false);
            tableFinished.getColumnModel().getColumn(4).setResizable(false);
            tableFinished.getColumnModel().getColumn(5).setResizable(false);
            tableFinished.getColumnModel().getColumn(6).setResizable(false);
            tableFinished.getColumnModel().getColumn(6).setPreferredWidth(10);
            tableFinished.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel6);

        materialTabbed1.addTab("Finished", jScrollPane5);

        jScrollPane6.setBorder(null);
        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel7.setBackground(new java.awt.Color(0,0,0,0));

        tableCancelled.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "Level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(tableCancelled);
        if (tableCancelled.getColumnModel().getColumnCount() > 0) {
            tableCancelled.getColumnModel().getColumn(0).setResizable(false);
            tableCancelled.getColumnModel().getColumn(1).setResizable(false);
            tableCancelled.getColumnModel().getColumn(2).setResizable(false);
            tableCancelled.getColumnModel().getColumn(3).setResizable(false);
            tableCancelled.getColumnModel().getColumn(4).setResizable(false);
            tableCancelled.getColumnModel().getColumn(5).setResizable(false);
            tableCancelled.getColumnModel().getColumn(6).setResizable(false);
            tableCancelled.getColumnModel().getColumn(6).setPreferredWidth(10);
            tableCancelled.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(jPanel7);

        materialTabbed1.addTab("Cancelled", jScrollPane6);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private User.SeeOrder.Swing.MaterialTabbed materialTabbed1;
    private User.SeeOrder.Swing.RoundPanel roundPanel1;
    private User.SeeOrder.Swing.Table tableActive;
    private User.SeeOrder.Swing.Table tableCancelled;
    private User.SeeOrder.Swing.Table tableFinished;
    private User.SeeOrder.Swing.Table tablePending;
    // End of variables declaration//GEN-END:variables
}
