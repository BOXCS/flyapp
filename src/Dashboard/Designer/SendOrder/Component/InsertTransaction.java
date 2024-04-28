package Dashboard.Designer.SendOrder.Component;

import Dashboard.Designer.SendOrder.Main.SendOrderMain;
import LoginRegister.Model.ModelUser;

public class InsertTransaction extends javax.swing.JPanel {

    private SendOrderMain sendOrderMain;
    private SendOrderPanel sendOrderPanel;
    private String transactionNumber;

    public InsertTransaction() {
        initComponents();
        setOpaque(false);
    }

    public InsertTransaction(SendOrderMain sendOrderMain, SendOrderPanel sendOrderPanel) {
        this.sendOrderMain = sendOrderMain;
        this.sendOrderPanel = sendOrderPanel;
        initComponents();
        setOpaque(false);

        cmdOK.addActionListener(e -> {
            transactionNumber = txtTransaction.getText().trim();
            if (!transactionNumber.isEmpty()) {
                sendOrderMain.updateReceipt(transactionNumber);
                sendOrderMain.updateDeliver(transactionNumber);
            }
            System.out.println("you insert transactionNumber:" + transactionNumber); // Debug
        });
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTransaction = new swing.TextField();
        cmdOK = new Dashboard.Swing.Button();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Scan barcode into here");

        txtTransaction.setLabelText("Scan Here");

        cmdOK.setBackground(new java.awt.Color(132, 132, 215));
        cmdOK.setForeground(new java.awt.Color(255, 255, 255));
        cmdOK.setText("OK");
        cmdOK.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmdOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(365, 365, 365))
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txtTransaction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTransaction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdOKActionPerformed

    public void setSendOrderPanel(SendOrderPanel sendOrderPanel) {
        this.sendOrderPanel = sendOrderPanel;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboard.Swing.Button cmdOK;
    private javax.swing.JLabel jLabel1;
    private Dashboard.Swing.RoundPanel roundPanel1;
    private swing.TextField txtTransaction;
    // End of variables declaration//GEN-END:variables
}
