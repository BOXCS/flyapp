
package Dashboard.User.ViewPortfolio.Main;

import Dashboard.User.ViewPortfolio.Component.PortfolioCard;
import java.awt.Dimension;

public class ViewPortMain extends javax.swing.JPanel {

    public ViewPortMain() {
        initComponents();
        setOpaque(false);
        addPortfolioCardToBody();
    }
    
    private void addPortfolioCardToBody() {
        PortfolioCard portfolioCard = new PortfolioCard();
        portfolioCard.setPreferredSize(new Dimension(portfolioCard.getPreferredSize().width, portfolioCard.getPreferredSize().height));
        body.add(portfolioCard);
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
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
