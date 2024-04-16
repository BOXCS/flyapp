package Dashboard.Designer.Analythics.Main;

import Dashboard.Designer.Analythics.Model.modelChart;
import Dashboard.Designer.Service.ServiceDesigner;
import LoginRegister.Model.ModelUser;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class AnalythicsMain extends javax.swing.JPanel {

    private final ModelUser user;
    private ServiceDesigner serviceDesigner;

    public AnalythicsMain(ModelUser user) {
        this.user = user;
        this.serviceDesigner = new ServiceDesigner();
        initComponents();

        setOpaque(false);

        init();

        displayEarnToDate();
        displayCompletion();
        displayEarnInMonth();
        displayCompleted();
        displayMostLevel();
    }

    private void init() {
        chart.addLegend("Sales", new Color(12, 84, 175), new Color(0, 108, 247));
        chart.addLegend("Cancelled", new Color(54, 4, 143), new Color(104, 49, 200));
        chart.addLegend("Completed", new Color(5, 125, 0), new Color(95, 209, 69));
        updateChart();
        chart.start();
    }

    private void displayEarnToDate() {
        String username = user.getUserName();

        try {
            double totalEarning = serviceDesigner.getTotalAmount(username);

            String formattedEarning = String.format("$ %.1f", totalEarning);

            lbEarnToDate.setText(formattedEarning);
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
            lbEarnToDate.setText("N/A");
        }
    }

    private void displayCompletion() {
        String username = user.getUserName();

        try {
            double averageSuccessRate = serviceDesigner.getAverageSuccessRate(username);

            lbCompletion.setText(String.format("%.2f%%", averageSuccessRate));
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
            lbEarnToDate.setText("N/A");
        }
    }

    private void displayEarnInMonth() {
        String username = user.getUserName();

        try {
            // Mendapatkan total penghasilan untuk bulan saat ini dan nama bulan dari serviceDesigner
            Map<String, Double> totalEarningsByMonth = serviceDesigner.getTotalAmountForCurrentMonth(username);

            // Ambil total penghasilan dan nama bulan pertama dari Map
            if (!totalEarningsByMonth.isEmpty()) {
                String monthName = totalEarningsByMonth.keySet().iterator().next();
                double totalEarningInMonth = totalEarningsByMonth.get(monthName);

                // Format penghasilan ke dalam format uang
                String formattedEarning = String.format("$ %.1f", totalEarningInMonth);

                // Setel nilai ke label
                lbEarnMonth.setText(formattedEarning);
                lbMonth.setText("Earned in " + monthName);
            } else {
                // Jika tidak ada data, setel label ke "N/A"
                lbEarnMonth.setText("N/A");
                lbMonth.setText("N/A");
            }
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
            lbEarnMonth.setText("N/A");
            lbMonth.setText("N/A");
        }
    }

    private void displayCompleted() {
        String username = user.getUserName();

        try {
            int finishedCount = serviceDesigner.getCountOfFinishedStatus(username);

            lbCompleted.setText(String.valueOf(finishedCount));
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
            lbCompleted.setText("N/A");
        }
    }

    private void displayMostLevel() {
        String username = user.getUserName();

        try {
            String mostLevel = serviceDesigner.getMostFrequentLevel(username);

            lbAvgLevel.setText(mostLevel);
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
            lbAvgLevel.setText("N/A");
        }
    }

    private void updateChart() {
        String username = user.getUserName();

        try {
            // Mendapatkan data total amount per bulan saat ini
            Map<String, Double> totalAmountByMonth = serviceDesigner.getTotalAmountForCurrentMonth(username);

            // Mendapatkan jumlah transaksi dengan status "Cancelled"
            Map<String, Integer> cancelledCountByMonth = serviceDesigner.getCountOfCancelledStatus(username);

            // Mendapatkan jumlah transaksi dengan status "Finished"
            Map<String, Integer> completedCountByMonth = serviceDesigner.getCountOfFinishedStatusByMonth(username);

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
                chart.addData(new modelChart(monthName, new double[]{
                    salesAmount, // Data untuk legend "Sales"
                    (double) cancelledCount, // Data untuk legend "Cancelled"
                    (double) completedCount // Data untuk legend "Completed"
                }));
            }

            // Mulai chart jika diperlukan
            chart.start();
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan saat mengakses database: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbEarnToDate = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbAvgLevel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbCompletion = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbCompleted = new javax.swing.JLabel();
        lbEarnMonth = new javax.swing.JLabel();
        lbMonth = new javax.swing.JLabel();
        chart = new Dashboard.Designer.Analythics.Chart.Chart();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Analytics");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Earnings to date");

        lbEarnToDate.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbEarnToDate.setForeground(new java.awt.Color(255, 255, 255));
        lbEarnToDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEarnToDate.setText("$ Amount");

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

        lbEarnMonth.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbEarnMonth.setForeground(new java.awt.Color(255, 255, 255));
        lbEarnMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEarnMonth.setText("$ Amount");

        lbMonth.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbMonth.setForeground(new java.awt.Color(255, 255, 255));
        lbMonth.setText("Earned in Month");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel1))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbEarnToDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(105, 105, 105)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbAvgLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(104, 104, 104)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbCompletion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(100, 100, 100)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbCompleted, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(96, 96, 96)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbEarnMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 227, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbEarnToDate))
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
                        .addComponent(lbEarnMonth)))
                .addGap(18, 18, 18)
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboard.Designer.Analythics.Chart.Chart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lbAvgLevel;
    private javax.swing.JLabel lbCompleted;
    private javax.swing.JLabel lbCompletion;
    private javax.swing.JLabel lbEarnMonth;
    private javax.swing.JLabel lbEarnToDate;
    private javax.swing.JLabel lbMonth;
    private Dashboard.Swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
