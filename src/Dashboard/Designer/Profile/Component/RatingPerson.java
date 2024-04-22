package Dashboard.Designer.Profile.Component;

import Dashboard.Designer.Profile.Model.ModelRatingView;
import Dashboard.Designer.Profile.Service.ServiceProfile;
import LoginRegister.Model.ModelUser;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RatingPerson extends javax.swing.JPanel {

    private ServiceProfile serviceProfile;
    private final ModelUser user;
    private List<ModelRatingView> ratings;

    public RatingPerson(ModelUser user, ModelRatingView rating) {
        this.user = user;
        initComponents();
        setOpaque(false);
        loadRating(rating);
    }

    private void loadRating(ModelRatingView rating) {
        try {
            ratings = serviceProfile.getRatingsByDesigner(user.getUserName());
        } catch (SQLException ex) {
            Logger.getLogger(RatingPerson.class.getName()).log(Level.SEVERE, null, ex);
        }

        lbName.setText(rating.getUsername());
        lbContent.setText(rating.getProductName());
        starRating.setStar(rating.getStarCount());
        lbStar.setText(String.valueOf(rating.getStarCount()));
        lbFeedback.setText(rating.getFeedback());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbName = new javax.swing.JLabel();
        lbContent = new javax.swing.JLabel();
        lbStar = new javax.swing.JLabel();
        lbFeedback = new javax.swing.JLabel();
        starRating = new Dashboard.Swing.rating.StarRating();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(132, 132, 215), 2, true));

        lbName.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setText("Name");

        lbContent.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbContent.setForeground(new java.awt.Color(255, 255, 255));
        lbContent.setText("typeContent");

        lbStar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbStar.setForeground(new java.awt.Color(255, 255, 255));
        lbStar.setText("5");

        lbFeedback.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbFeedback.setForeground(new java.awt.Color(255, 255, 255));
        lbFeedback.setText("Feedback");

        starRating.setBackground(new java.awt.Color(0,0,0,0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbContent, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(starRating, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbStar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lbFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbContent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbStar)
                    .addComponent(starRating, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbFeedback)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbContent;
    private javax.swing.JLabel lbFeedback;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStar;
    private Dashboard.Swing.rating.StarRating starRating;
    // End of variables declaration//GEN-END:variables
}
