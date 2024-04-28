package Dashboard.Designer.SendOrder.Main;

import Dashboard.Designer.SendOrder.Component.InsertTransaction;
import Dashboard.Designer.SendOrder.Component.SendOrderPanel;
import LoginRegister.Model.ModelUser;

public class SendOrderMain extends javax.swing.JPanel {

    private SendOrderPanel sendOrderPanel;
    private ModelUser user;
    private String transactionNumber;

    public SendOrderMain() {
        initComponents();
        setOpaque(false);
        this.setLayout(null);

        setCustomLayout();
    }

    private void setCustomLayout() {
        // set layout
        receipt1.setBounds(750, 100, receipt1.getPreferredSize().width, receipt1.getPreferredSize().height);
        insertTransaction1.setBounds(0, 25, insertTransaction1.getPreferredSize().width, insertTransaction1.getPreferredSize().height);
        sendOrderPanel1.setBounds(0, insertTransaction1.getY() + 150, insertTransaction1.getWidth(), sendOrderPanel1.getPreferredSize().height);
    }

    public void updateReceipt(String transactionNumber) {
        receipt1.loadReceipt(transactionNumber);
    }

    public void updateDeliver(String transactionNumber) {
        sendOrderPanel1.loadTransactionNumber(transactionNumber);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        insertTransaction1 = new Dashboard.Designer.SendOrder.Component.InsertTransaction(this, sendOrderPanel);
        sendOrderPanel1 = new Dashboard.Designer.SendOrder.Component.SendOrderPanel(this);
        receipt1 = new Dashboard.Designer.SendOrder.Component.Receipt(transactionNumber);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(insertTransaction1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sendOrderPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addComponent(receipt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(insertTransaction1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sendOrderPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(receipt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboard.Designer.SendOrder.Component.InsertTransaction insertTransaction1;
    private Dashboard.Designer.SendOrder.Component.Receipt receipt1;
    private Dashboard.Designer.SendOrder.Component.SendOrderPanel sendOrderPanel1;
    // End of variables declaration//GEN-END:variables
}
