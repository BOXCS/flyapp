/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin.analytic;

import Admin.analytic.service.service;
import Dashboard.Designer.Analythics.Model.modelChart;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public class AnalyticAdminmain extends javax.swing.JPanel {

    private service service;

    public AnalyticAdminmain() {
        initComponents();
        service = new service();
        init();
        setOpaque(false);
        earnTodate();
        mostSellingLevel();
        orderComplete();
        displayCompletion();

        displayEarnInMonth();
    }
    private void init() {
        chart1.addLegend("Sales", new Color(12, 84, 175), new Color(0, 108, 247));
        chart1.addLegend("Cancelled", new Color(54, 4, 143), new Color(104, 49, 200));
        chart1.addLegend("Completed", new Color(5, 125, 0), new Color(95, 209, 69));
        updateChart();
        chart1.start();
    }

    private void earnTodate() {
        try {
            double totalEarning = service.getTotalAmount();

            String formattedEarning = String.format("$ %.1f", totalEarning);

            total.setText(formattedEarning);
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
            total.setText("N/A");
        }
    }

    private void mostSellingLevel() {

        try {
            String mostLevel = service.getMostFrequentLevel();

            lbAvgLevel.setText(mostLevel);
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
            lbAvgLevel.setText("N/A");
        }
    }

    private void orderComplete() {
        try {
            int Complete = service.getFinishedStatus();
            lbCompleted.setText(String.valueOf(Complete));
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
            lbCompleted.setText("N/A");

        }
    }

    private void displayCompletion() {

        try {
            double averageSuccessRate = service.getAverageSuccessRate();

            lbCompletion.setText(String.format("%.2f%%", averageSuccessRate));
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
            total.setText("N/A");
        }
    }

    private void displayEarnInMonth() {

        try {
            // Mendapatkan total penghasilan untuk bulan saat ini dan nama bulan dari serviceDesigner
            Map<String, Double> totalEarningsByMonth = service.getTotalAmountForCurrentMonth();

            // Cek apakah Map berisi data untuk bulan saat ini
            if (!totalEarningsByMonth.isEmpty()) {
                // Ambil total penghasilan dan nama bulan pertama dari Map
                String monthName = totalEarningsByMonth.keySet().iterator().next();
                double totalEarningInMonth = totalEarningsByMonth.get(monthName);

                // Format penghasilan ke dalam format uang
                String formattedEarning = String.format("$ %.1f", totalEarningInMonth);

                // Setel nilai ke label
                lbEarnMonth.setText(formattedEarning);
                lbMonth.setText("Earned in " + monthName);
            } else {
                // Jika tidak ada data, setel label ke "N/A" dan bulan saat ini
                lbEarnMonth.setText("$ 0");

                // Format nama bulan saat ini menjadi title case
                DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
                String formattedMonthName = LocalDate.now().format(monthFormatter);

                lbMonth.setText("Earned in " + formattedMonthName);
            }
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
            System.err.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
            e.printStackTrace();
            lbEarnMonth.setText("N/A");
            lbMonth.setText("N/A");
        }
    }
    private void updateChart() {
        

        try {
            // Mendapatkan data total amount per bulan saat ini
            Map<String, Double> totalAmountByMonth = service.getTotalAmountByMonth();

            // Mendapatkan jumlah transaksi dengan status "Cancelled"
            Map<String, Integer> cancelledCountByMonth = service.getCountOfCancelledStatus();

            // Mendapatkan jumlah transaksi dengan status "Finished"
            Map<String, Integer> completedCountByMonth = service.getCountOfFinishedStatusByMonth();

            // Iterasi melalui total amount per bulan
            for (Map.Entry<String, Double> entry : totalAmountByMonth.entrySet()) {
                // Ambil nama bulan dan total amount
                String monthName = entry.getKey();
                double salesAmount = entry.getValue();

                // Debugging: periksa nama bulan dan cancelledCount
                System.out.println("Month: " + monthName + ", Cancelled Count: " + cancelledCountByMonth.getOrDefault(monthName, 0));

                int cancelledCount = cancelledCountByMonth.getOrDefault(monthName, 0);
                int completedCount = completedCountByMonth.getOrDefault(monthName, 0);

                // Tambahkan data ke dalam chart untuk bulan yang sesuai
                chart1.addData(new modelChart(monthName, new double[]{
                    salesAmount, // Data untuk legend "Sales"
                    (double) cancelledCount, // Data untuk legend "Cancelled"
                    (double) completedCount // Data untuk legend "Completed"
                }));
            }

            // Mulai chart jika diperlukan
            chart1.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is alwaysk
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbAvgLevel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbCompletion = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbCompleted = new javax.swing.JLabel();
        lbMonth = new javax.swing.JLabel();
        lbEarnMonth = new javax.swing.JLabel();
        chart1 = new Dashboard.Designer.Analythics.Chart.Chart();

        setPreferredSize(new java.awt.Dimension(1366, 768));

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Analytics");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Earnings to date");

        total.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total.setText("$ Amount");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Avg. selling level");

        lbAvgLevel.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbAvgLevel.setForeground(new java.awt.Color(255, 255, 255));
        lbAvgLevel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAvgLevel.setText("most level");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Order completion");

        lbCompletion.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbCompletion.setForeground(new java.awt.Color(255, 255, 255));
        lbCompletion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCompletion.setText("% Completion");

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Orders completed");

        lbCompleted.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbCompleted.setForeground(new java.awt.Color(255, 255, 255));
        lbCompleted.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCompleted.setText("Completed");

        lbMonth.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbMonth.setForeground(new java.awt.Color(255, 255, 255));
        lbMonth.setText("Earned in Month");

        lbEarnMonth.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbEarnMonth.setForeground(new java.awt.Color(255, 255, 255));
        lbEarnMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEarnMonth.setText("$ Amount");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addContainerGap(1233, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(chart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(total)))
                        .addGap(68, 68, 68)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbAvgLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(104, 104, 104)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCompletion, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCompleted, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(96, 96, 96)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEarnMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(236, 236, 236))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbAvgLevel))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbCompletion))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbCompleted))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(lbMonth)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbEarnMonth))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(total)))
                .addGap(87, 87, 87)
                .addComponent(chart1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
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
    private Dashboard.Designer.Analythics.Chart.Chart chart1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lbAvgLevel;
    private javax.swing.JLabel lbCompleted;
    private javax.swing.JLabel lbCompletion;
    private javax.swing.JLabel lbEarnMonth;
    private javax.swing.JLabel lbMonth;
    private Dashboard.Swing.RoundPanel roundPanel1;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
