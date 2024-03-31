package Dashboard.Admin.form;

import Dashboard.Admin.Model.ModelDesigner;
import Dashboard.Admin.Model.ModelName;
import Dashboard.Admin.Service.ServiceDesigner;
import Dashboard.Admin.Service.ServiceReport;
import Dashboard.Swing.chart.ModelChart;
import java.awt.Color;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;

public class FormHome extends javax.swing.JPanel {

    public FormHome() {
        initComponents();
        setOpaque(false);
        table1.addTableStyle(jScrollPane1);
        table1.setAutoCreateRowSorter(true);
        init();
        initDataTable();
    }

    private void init() {
        chart.addLegend("Finished", new Color(5, 125, 0), new Color(95, 209, 69));
        chart.addLegend("Canceled", new Color(186, 37, 37), new Color(241, 100, 120));
        chart.addLegend("Pending", new Color(12, 84, 175), new Color(0, 108, 247));

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
    }

    private void initDataTable() {
        table1.addTableCell(new Dashboard.Admin.Cell.CellUserName(), 0);
        table1.addTableCell(new Dashboard.Admin.Cell.CellInstagram(), 1);
        table1.addTableCell(new Dashboard.Admin.Cell.CellType(), 2);
        table1.addTableCell(new Dashboard.Admin.Cell.CellStatus(), 3);
        table1.addTableCell(new Dashboard.Admin.Cell.CellLastProject(), 4);
        table1.addTableCell(new Dashboard.Admin.Cell.CellAction(), 5);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    for (ModelDesigner staff : new ServiceDesigner().getDesigner()) {
//                        table1.addRow(staff, false);
//                    }
//                } catch (SQLException e) {
//                    System.err.println(e);
//                }
//            }
//        }).start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        chart = new Dashboard.Swing.chart.CurveChart();
        roundPanel2 = new Dashboard.Swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new Dashboard.Swing.Table();
        buttonDash1 = new swing.ButtonDash();
        jLabel1 = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));
        roundPanel1.setPreferredSize(new java.awt.Dimension(940, 290));

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Instagram", "Type Content", "Status", "URL", "Action"
            }
        ));
        jScrollPane1.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(1).setPreferredWidth(50);
            table1.getColumnModel().getColumn(2).setPreferredWidth(20);
            table1.getColumnModel().getColumn(3).setPreferredWidth(20);
            table1.getColumnModel().getColumn(5).setMinWidth(75);
            table1.getColumnModel().getColumn(5).setPreferredWidth(75);
            table1.getColumnModel().getColumn(5).setMaxWidth(75);
        }

        buttonDash1.setForeground(new java.awt.Color(255, 255, 255));
        buttonDash1.setText("+ Add New");
        buttonDash1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        buttonDash1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDash1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("List Designer");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(buttonDash1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 638, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(563, 563, 563))))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonDash1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
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

    private void buttonDash1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDash1ActionPerformed
        // Ambil tanggal hari ini
        Date today = new Date();

        table1.insertRowWithEdit(new ModelDesigner(0, new ModelName("", null, ""), "", "Video Editing", "", today), 0, true);
    }//GEN-LAST:event_buttonDash1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.ButtonDash buttonDash1;
    private Dashboard.Swing.chart.CurveChart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private Dashboard.Swing.RoundPanel roundPanel1;
    private Dashboard.Swing.RoundPanel roundPanel2;
    private Dashboard.Swing.Table table1;
    // End of variables declaration//GEN-END:variables
}
