package Dashboard.Designer.Component;

import Dashboard.Designer.Service.ServiceDesigner;
import LoginRegister.Model.ModelUser;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

public class Earning extends javax.swing.JPanel {

    private final ModelUser user;
    private ServiceDesigner serviceDesigner;

    public Earning() {
        this.user = null;
        initComponents();  // Panggil metode initComponents() untuk menginisialisasi GUI
    }
    
    public Earning(ModelUser user) {
        this.user = user;
        this.serviceDesigner = new ServiceDesigner();
        initComponents();

        setOpaque(false);

        displayEarning();
    }

    private void displayEarning() {
        String username = user.getUserName();

        try {
            // Panggil metode getTotalAmountForCurrentMonth untuk mendapatkan total earning dalam bulan saat ini
            Map<String, Double> totalEarningMap = serviceDesigner.getTotalAmountForCurrentMonth(username);

            // Periksa apakah peta totalEarningMap kosong
            if (totalEarningMap.isEmpty()) {
                lbEarning.setText("$ 0");
                
                // Format nama bulan saat ini menjadi title case
                DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
                String formattedMonthName = LocalDate.now().format(monthFormatter);

                lbMonth.setText("Earned in " + formattedMonthName);
                
                return;
            }

            // Ambil nama bulan pertama (hanya ada satu entri dalam totalEarningMap)
            String currentMonth = totalEarningMap.keySet().iterator().next();
            double totalEarning = totalEarningMap.get(currentMonth);

            // Format total earning dengan simbol mata uang dan satu desimal
            String formattedEarning = String.format("$ %.1f", totalEarning);

            // Setel lbEarning dengan formattedEarning
            lbEarning.setText(formattedEarning);

            // Dapatkan bulan saat ini dalam format "Month YYYY"
            DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM");
            String currentMonthYear = LocalDate.now().format(monthYearFormatter);

            // Setel lbMonth dengan teks "Earned in 'Current Month'"
            lbMonth.setText("Earned in " + currentMonthYear);
        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
            lbEarning.setText("N/A"); // Atasi dengan nilai default jika terjadi kesalahan
            lbMonth.setText("N/A");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        lbMonth = new javax.swing.JLabel();
        lbEarning = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        lbMonth.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbMonth.setForeground(new java.awt.Color(255, 255, 255));
        lbMonth.setText("Earned in Month");

        lbEarning.setForeground(new java.awt.Color(255, 255, 255));
        lbEarning.setText("Earning");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addComponent(lbEarning)
                .addGap(15, 15, 15))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMonth)
                    .addComponent(lbEarning))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbEarning;
    private javax.swing.JLabel lbMonth;
    private Dashboard.Swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
