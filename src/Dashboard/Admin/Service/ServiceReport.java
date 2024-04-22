package Dashboard.Admin.Service;

import Dashboard.Swing.chart.ModelChart;
import connection.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceReport {

    public List<ModelChart> getData() throws SQLException {
        List<ModelChart> list = new ArrayList<>();
        String sql = "SELECT MONTH(created_at) AS Month, "
                + "SUM(CASE WHEN status = 'Finished' THEN 1 ELSE 0 END) AS Finished, "
                + "SUM(CASE WHEN status = 'Canceled' THEN 1 ELSE 0 END) AS Cancelled, "
                + "SUM(CASE WHEN status = 'Pending' THEN 1 ELSE 0 END) AS Pending, "
                + "SUM(CASE WHEN status = 'Active' THEN 1 ELSE 0 END) AS Active, "
                + "SUM(CASE WHEN status = 'Waiting' THEN 1 ELSE 0 END) AS Waiting, "
                + "SUM(CASE WHEN status = 'Late' THEN 1 ELSE 0 END) AS Late "
                + "FROM transaction "
                + "GROUP BY MONTH(created_at) "
                + "ORDER BY MONTH(created_at)";

        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int month = r.getInt("Month");
            int finishedCount = r.getInt("Finished");
            int canceledCount = r.getInt("Cancelled");
            int pendingCount = r.getInt("Pending");
            int activeCount = r.getInt("Active");
            int waitingCount = r.getInt("Waiting");
            int lateCount = r.getInt("Late");
            list.add(new ModelChart(getMonthName(month), new double[]{finishedCount, canceledCount, pendingCount, activeCount, waitingCount, lateCount}));
        }
        r.close();
        p.close();
        return list;
    }

    // Method untuk mengembalikan nama bulan berdasarkan angka bulan
    private String getMonthName(int month) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};
        return monthNames[month - 1];
    }

    public List<ModelChart> getPieChartData(int year, int month) throws SQLException {
        List<ModelChart> list = new ArrayList<>();
        String sql = "SELECT status, COUNT(*) AS Count "
                + "FROM transaction "
                + "WHERE YEAR(created_at) = ? AND MONTH(created_at) = ? "
                + "GROUP BY status";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setInt(1, year);
        p.setInt(2, month);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            String status = r.getString("status");
            int count = r.getInt("Count");
            list.add(new ModelChart(status, new double[]{count}));
        }
        r.close();
        p.close();
        return list;
    }

}
