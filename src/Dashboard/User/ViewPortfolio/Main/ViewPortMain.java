package Dashboard.User.ViewPortfolio.Main;

import Dashboard.User.ViewPortfolio.Component.PortfolioCard;
import java.awt.Dimension;
import java.util.List;

public class ViewPortMain extends javax.swing.JPanel {

    private int designerId;
    private List<Integer> designerIds;

    public ViewPortMain(List<Integer> designerIds) {
        initComponents();
        setOpaque(false);
        this.designerIds = designerIds;
        addPortfolioCardsToBody();
    }

    private void addPortfolioCardsToBody() {
        for (int designerId : designerIds) {
            PortfolioCard portfolioCard = new PortfolioCard(designerId);
            portfolioCard.setPreferredSize(new Dimension(portfolioCard.getPreferredSize().width, portfolioCard.getPreferredSize().height));
            body.add(portfolioCard);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new Dashboard.User.ViewPortfolio.Swing.RoundPanel();

        setOpaque(false);

        body.setBackground(new java.awt.Color(0,0,0,0));
        body.setLayout(new java.awt.GridLayout(4, 0, 0, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 1354, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboard.User.ViewPortfolio.Swing.RoundPanel body;
    // End of variables declaration//GEN-END:variables
}
