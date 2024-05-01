package Dashboard.Designer.Profile.Component;

import Dashboard.Designer.Component.Imageportfolio;
import Dashboard.Designer.Component.VideoPortfolio;
import Dashboard.Designer.Model.ModelPortfolio;
import Dashboard.Designer.Profile.Service.ServiceProfile;
import Dashboard.Swing.scroll.ModernScrollBarUI;
import LoginRegister.Model.ModelUser;
import Test.DisplayImage.Main.DisplayImageFromDatabasePreview;
import java.awt.Cursor;
import java.awt.Window;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import jnafilechooser.api.JnaFileChooser;

public class PortfolioCard extends javax.swing.JPanel {

    private ServiceProfile serviceProfile;

    public PortfolioCard() {
        initComponents();
        setOpaque(false);
//        configureScrollPane();
        this.user = null;
//        loadPortfolios();
    }
    
    private final ModelUser user;

    public PortfolioCard(ModelUser user) {
        this.user = user;
        initComponents();
        setOpaque(false);
        configureScrollPane();
        loadPortfolios();
    }

    private void configureScrollPane() {
        jScrollPane1.setOpaque(false);
        jScrollPane1.setBackground(null);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.getViewport().setBackground(null);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        jScrollPane1.getHorizontalScrollBar().setUI(new ModernScrollBarUI());

        body.setOpaque(false);
        body.setBackground(null);
        addPortfolio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void loadPortfolios() {

        // Dapatkan designerId
        int designerId = user.getUserID();

        List<ModelPortfolio> portfolios = new ArrayList<>();

        try {
            // Dapatkan daftar portofolio dalam bentuk List<Map<String, Object>>
            List<Map<String, Object>> portfolioDataList = serviceProfile.getPortfoliosByDesignerId(designerId);

            // Konversi List<Map<String, Object>> ke List<ModelPortfolio>
            for (Map<String, Object> portfolioData : portfolioDataList) {
                // Buat instance ModelPortfolio dari data Map<String, Object>
                ModelPortfolio portfolio = new ModelPortfolio();

                // Ekstrak data yang diperlukan dari Map<String, Object> dan set ke ModelPortfolio
                portfolio.setPortfolioId((int) portfolioData.get("portfolio_id"));
                portfolio.setDesignerId((int) portfolioData.get("designer_id"));
                portfolio.setMediaType((String) portfolioData.get("media_type"));
                portfolio.setMediaContent((byte[]) portfolioData.get("media_content"));

                // Tambahkan ModelPortfolio ke daftar portfolios
                portfolios.add(portfolio);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load portfolios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Iterasi melalui daftar portfolios
        for (ModelPortfolio portfolio : portfolios) {
            // Buat instance PlayPortfolio untuk setiap portofolio
            PlayPortfolio playPortfolio = new PlayPortfolio(portfolio.toString());

            // Tambahkan mouseClicked listener untuk playPortfolio
            playPortfolio.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    playPortfolioMedia(portfolio);
                }
            });

            // Tambahkan PlayPortfolio ke panel body
            body.add(playPortfolio);
        }

        // Perbarui tata letak panel body
        body.revalidate();
        body.repaint();
    }

    private void playPortfolioMedia(ModelPortfolio portfolio) {
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

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();
        addPortfolio = new Dashboard.Designer.Profile.Component.AddPortfolio();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("My Portfolio");

        jScrollPane1.setBackground(new java.awt.Color(102, 0, 204));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 0));
        jScrollPane1.setOpaque(false);

        body.setOpaque(false);
        body.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 15, 5));

        addPortfolio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPortfolioMouseClicked(evt);
            }
        });
        body.add(addPortfolio);

        jScrollPane1.setViewportView(body);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 392, Short.MAX_VALUE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addContainerGap())
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

    private void addPortfolioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPortfolioMouseClicked
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.setTitle("Select Portfolio");
        Window window = null;
        boolean action = fileChooser.showOpenDialog(window);

        if (action) {
            File selectedFile = fileChooser.getSelectedFile();

            byte[] mediaContent = null;
            try (FileInputStream fis = new FileInputStream(selectedFile)) {
                mediaContent = fis.readAllBytes();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to read file.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int designerId = user.getUserID();

            String mediaType = null;
            String fileName = selectedFile.getName();
            if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")) {
                mediaType = "image";
            } else if (fileName.endsWith(".mp4") || fileName.endsWith(".avi")) {
                mediaType = "video";
            } else {
                JOptionPane.showMessageDialog(this, "Unsupported media type.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                if (serviceProfile == null) {
                    serviceProfile = new ServiceProfile(); // Pastikan ServiceProfile diinisialisasi
                }

                serviceProfile.insertPortfolio(designerId, mediaType, mediaContent);
                JOptionPane.showMessageDialog(this, "Portfolio added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to add portfolio to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_addPortfolioMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboard.Designer.Profile.Component.AddPortfolio addPortfolio;
    private javax.swing.JPanel body;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private Dashboard.Swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
