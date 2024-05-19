package Dashboard.User.ViewPortfolio.Main;

import Dashboard.Swing.ModernScrollBarUI;
import Dashboard.User.ViewPortfolio.Component.PortfolioCard;
import LoginRegister.Model.ModelUser;
import java.awt.Dimension;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class ViewPortMain extends javax.swing.JPanel {

    private int designerId;
    private List<Integer> designerIds;
    private final List<ModelUser> users;

    public ViewPortMain(List<ModelUser> users) {
        initComponents();
        setOpaque(false);
        this.users = users;
        configureScrollPane();
        body3.setLayout(new BoxLayout(body3, BoxLayout.Y_AXIS));
        addPortfolioCardsToBody();
    }

    private void configureScrollPane() {
        jScrollPane1.setOpaque(false);
        jScrollPane1.setBackground(null);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.getViewport().setBackground(null);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        jScrollPane1.getHorizontalScrollBar().setUI(new ModernScrollBarUI());
        jScrollPane1.getVerticalScrollBar().setUI(new ModernScrollBarUI());

        body3.setOpaque(false);
        body3.setBackground(null);
    }

    private void addPortfolioCardsToBody() {
        for (ModelUser user : users) {
            PortfolioCard portfolioCard = new PortfolioCard(user);
            body3.add(Box.createVerticalStrut(20));
            body3.add(portfolioCard);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        body3 = new javax.swing.JPanel();

        setOpaque(false);

        jScrollPane1.setOpaque(false);

        javax.swing.GroupLayout body3Layout = new javax.swing.GroupLayout(body3);
        body3.setLayout(body3Layout);
        body3Layout.setHorizontalGroup(
            body3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1352, Short.MAX_VALUE)
        );
        body3Layout.setVerticalGroup(
            body3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 754, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(body3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
