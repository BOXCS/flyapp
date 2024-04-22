package Dashboard.Designer.Component;

import Dashboard.Designer.Model.ModelDesigner;
import Dashboard.Designer.Service.ServiceDesigner;
import LoginRegister.Model.ModelUser;

public class Profile extends javax.swing.JPanel {

    private final ModelUser user;
    private final ServiceDesigner serviceDesigner;

    public Profile() {
        this.user = null;
        this.serviceDesigner = null;
        initComponents();
    }

    
    
    public Profile(ModelUser user) {
        this.user = user;
        this.serviceDesigner = new ServiceDesigner();
        initComponents();

        setOpaque(false);

        lbName.setText(user.getUserName());

        try {
            ModelDesigner model = serviceDesigner.getDesignerInfo(user.getUserName());
            if (model != null) {
                lbContent.setText(model.getTypeContent());
                lbMedia.setText(model.getInstagram());
                lbStatus1.setText(model.getStatus());
            }

            double averageRating = serviceDesigner.getAverageRating(user.getUserName());
            String icon = "⭐"; // Anda dapat mengganti ini dengan ikon yang diinginkan
            int averageStar = (int) Math.round(averageRating);
            lbRating.setText(String.valueOf(averageStar));

            double averageSuccessRate = serviceDesigner.getAverageSuccessRate(user.getUserName());
            lbScore.setText(String.format("%.2f%%", averageSuccessRate));

        } catch (Exception e) {
            System.err.println("Error fetching data for Profile: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        lbName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbContent = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbScore = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbRating = new javax.swing.JLabel();
        lbMedia = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        buttonDash1 = new swing.ButtonDash();
        jLabel10 = new javax.swing.JLabel();
        lbStatus1 = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        lbName.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbName.setText("Name");

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Type of Content :");

        lbContent.setForeground(new java.awt.Color(255, 255, 255));
        lbContent.setText("content");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Success Score :");

        lbScore.setForeground(new java.awt.Color(255, 255, 255));
        lbScore.setText("score");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Rating :");

        lbRating.setForeground(new java.awt.Color(255, 255, 255));
        lbRating.setText("rating");

        lbMedia.setForeground(new java.awt.Color(255, 255, 255));
        lbMedia.setText("media");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Instagram :");

        buttonDash1.setForeground(new java.awt.Color(255, 255, 255));
        buttonDash1.setText("Go to your analytics");
        buttonDash1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Status :");

        lbStatus1.setForeground(new java.awt.Color(255, 255, 255));
        lbStatus1.setText("status");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbContent)
                        .addGap(43, 43, 43))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                        .addComponent(lbScore)
                        .addGap(43, 43, 43))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                        .addComponent(lbRating)
                        .addGap(43, 43, 43))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbMedia)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(buttonDash1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbStatus1)
                        .addGap(43, 43, 43))))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbName)
                .addGap(28, 28, 28)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbContent))
                .addGap(28, 28, 28)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbMedia))
                .addGap(28, 28, 28)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lbStatus1))
                .addGap(85, 85, 85)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbScore))
                .addGap(29, 29, 29)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbRating))
                .addGap(36, 36, 36)
                .addComponent(buttonDash1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.ButtonDash buttonDash1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lbContent;
    private javax.swing.JLabel lbMedia;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbRating;
    private javax.swing.JLabel lbScore;
    private javax.swing.JLabel lbStatus1;
    private Dashboard.Swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
