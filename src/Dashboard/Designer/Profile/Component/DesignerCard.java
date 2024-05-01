package Dashboard.Designer.Profile.Component;

import Dashboard.Designer.Model.ModelDesigner;
import Dashboard.Designer.Service.ServiceDesigner;
import LoginRegister.Model.ModelUser;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DesignerCard extends javax.swing.JPanel {

    private final ModelUser user;
    private ServiceDesigner serviceDesigner;

    public DesignerCard() {
        this.user = null;
        initComponents();
        setOpaque(false);
//        loadDesigner();
    }

    public DesignerCard(ModelUser user) {
        this.user = user;
        this.serviceDesigner = new ServiceDesigner();
        initComponents();
        setOpaque(false);
        loadDesigner();
    }

    private void loadDesigner() {
        lbName.setText(user.getUserName());
        lbEmail.setText(user.getEmail());

        try {
            ModelDesigner model = serviceDesigner.getDesignerInfo(user.getUserName());
            if (model != null) {
                lbInstagram.setText("@" + model.getInstagram());
            }

            String memberSince = serviceDesigner.getMemberSince(user.getUserName());
            lbMember.setText(memberSince);

            LocalDateTime lastDelivery = serviceDesigner.getLastDesignDate(user.getUserName());
            if (lastDelivery != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
                String formattedLastDelivery = lastDelivery.format(formatter);
                lbDelivery.setText(formattedLastDelivery);
            }

        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        lbName = new javax.swing.JLabel();
        lbInstagram = new javax.swing.JLabel();
        imageAvatar1 = new Dashboard.Swing.ImageAvatar();
        buttonOutline1 = new swing.ButtonOutline();
        jLabel3 = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        lbMember = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbDelivery = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        lbName.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbName.setText("Name");

        lbInstagram.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbInstagram.setForeground(new java.awt.Color(204, 204, 204));
        lbInstagram.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInstagram.setText("@instagram");

        imageAvatar1.setBorderSize(3);
        imageAvatar1.setBorderSpace(2);
        imageAvatar1.setGradientColor1(new java.awt.Color(63, 109, 217));
        imageAvatar1.setGradientColor2(new java.awt.Color(199, 42, 42));
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/Dashboard/User/icon/profile.png"))); // NOI18N

        buttonOutline1.setForeground(new java.awt.Color(255, 255, 255));
        buttonOutline1.setText("Go To Your Analythics");
        buttonOutline1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Email");

        lbEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbEmail.setForeground(new java.awt.Color(255, 255, 255));
        lbEmail.setText("@gmail.com");

        lbMember.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbMember.setForeground(new java.awt.Color(255, 255, 255));
        lbMember.setText("timestamp");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Member since");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Last Delivery");

        lbDelivery.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbDelivery.setForeground(new java.awt.Color(255, 255, 255));
        lbDelivery.setText("timestamp");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbEmail))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbMember))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbDelivery))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imageAvatar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonOutline1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbInstagram, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbInstagram)
                .addGap(39, 39, 39)
                .addComponent(buttonOutline1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbMember))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbDelivery))
                .addContainerGap(35, Short.MAX_VALUE))
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
    private swing.ButtonOutline buttonOutline1;
    private Dashboard.Swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbDelivery;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbInstagram;
    private javax.swing.JLabel lbMember;
    private javax.swing.JLabel lbName;
    private Dashboard.Swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
