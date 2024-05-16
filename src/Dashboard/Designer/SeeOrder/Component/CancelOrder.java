package Dashboard.Designer.SeeOrder.Component;

import Dashboard.Designer.SeeOrder.Service.ServiceOrderD;
import javax.swing.JOptionPane;

public class CancelOrder extends javax.swing.JFrame {

    private final String transactionNumber;
    private final String userName;

    public CancelOrder() {
        initComponents();
        this.transactionNumber = null;
        this.userName = null;
    }

    public CancelOrder(String transactionNumber, String userName) {
        initComponents();
        this.transactionNumber = transactionNumber;
        this.userName = userName;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        textAreaScroll1 = new Dashboard.Swing.textarea.TextAreaScroll();
        areaReason = new Dashboard.Swing.textarea.TextArea();
        cmdSend = new swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cancel Order");

        textAreaScroll1.setLabelText("Provide Your Reason");

        areaReason.setColumns(20);
        areaReason.setRows(5);
        textAreaScroll1.setViewportView(areaReason);

        cmdSend.setBackground(new java.awt.Color(132, 132, 215));
        cmdSend.setForeground(new java.awt.Color(255, 255, 255));
        cmdSend.setText("SEND");
        cmdSend.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmdSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(textAreaScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdSend, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSendActionPerformed
        String reason = areaReason.getText();
        if (reason.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please provide a reason for cancellation.");
        } else {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel this order?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                ServiceOrderD.cancelOrder(transactionNumber, userName, reason);
                JOptionPane.showMessageDialog(this, "Order successfully cancelled.");
                dispose(); // Close the cancel order window
            }
        }
    }//GEN-LAST:event_cmdSendActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboard.Swing.textarea.TextArea areaReason;
    private swing.Button cmdSend;
    private javax.swing.JLabel jLabel1;
    private Dashboard.Swing.RoundPanel roundPanel1;
    private Dashboard.Swing.textarea.TextAreaScroll textAreaScroll1;
    // End of variables declaration//GEN-END:variables
}
