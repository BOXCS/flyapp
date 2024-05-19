package User.Cart.Component;

import User.Cart.Service.ServiceCart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import raven.alerts.MessageAlerts;

public class EditPopup extends javax.swing.JPanel {

    private ServiceCart serviceCart;

    public EditPopup() {
        initComponents();
        this.serviceCart = new ServiceCart();

        // Menambahkan ActionListener untuk tombol OK
        cmdOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDesigner();
            }
        });
    }

    public void setTransaction(String transactionNumber) {
        lbTransaction.setText(transactionNumber);
    }

    public void setProduct(String productName) {
        lbProduct.setText(productName);
    }

    public void setLevel(String level) {
        lbLevel.setText(level);
    }

    public void setAmount(String amount) {
        lbAmount.setText(amount);
    }

    public void setDesignerList(List<String> designerList) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String designer : designerList) {
            model.addElement(designer);
        }
        cbListDesigner.setModel(model);
    }

    // Metode untuk mengupdate desainer
    private void updateDesigner() {
        String selectedDesigner = (String) cbListDesigner.getSelectedItem();
        String transactionNumber = lbTransaction.getText();

        if (selectedDesigner != null && !selectedDesigner.isEmpty()) {
            if (serviceCart.updateDesigner(transactionNumber, selectedDesigner)) {
                // Jika pembaruan berhasil
                MessageAlerts.getInstance().showMessage("Success", "Designer updated successfully.", MessageAlerts.MessageType.SUCCESS);
            } else {
                // Jika pembaruan gagal
                MessageAlerts.getInstance().showMessage("Failed", "Failed to update designer.", MessageAlerts.MessageType.ERROR);
            }
        } else {
            // Jika tidak ada desainer yang dipilih
            MessageAlerts.getInstance().showMessage("Warning", "Please select a designer.", MessageAlerts.MessageType.WARNING);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTransaction = new javax.swing.JLabel();
        roundPanel1 = new User.Cart.Swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        lbProduct = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbLevel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbAmount = new javax.swing.JLabel();
        cmdOK = new Dashboard.Swing.Button();
        cbListDesigner = new javax.swing.JComboBox<>();

        lbTransaction.setText("jLabel2");

        setOpaque(false);

        jLabel1.setText("Product Name :");

        lbProduct.setText("Product");

        jLabel3.setText("Designer :");

        jLabel4.setText("Level :");

        lbLevel.setText("level");

        jLabel6.setText("Amount :");

        lbAmount.setText("amount");

        cmdOK.setBackground(new java.awt.Color(132, 132, 215));
        cmdOK.setForeground(new java.awt.Color(255, 255, 255));
        cmdOK.setText("OK");
        cmdOK.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbProduct))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbListDesigner, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbLevel))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbAmount)))
                .addContainerGap(226, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbProduct))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbListDesigner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbLevel))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbAmount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbListDesigner;
    private Dashboard.Swing.Button cmdOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lbAmount;
    private javax.swing.JLabel lbLevel;
    private javax.swing.JLabel lbProduct;
    private javax.swing.JLabel lbTransaction;
    private User.Cart.Swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
