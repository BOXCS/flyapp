package Dashboard.Designer.Profile.Component;

import Dashboard.Designer.Profile.Model.ModelRatingView;
import Dashboard.Designer.Profile.Service.ServiceProfile;
import Dashboard.Swing.VerticalFlowLayoutPanel;
import Dashboard.Swing.scroll.ModernScrollBarUI;
import LoginRegister.Model.ModelUser;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.swing.BoxLayout;

public class RatingCard extends javax.swing.JPanel {

    private final ModelUser user;

    public RatingCard() {
        this.user = null;
        initComponents();
        setOpaque(false);
    }

    public RatingCard(ModelUser user) {
        this.user = user;
        initComponents();
        setOpaque(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.getViewport().setBackground(null);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        jScrollPane1.getVerticalScrollBar().setUI(new ModernScrollBarUI());

        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));

        addRatingPersonToBody();

        // Tambahkan panggilan ke metode ServiceProfile untuk menghitung jumlah dan rata-rata rating
        try {
            int ratingCount = ServiceProfile.getRatingCountByDesigner(user.getUserName());
            double averageStarCount = ServiceProfile.getAverageStarCountByDesigner(user.getUserName());

            // Atur nilai ke lbRating, starRatingAvg, dan lbAvg
            lbRating.setText(ratingCount + " reviews for this seller");

            // Konversi averageStarCount ke bilangan bulat untuk setStar dan lbAvg
            int averageStar = (int) Math.round(averageStarCount);
            starRatingAvg.setStar(averageStar);

            // Tampilkan averageStarCount dalam bentuk string bilangan bulat di lbAvg
            lbAvg.setText(String.valueOf(averageStar));

        } catch (SQLException e) {
            // Tangani kesalahan SQL jika terjadi
            e.printStackTrace();
            // Atur nilai default jika terjadi kesalahan
            lbRating.setText("0 reviews for this seller");
            starRatingAvg.setStar(0);
            lbAvg.setText("0");
        }
    }

    private void addRatingPersonToBody() {

        // Ambil daftar rating dari service
        List<ModelRatingView> ratings = getRatingsFromService(); // Anda perlu menggantikan ini dengan metode yang benar untuk mengambil rating

        // Iterasi melalui daftar rating
        for (ModelRatingView rating : ratings) {
            // Buat objek RatingPerson dengan memberikan user dan rating
            RatingPerson ratingPerson = new RatingPerson(user, rating);

            Dimension fixedSize = new Dimension(350, 130);
            ratingPerson.setPreferredSize(fixedSize);
            ratingPerson.setMinimumSize(fixedSize);
            ratingPerson.setMaximumSize(fixedSize);

            // Tambahkan RatingPerson ke dalam body
            body.add(ratingPerson);
        }

        // Lakukan revalidate dan repaint untuk memperbarui tampilan
        body.revalidate();
        body.repaint();
    }

    private List<ModelRatingView> getRatingsFromService() {
        try {
            return ServiceProfile.getRatingsByDesigner(user.getUserName());
        } catch (SQLException ex) {
            return Collections.emptyList(); // Mengembalikan daftar kosong jika terjadi kesalahan
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        lbRating = new javax.swing.JLabel();
        lbAvg = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();
        starRatingAvg = new Dashboard.Swing.rating.StarRating();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        lbRating.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lbRating.setForeground(new java.awt.Color(255, 255, 255));
        lbRating.setText("99 reviews for this seller");

        lbAvg.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lbAvg.setForeground(new java.awt.Color(255, 255, 255));
        lbAvg.setText("4");

        jScrollPane1.setBorder(null);

        body.setOpaque(false);
        body.setLayout(new javax.swing.BoxLayout(body, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(body);

        starRatingAvg.setBackground(new java.awt.Color(0,0,0,0));

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(lbRating)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(starRatingAvg, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbAvg)
                        .addGap(0, 70, Short.MAX_VALUE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(starRatingAvg, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbAvg)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(lbRating)
                                .addGap(1, 1, 1)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAvg;
    private javax.swing.JLabel lbRating;
    private Dashboard.Swing.RoundPanel roundPanel1;
    private Dashboard.Swing.rating.StarRating starRatingAvg;
    // End of variables declaration//GEN-END:variables
}
