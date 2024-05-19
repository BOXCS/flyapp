package Dashboard.Admin.form;

import Dashboard.Admin.AddMessage.Main.AddMessageMain;
import Dashboard.Admin.Model.ModelDesigner;
import Dashboard.Admin.Model.ModelName;
import Dashboard.Admin.RatingView.Main.MainRatingView;
import Dashboard.Admin.Service.ServiceDesigner;
import Dashboard.Admin.Service.ServiceReport;
import Dashboard.Swing.chart.ModelChart;
import Dashboard.Swing.pieChart.ModelPieChart;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;

public class FormHome extends javax.swing.JPanel {

    private MainRatingView mainRatingView1;

    public FormHome() {
        initComponents();
        setOpaque(false);
        this.setLayout(null);
        init();
    }

    private void init() {
        mainRatingView1 = new MainRatingView();

        chart.addLegend("Finished", new Color(5, 125, 0), new Color(95, 209, 69));
        chart.addLegend("Cancelled", new Color(186, 37, 37), new Color(241, 100, 120));
        chart.addLegend("Pending", new Color(12, 84, 175), new Color(0, 108, 247));
        chart.addLegend("Active", new Color(30, 144, 255), new Color(173, 216, 230));
        chart.addLegend("Waiting", new Color(255, 165, 0), new Color(255, 215, 0));
        chart.addLegend("late", new Color(128, 0, 128), new Color(186, 85, 211));

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

        add(mainRatingView1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        customLayout();
    }

    private void customLayout() {
        int panelWidth = getWidth(); // Mendapatkan lebar panel
        int panelHeight = getHeight(); // Mendapatkan tinggi panel

        // Mengatur posisi dan ukuran roundPanel1
        roundPanel1.setBounds(0, 0, panelWidth, panelHeight - 400);

        // Mengatur posisi dan ukuran chart
        chart.setBounds(10, 10, panelWidth - 20, roundPanel1.getHeight() - 20);

        // Mengatur posisi dan ukuran addMessageMain1
        addMessageMain1.setBounds(0, roundPanel1.getY() + 340, panelWidth / 2, 390);

        // Mengatur posisi dan ukuran mainRatingView1
        mainRatingView1.setBounds(panelWidth / 2 + 5, roundPanel1.getHeight() + 10, panelWidth / 2 - 15, 385);
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
        addMessageMain1 = new Dashboard.Admin.AddMessage.Main.AddMessageMain();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));
        roundPanel1.setPreferredSize(new java.awt.Dimension(940, 290));

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1153, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(378, 378, 378)
                .addComponent(addMessageMain1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(addMessageMain1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboard.Admin.AddMessage.Main.AddMessageMain addMessageMain1;
    private Dashboard.Swing.chart.CurveChart chart;
    private Dashboard.Swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
