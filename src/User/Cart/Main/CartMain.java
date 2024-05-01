package User.Cart.Main;

import LoginRegister.Model.ModelUser;
import User.Cart.Service.ServiceCart;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import raven.alerts.MessageAlerts;

public class CartMain extends javax.swing.JPanel {

    private final ModelUser user;
    private ServiceCart serviceCart;
    private String transactionSig;

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
            if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 4) {
                calculateTotal(); // Hitung total saat status centang berubah
            }
        });
    }

    // Metode untuk menghitung jumlah total amount dari baris yang dicentang
    private void calculateTotal() {
        DefaultTableModel model = (DefaultTableModel) tableCart.getModel();
        double total = 0.0;
        for (int i = 0; i < model.getRowCount(); i++) {
            boolean isChecked = (boolean) model.getValueAt(i, 4); // Ambil nilai status centang
            if (isChecked) {
                Object amountObj = model.getValueAt(i, 3); // Ambil nilai amount
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
            String transactionType = String.format("%04d", randomNumber);

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

        roundPanel2.setBackground(new java.awt.Color(0, 0, 0, 50));

        tableCart.setBackground(new java.awt.Color(60, 60, 60));
        tableCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "product_name", "level", "designer", "amount", "status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
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

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap(802, Short.MAX_VALUE)
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
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbPrice)
                    .addComponent(cmdCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            boolean isChecked = (boolean) model.getValueAt(i, 4);
            if (isChecked) {
                String productName = (String) model.getValueAt(i, 0);
                if (serviceCart.updateTransactionStatus(productName, transactionSig)) {
                    System.out.println("Product '" + productName + "' checked out successfully.");
                    MessageAlerts.getInstance().showMessage("Success", "Checkout Success", MessageAlerts.MessageType.SUCCESS);
                } else {
                    System.out.println("Failed to check out product '" + productName + "'.");
                    MessageAlerts.getInstance().showMessage("Failed", "Checkout Failed", MessageAlerts.MessageType.ERROR);
                }
            }
        }
        // Refresh tampilan setelah checkout
        serviceCart.fetchCartOrders((DefaultTableModel) tableCart.getModel(), user.getUserName());
        calculateTotal();
    }//GEN-LAST:event_cmdCheckoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private User.Cart.Swing.Button cmdCheckout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbPrice;
    private User.Cart.Swing.RoundPanel roundPanel1;
    private User.Cart.Swing.RoundPanel roundPanel2;
    private User.Cart.Swing.Table tableCart;
    // End of variables declaration//GEN-END:variables
}
