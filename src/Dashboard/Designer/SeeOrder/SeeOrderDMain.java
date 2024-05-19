package Dashboard.Designer.SeeOrder;

import Dashboard.Designer.SeeOrder.Component.CancelOrder;
import Dashboard.Designer.SeeOrder.Service.ServiceOrderD;
import Dashboard.Designer.SeeOrder.Swing.CellActive.CellActiveRenderer;
import Dashboard.Designer.SeeOrder.Swing.CellActive.TableActionActiveEvent;
import Dashboard.Designer.SeeOrder.Swing.CellActive.TableActiveCellEditor;
import Dashboard.Designer.SeeOrder.Swing.CellFinished.CellFinishedRenderer;
import Dashboard.Designer.SeeOrder.Swing.CellFinished.TableActionFinishedEvent;
import Dashboard.Designer.SeeOrder.Swing.CellFinished.TableFinishedCellEditor;
import Dashboard.Designer.SeeOrder.Swing.CellPending.TableActionPendingEvent;
import Dashboard.Designer.SeeOrder.Swing.CellPending.TablePendingCellEditor;
import Dashboard.Designer.SeeOrder.Swing.CellPending.TablePendingCellRenderer;
import Dashboard.Designer.SeeOrder.Swing.CellWaiting.CellWaitingRenderer;
import Dashboard.Designer.SeeOrder.Swing.CellWaiting.TableActionWaitingEvent;
import Dashboard.Designer.SeeOrder.Swing.CellWaiting.TableWaitingCellEditor;
import LoginRegister.Component.Message;
import LoginRegister.Model.ModelUser;
import Test.DisplayImage.Main.DisplayImageFromDatabase;
import Test.DisplayImage.Main.DisplayImageFromDatabasePreview;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import raven.alerts.MessageAlerts;
import video.MainVideoJ;
import video.MainVideoJPreview;

public class SeeOrderDMain extends javax.swing.JPanel {

    private final ModelUser user;

