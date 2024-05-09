package Dashboard.Admin.RatingView.Component;

import Dashboard.Admin.RatingView.Service.ServiceViewRating;
import Dashboard.Designer.Component.VideoPortfolio;
import Dashboard.Designer.Profile.Model.ModelRatingView;
import java.sql.ResultSet;
import java.util.List;

public class Rating extends javax.swing.JPanel {

    private final ServiceViewRating serviceViewRating;
    private List<ModelRatingView> ratingV;

    public Rating(ModelRatingView rating) {
        initComponents();
        serviceViewRating = new ServiceViewRating();
        loadDataFromDatabase(rating);
    }

    private void loadDataFromDatabase(ModelRatingView rating) {
        try {
            ratingV = serviceViewRating.getRatingData();
        } catch (Exception e) {
        }
        
        lbDesigner.setText(rating.getDesignerName());
        lbUser.setText(rating.getUsername());
        lbContent.setText(rating.getProductName());
        areaFeedback.setText(rating.getFeedback());
        lbRatingCount.setText(String.valueOf(rating.getStarCount()));
        ratingCount.setStar(rating.getStarCount());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbDesigner = new javax.swing.JLabel();
        lbContent = new javax.swing.JLabel();
        ratingCount = new Dashboard.Swing.rating.StarRating();
        lbRatingCount = new javax.swing.JLabel();
        textAreaScroll1 = new User.JobApply.Swing.textarea.TextAreaScroll();
        areaFeedback = new User.JobApply.Swing.textarea.TextArea();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(132, 132, 215), 2, true));
        setOpaque(false);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("From :");

        lbUser.setBackground(new java.awt.Color(255, 255, 255));
        lbUser.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lbUser.setForeground(new java.awt.Color(255, 255, 255));
        lbUser.setText("User");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("To :");

        lbDesigner.setBackground(new java.awt.Color(255, 255, 255));
        lbDesigner.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lbDesigner.setForeground(new java.awt.Color(255, 255, 255));
        lbDesigner.setText("Designer");

        lbContent.setBackground(new java.awt.Color(255, 255, 255));
        lbContent.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lbContent.setForeground(new java.awt.Color(255, 255, 255));
        lbContent.setText("Type Content");

        ratingCount.setBackground(new java.awt.Color(0,0,0,0));

        lbRatingCount.setBackground(new java.awt.Color(255, 255, 255));
        lbRatingCount.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lbRatingCount.setForeground(new java.awt.Color(255, 255, 255));
        lbRatingCount.setText("5");

        textAreaScroll1.setLabelText("Feedback");

        areaFeedback.setColumns(20);
        areaFeedback.setRows(5);
        textAreaScroll1.setViewportView(areaFeedback);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textAreaScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbUser)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbDesigner))
                            .addComponent(lbContent)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ratingCount, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbRatingCount)))
                        .addGap(61, 253, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbUser)
                    .addComponent(jLabel3)
                    .addComponent(lbDesigner))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbContent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ratingCount, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRatingCount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private User.JobApply.Swing.textarea.TextArea areaFeedback;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbContent;
    private javax.swing.JLabel lbDesigner;
    private javax.swing.JLabel lbRatingCount;
    private javax.swing.JLabel lbUser;
    private Dashboard.Swing.rating.StarRating ratingCount;
    private User.JobApply.Swing.textarea.TextAreaScroll textAreaScroll1;
    // End of variables declaration//GEN-END:variables
}
