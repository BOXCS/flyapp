package Dashboard.Designer.Component;

import Dashboard.Admin.AddMessage.Model.ModelMessage;
import Dashboard.Admin.AddMessage.Service.ServiceMessage;
import LoginRegister.Model.ModelUser;

public class MessageCardDesigner extends javax.swing.JPanel {

    private final ModelMessage message;
    private ServiceMessage serviceMessage;

    public MessageCardDesigner() {
        initComponents();
        this.message = null;
    }

    public MessageCardDesigner(ModelMessage message) {
        initComponents();
        this.message = message;

        lbTitle.setText(message.getTitle());
        lbDesc.setText(message.getDescription());
    }

    public ModelMessage getMessage() {
        return message;
    }

    public String getPriority() {
        if (message != null) {
            return message.getMessageStatus();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        lbTitle = new javax.swing.JLabel();
        lbDesc = new javax.swing.JLabel();

        setOpaque(false);

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        lbTitle.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle.setText("Title");

        lbDesc.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbDesc.setForeground(new java.awt.Color(204, 204, 204));
        lbDesc.setText("Description");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbDesc))
                    .addComponent(lbTitle))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDesc)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbDesc;
    private javax.swing.JLabel lbTitle;
    private Dashboard.Swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
