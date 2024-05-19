package Dashboard.User.ViewPortfolio.Component;

import Dashboard.Designer.Component.Imageportfolio;
import Dashboard.Designer.Component.ModernScrollBarUI;
import Dashboard.Designer.Component.VideoPortfolio;
import Dashboard.User.ViewPortfolio.Model.ModelViewPortfolio;
import Dashboard.User.ViewPortfolio.Service.ServiceViewPortfolio;
import LoginRegister.Model.ModelUser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class PortfolioCard extends javax.swing.JPanel {

    private int designerId;
    private ModelUser user;

    public PortfolioCard(ModelUser user) {
        initComponents();
        setOpaque(false);
        this.user = user;
        configureScrollPane();
        displayPortfoliosFromDatabase();
    }

    private void configureScrollPane() {
        jScrollPane1.setOpaque(false);
        jScrollPane1.setBackground(null);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.getViewport().setBackground(null);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        jScrollPane1.getHorizontalScrollBar().setUI(new ModernScrollBarUI());
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    }

    private void displayPortfoliosFromDatabase() {
        ServiceViewPortfolio service = new ServiceViewPortfolio();
        List<ModelViewPortfolio> portfolios = service.fetchDataPortfolioByDesignerId(user.getUserID());

        for (ModelViewPortfolio portfolio : portfolios) {
            PlayPortfolio playPortfolio = new PlayPortfolio(portfolio.toString());

            playPortfolio.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    playPortfolioMedia(portfolio);
                }
            });

            body.add(playPortfolio);
        }

        lbDesigner.setText(user.getUserName());

        body.revalidate();
        body.repaint();
    }

    private void playPortfolioMedia(ModelViewPortfolio portfolio) {
        if (portfolio.getMediaType().equalsIgnoreCase("video")) {
            // Buat instance VideoPortfolio dan tampilkan video
            VideoPortfolio videoPortfolio = new VideoPortfolio(String.valueOf(portfolio.getPortfolioId()));
            videoPortfolio.playVideo(portfolio.getMediaContent());
        } else if (portfolio.getMediaType().equalsIgnoreCase("image")) {
            Imageportfolio displayPreview = new Imageportfolio(String.valueOf(portfolio.getPortfolioId()));
            displayPreview.displayImageFromDatabase();
        } else {
            JOptionPane.showMessageDialog(this, "Media type is not video.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new Dashboard.User.ViewPortfolio.Swing.RoundPanel();
        lbDesigner = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        body = new Dashboard.User.ViewPortfolio.Swing.RoundPanel();

        setBackground(new java.awt.Color(0,0,0,0));
        setOpaque(false);

        bg.setBackground(new java.awt.Color(0, 0, 0, 50));

        lbDesigner.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbDesigner.setForeground(new java.awt.Color(255, 255, 255));
        lbDesigner.setText("designer");

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setBorder(null);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        body.setBackground(new java.awt.Color(0, 0, 0, 0));
        body.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));
        jScrollPane1.setViewportView(body);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(lbDesigner)
                        .addGap(0, 1287, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbDesigner)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboard.User.ViewPortfolio.Swing.RoundPanel bg;
    private Dashboard.User.ViewPortfolio.Swing.RoundPanel body;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDesigner;
    // End of variables declaration//GEN-END:variables
}