    public SeeOrderDMain(ModelUser user) {
        this.user = user;
        initComponents();

        setOpaque(false);

        tableActive.addTableStyle(jScrollPane2);
        tableWaiting.addTableStyle(jScrollPane13);
        tablePending.addTableStyle(jScrollPane14);
        tableFinished.addTableStyle(jScrollPane15);
        tableCancelled.addTableStyle(jScrollPane16);
        tableLate.addTableStyle(jScrollPane17);

        ServiceOrderD.fetchActiveOrdersByDesigner((DefaultTableModel) tableActive.getModel(), user.getUserName());
        ServiceOrderD.fetchWaitingOrdersByDesigner((DefaultTableModel) tableWaiting.getModel(), user.getUserName());
        ServiceOrderD.fetchPendingOrdersByDesigner((DefaultTableModel) tablePending.getModel(), user.getUserName());
        ServiceOrderD.fetchFinishedOrdersByDesigner((DefaultTableModel) tableFinished.getModel(), user.getUserName());
        ServiceOrderD.fetchCancelledOrdersByDesigner((DefaultTableModel) tableCancelled.getModel(), user.getUserName());
        ServiceOrderD.fetchLateOrdersByDesigner((DefaultTableModel) tableLate.getModel(), user.getUserName());

        TableActionActiveEvent eventA = new TableActionActiveEvent() {
            @Override
            public void onReceipt(int row) {
                int selectedRow = tableActive.getSelectedRow();
                if (selectedRow != -1) {
                    Object type = tableActive.getValueAt(selectedRow, 0);
                    ServiceOrderD.printReceipt(type.toString());
                } else {
                    JOptionPane.showMessageDialog(SeeOrderDMain.this, "Please select a row first.");
                }
            }
        };
        tableActive.getColumnModel().getColumn(9).setCellRenderer(new CellActiveRenderer());
        tableActive.getColumnModel().getColumn(9).setCellEditor(new TableActiveCellEditor(eventA));

        TableActionWaitingEvent eventW = new TableActionWaitingEvent() {
            @Override
            public void onApprove(int row) {
                if (row >= 0) {
                    Object transactionNumber = tableWaiting.getValueAt(row, 0);
                    Object userName = tableWaiting.getValueAt(row, 1);
                    MessageAlerts.getInstance().showMessage("Approve Order", "Please check footage first", MessageAlerts.MessageType.DEFAULT, MessageAlerts.YES_NO_OPTION, ((pc, i) -> {
                        if (i == MessageAlerts.YES_OPTION) {
                            ServiceOrderD.approveOrder(transactionNumber.toString(), userName.toString(), user);
                        } else {
                            MessageAlerts.getInstance().showMessage("Approve Order", "Please check everything", MessageAlerts.MessageType.DEFAULT);
                        }
                    }));
                } else {
                    JOptionPane.showMessageDialog(SeeOrderDMain.this, "Please select a row first.");
                }
            }

            @Override
            public void onCancel(int row) {
                if (row >= 0) {
                    Object transactionNumber = tableWaiting.getValueAt(row, 0);
                    Object userName = tableWaiting.getValueAt(row, 1);
                    CancelOrder cancelOrder = new CancelOrder(transactionNumber.toString(), userName.toString());
                    cancelOrder.setVisible(true);
                    DefaultTableModel model = (DefaultTableModel) tableWaiting.getModel();
                    model.removeRow(row);
                } else {
                    JOptionPane.showMessageDialog(SeeOrderDMain.this, "Please select a row first.");
                }
            }
        };
        tableWaiting.getColumnModel().getColumn(8).setCellRenderer(new CellWaitingRenderer());
        tableWaiting.getColumnModel().getColumn(8).setCellEditor(new TableWaitingCellEditor(eventW));

        TableActionPendingEvent eventP = new TableActionPendingEvent() {
            @Override
            public void onPlay(int row) {
                int selectedRow = tablePending.getSelectedRow();
                if (selectedRow != -1) {
                    Object transactionNumber = tablePending.getValueAt(selectedRow, 0);
                    Object product = tablePending.getValueAt(selectedRow, 2); // Mengambil nilai kolom "Product"

                    if (product != null && product.toString().equalsIgnoreCase("Video Editing")) {
                        // Memainkan video dengan MainVideoJ
                        MainVideoJPreview videoPlayer = new MainVideoJPreview(transactionNumber.toString());
                        videoPlayer.playVideoFromDatabase();
                    } else if (product != null && (product.toString().equalsIgnoreCase("Design Graphic") || product.toString().equalsIgnoreCase("3D Modelling"))) {
                        // Menampilkan gambar dengan DisplayImageFromDatabase
                        DisplayImageFromDatabasePreview imageViewerP = new DisplayImageFromDatabasePreview(transactionNumber.toString());
                        imageViewerP.displayCurrentImage();
                    } else {
                        JOptionPane.showMessageDialog(SeeOrderDMain.this, "Unsupported product type.");
                    }
                } else {
                    JOptionPane.showMessageDialog(SeeOrderDMain.this, "Please select a row first.");
                }
            }
        };
        tablePending.getColumnModel().getColumn(8).setCellRenderer(new TablePendingCellRenderer());
        tablePending.getColumnModel().getColumn(8).setCellEditor(new TablePendingCellEditor(eventP));

        TableActionFinishedEvent eventF = new TableActionFinishedEvent() {
            @Override
            public void onPlay(int row) {
                int selectedRow = tableFinished.getSelectedRow();
                if (selectedRow != -1) {
                    Object transactionNumber = tableFinished.getValueAt(selectedRow, 0);
                    Object product = tableFinished.getValueAt(selectedRow, 2); // Mengambil nilai kolom "Product"

                    if (product != null && product.toString().equalsIgnoreCase("Video Editing")) {
                        // Memainkan video dengan MainVideoJ
                        MainVideoJ videoPlayer = new MainVideoJ(transactionNumber.toString());
                        videoPlayer.playVideoFromDatabase();
                    } else if (product != null && (product.toString().equalsIgnoreCase("Design Graphic") || product.toString().equalsIgnoreCase("3D Modelling"))) {
                        // Menampilkan gambar dengan DisplayImageFromDatabase
                        DisplayImageFromDatabase imageViewer = new DisplayImageFromDatabase(transactionNumber.toString());
                        imageViewer.displayImageFromDatabase();
                    } else {
                        JOptionPane.showMessageDialog(SeeOrderDMain.this, "Unsupported product type.");
                    }
                } else {
                    JOptionPane.showMessageDialog(SeeOrderDMain.this, "Please select a row first.");
                }
            }

            @Override
            public void onPortfolio(int row) {
                int selectedRow = tableFinished.getSelectedRow();
                if (selectedRow != -1) {
                    Object transactionNumber = tableFinished.getValueAt(selectedRow, 0);
                    Object product = tableFinished.getValueAt(selectedRow, 2); // Mengambil nilai kolom "Product"

                    if (product != null) {
                        String productType = product.toString();
                        ServiceOrderD.insertIntoPortfolio(transactionNumber.toString(), user.getUserID(), productType);
                    } else {
                        JOptionPane.showMessageDialog(SeeOrderDMain.this, "Product type is null.");
                    }
                } else {
                    JOptionPane.showMessageDialog(SeeOrderDMain.this, "Please select a row first.");
                }
            }
        };
        tableFinished.getColumnModel().getColumn(8).setCellRenderer(new CellFinishedRenderer());
        tableFinished.getColumnModel().getColumn(8).setCellEditor(new TableFinishedCellEditor(eventF));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        materialTabbed1 = new User.SeeOrder.Swing.MaterialTabbed();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableActive = new User.SeeOrder.Swing.Table();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tableWaiting = new User.SeeOrder.Swing.Table();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tablePending = new User.SeeOrder.Swing.Table();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tableFinished = new User.SeeOrder.Swing.Table();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tableCancelled = new User.SeeOrder.Swing.Table();
        jScrollPane11 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tableLate = new User.SeeOrder.Swing.Table();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        materialTabbed1.setForeground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0, 0));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableActive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "type", "No Transaction", "Username", "Product", "level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ));
        tableActive.getColumnModel().getColumn(0).setMinWidth(0); // Atur lebar kolom menjadi 0 piksel
        tableActive.getColumnModel().getColumn(0).setMaxWidth(0);
        tableActive.getColumnModel().getColumn(0).setWidth(0);
        jScrollPane2.setViewportView(tableActive);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        materialTabbed1.addTab("Active", jScrollPane1);

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0, 0));

        jScrollPane13.setBorder(null);
        jScrollPane13.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane13.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableWaiting.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ));
        jScrollPane13.setViewportView(tableWaiting);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel2);

        materialTabbed1.addTab("Waiting", jScrollPane3);

        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0, 0));

        jScrollPane14.setBorder(null);
        jScrollPane14.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane14.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tablePending.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ));
        jScrollPane14.setViewportView(tablePending);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel3);

        materialTabbed1.addTab("Pending", jScrollPane5);

        jScrollPane7.setBorder(null);
        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0, 0));

        jScrollPane15.setBorder(null);
        jScrollPane15.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane15.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableFinished.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ));
        jScrollPane15.setViewportView(tableFinished);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jScrollPane7.setViewportView(jPanel4);

        materialTabbed1.addTab("Finished", jScrollPane7);

        jScrollPane9.setBorder(null);
        jScrollPane9.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane9.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0, 0));

        jScrollPane16.setBorder(null);
        jScrollPane16.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane16.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableCancelled.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ));
        jScrollPane16.setViewportView(tableCancelled);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jScrollPane9.setViewportView(jPanel5);

        materialTabbed1.addTab("Cancelled", jScrollPane9);

        jScrollPane11.setBorder(null);
        jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane11.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0, 0));

        jScrollPane17.setBorder(null);
        jScrollPane17.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane17.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableLate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ));
        jScrollPane17.setViewportView(tableLate);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jScrollPane11.setViewportView(jPanel6);

        materialTabbed1.addTab("Late", jScrollPane11);

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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private User.SeeOrder.Swing.MaterialTabbed materialTabbed1;
    private Dashboard.Swing.RoundPanel roundPanel1;
    private User.SeeOrder.Swing.Table tableActive;
    private User.SeeOrder.Swing.Table tableCancelled;
    private User.SeeOrder.Swing.Table tableFinished;
    private User.SeeOrder.Swing.Table tableLate;
    private User.SeeOrder.Swing.Table tablePending;
    private User.SeeOrder.Swing.Table tableWaiting;
    // End of variables declaration//GEN-END:variables
}
