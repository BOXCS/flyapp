package Dashboard.Designer.Profile.Main;

import LoginRegister.Model.ModelUser;

public class ProfileMain extends javax.swing.JPanel {

    private final ModelUser user;

    public ProfileMain(ModelUser user) {
        this.user = user;
        initComponents();

        setOpaque(false);
        this.setLayout(null);

        setCustomLayout();
    }

    private void setCustomLayout() {
        // Mengatur tata letak komponen menggunakan setBounds
        designerCard1.setBounds(38, 33, 647, designerCard1.getPreferredSize().height); // atur posisi (x, y, width, height)
        portfolioCard1.setBounds(38, designerCard1.getY() + 430, 647, 250); // atur posisi (x, y, width, height)
        ratingCard1.setBounds(750, 33, ratingCard1.getPreferredSize().width, 650); // atur posisi (x, y, width, height)
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        portfolioCard1 = new Dashboard.Designer.Profile.Component.PortfolioCard(user);
        designerCard1 = new Dashboard.Designer.Profile.Component.DesignerCard(user);
        ratingCard1 = new Dashboard.Designer.Profile.Component.RatingCard(user);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(portfolioCard1, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                    .addComponent(designerCard1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                .addComponent(ratingCard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ratingCard1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(designerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(portfolioCard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboard.Designer.Profile.Component.DesignerCard designerCard1;
    private Dashboard.Designer.Profile.Component.PortfolioCard portfolioCard1;
    private Dashboard.Designer.Profile.Component.RatingCard ratingCard1;
    // End of variables declaration//GEN-END:variables
}
