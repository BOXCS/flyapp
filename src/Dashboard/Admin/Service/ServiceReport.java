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
                + "SUM(CASE WHEN status = 'Canceled' THEN 1 ELSE 0 END) AS Canceled, "
                + "SUM(CASE WHEN status = 'Pending' THEN 1 ELSE 0 END) AS Pending "
                + "FROM transaction "
                + "GROUP BY MONTH(created_at) "
                + "ORDER BY MONTH(created_at)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int month = r.getInt("Month");
            int finishedCount = r.getInt("Finished");
            int canceledCount = r.getInt("Canceled");
            int pendingCount = r.getInt("Pending");
            list.add(new ModelChart(getMonthName(month), new double[]{finishedCount, canceledCount, pendingCount}));
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
}
