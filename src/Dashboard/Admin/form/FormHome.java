package Dashboard.Admin.form;

import Dashboard.Admin.Model.ModelDesigner;
import Dashboard.Admin.Model.ModelName;
import Dashboard.Admin.Service.ServiceDesigner;
import Dashboard.Admin.Service.ServiceReport;
import Dashboard.Swing.chart.ModelChart;
import Dashboard.Swing.pieChart.ModelPieChart;
import java.awt.Color;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;

public class FormHome extends javax.swing.JPanel {

    public FormHome() {
        initComponents();
        setOpaque(false);
        init();
    }

    private void init() {
        chart.addLegend("Finished", new Color(5, 125, 0), new Color(95, 209, 69));
        chart.addLegend("Cancelled", new Color(186, 37, 37), new Color(241, 100, 120));
        chart.addLegend("Pending", new Color(12, 84, 175), new Color(0, 108, 247));
        chart.addLegend("Active", new Color(30, 144, 255), new Color(173, 216, 230));
        chart.addLegend("Waiting", new Color(255, 165, 0), new Color(255, 215, 0));

        try {
            List<ModelChart> datas = new ServiceReport().getData();

            // Tambahkan data ke dalam chart
            for (ModelChart data : datas) {
                chart.addData(data);
            }

            // Mulai chart
            chart.start();
        } catch (Exception e) {
            System.err.println(e);
        }

        // Inisialisasi item untuk JComboBox cbYear
        for (int year = 2020; year <= 2030; year++) {
            cbYear.addItem(String.valueOf(year));
        }

        // Inisialisasi item untuk JComboBox cbMonth
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (String month : monthNames) {
            cbMonth.addItem(month);
        }

        // Melakukan update pada pie chart untuk menampilkan data berdasarkan tahun dan bulan yang dipilih
        try {
            updatePieChart();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void updatePieChart() throws SQLException {
        // Ambil tahun dan bulan yang dipilih
        int selectedYear = Integer.parseInt((String) cbYear.getSelectedItem());
        int selectedMonth = cbMonth.getSelectedIndex() + 1; // Indeks bulan dimulai dari 0, sementara data bulan dimulai dari 1

        // Ambil data untuk pie chart berdasarkan tahun dan bulan yang dipilih
        List<ModelChart> pieChartData = new ServiceReport().getPieChartData(selectedYear, selectedMonth);

        // Bersihkan data lama dari pie chart
        pieChart1.clearData();

        // Tambahkan data baru ke dalam pie chart
        for (ModelChart data : pieChartData) {
            // Konversi nama label dan nilai dari ModelChart ke ModelPieChart
            String label = data.getLabel();
            double value = data.getValues()[0]; // Ambil nilai pertama saja karena pie chart Anda hanya memiliki satu nilai
            Color color = getColorForLabel(label); // Tentukan warna berdasarkan label
            ModelPieChart pieChartModel = new ModelPieChart(label, value, color);
            pieChart1.addData(pieChartModel);
        }

        // Refresh tampilan pie chart
        pieChart1.repaint(); // Secara langsung memanggil repaint() untuk memperbarui tampilan
    }

    // Method untuk menentukan warna berdasarkan label
    private Color getColorForLabel(String label) {
        switch (label) {
            case "Finished":
                return new Color(5, 125, 0); // Warna hijau untuk Finished
            case "Canceled":
                return new Color(186, 37, 37); // Warna merah untuk Canceled
            case "Pending":
                return new Color(12, 84, 175); // Warna biru untuk Pending
            default:
                return Color.BLACK; // Warna default jika label tidak cocok
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        chart = new Dashboard.Swing.chart.CurveChart();
        roundPanel2 = new Dashboard.Swing.RoundPanel();
        cbMonth = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbYear = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        pieChart1 = new Dashboard.Swing.pieChart.PieChart();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));
        roundPanel1.setPreferredSize(new java.awt.Dimension(940, 290));

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 1358, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel2.setBackground(new java.awt.Color(0, 0, 0, 50));

        jLabel2.setText("Month :");

        jLabel1.setText("Year :");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbYear, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1370, Short.MAX_VALUE)
            .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbMonth;
    private javax.swing.JComboBox<String> cbYear;
    private Dashboard.Swing.chart.CurveChart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private Dashboard.Swing.pieChart.PieChart pieChart1;
    private Dashboard.Swing.RoundPanel roundPanel1;
    private Dashboard.Swing.RoundPanel roundPanel2;
    // End of variables declaration//GEN-END:variables
}
