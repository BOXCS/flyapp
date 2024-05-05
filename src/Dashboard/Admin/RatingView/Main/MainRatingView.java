package Dashboard.Admin.RatingView.Main;

import Dashboard.Admin.RatingView.Component.Rating;
import Dashboard.Admin.RatingView.Service.ServiceViewRating;
import java.util.List;
import Dashboard.Designer.Profile.Model.ModelRatingView;
import Dashboard.Swing.ModernScrollBarUI;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Collections;

public class MainRatingView extends javax.swing.JPanel {

    private ModelRatingView rating;

    public MainRatingView() {
        initComponents();
        setOpaque(false);
        configureScrollPane();
//        addRatingToBody(rating);
        addComboBoxListener();
        fetchRatingData();
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
    }

    private void addComboBoxListener() {
        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFilter = jComboBox1.getSelectedItem().toString();

                // Hapus semua rating yang ada di dalam body
                body.removeAll();

                // Dapatkan ulang semua rating dari service
                List<ModelRatingView> allRatings = getRatingFromService();

                // Tambahkan kembali rating yang sesuai dengan filter yang dipilih
                if (selectedFilter.equals("All")) {
                    for (ModelRatingView rating : allRatings) {
                        addRatingToBody(rating);
                    }
                } else {
                    for (ModelRatingView rating : allRatings) {
                        int starCount = rating.getStarCount();
                        if (starCount == Integer.parseInt(selectedFilter.charAt(0) + "")) {
                            addRatingToBody(rating);
                        }
                    }
                }

                // Revalidate dan repaint body
                body.revalidate();
                body.repaint();
            }
        });

        // Pindahkan setSelectedItem(0) setelah item-item pada combobox ditambahkan
        jComboBox1.setSelectedItem("All");
    }

    private void addRatingToBody(ModelRatingView rating) {
        Rating ratingC = new Rating(rating);

        Dimension fixedSize = new Dimension(527, ratingC.getPreferredSize().height);
        ratingC.setPreferredSize(fixedSize);
        ratingC.setMinimumSize(fixedSize);
        ratingC.setMaximumSize(fixedSize);

        body.add(ratingC);

        body.revalidate();
        body.repaint();
    }

    private List<ModelRatingView> getRatingFromService() {
        try {
            return ServiceViewRating.getRatingData();
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    private void fetchRatingData() {
        try {
            // Fetch rating data from the service
            List<ModelRatingView> ratings = ServiceViewRating.getRatingData();

            // Check if there are any ratings available
            if (!ratings.isEmpty()) {
                // Assign the first rating to the 'rating' object
                rating = ratings.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        setOpaque(false);

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "5 Star", "4 Star", "3 Star", "2 Star", "1 Star" }));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        body.setOpaque(false);
        body.setLayout(new javax.swing.BoxLayout(body, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(body);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(368, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
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
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private Dashboard.Swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
