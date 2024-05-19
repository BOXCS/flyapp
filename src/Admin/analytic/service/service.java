/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin.analytic.service;

import connection.DatabaseConnection;
import connection.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.text.DateFormatter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 *
 * @author LENOVO
 */
public class service {
    private static Connection connection = null; // Menyimpan koneksi agar dapat digunakan kembali

    // Method untuk mendapatkan koneksi database
    private static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getInstance().getConnection();
        }
        return connection;
    }
    public static double getTotalAmount() throws SQLException{
       Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double totalAmount = 0; 
        
        try {
            // Query to calculate total amount from transaction table
            String query = "SELECT SUM(amount) AS total_amount FROM transaction WHERE status = 'Finished'";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalAmount = rs.getDouble("total_amount");
            }
        }finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
    } 
            return totalAmount;
}
    public static String getMostFrequentLevel() throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String mostFrequentLevel = null;

        try {
            // Query untuk mengelompokkan transaksi berdasarkan level dan menghitung jumlah transaksi di setiap level
            String query = "SELECT level, COUNT(*) AS count FROM transaction "
                    + "GROUP BY level "
                    + "ORDER BY count DESC "
                    + "LIMIT 1";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            // Ambil level yang paling sering dipesan
            if (rs.next()) {
                mostFrequentLevel = rs.getString("level");
            }

        } finally {
            // Tutup sumber daya database setelah digunakan
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return mostFrequentLevel;
    }
    public static int getFinishedStatus() throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Query untuk menghitung jumlah transaksi dengan status "Finished" untuk desainer tertentu
            String query = "SELECT COUNT(*) AS finished_count FROM transaction WHERE status = 'Finished'";
            stmt = conn.prepareStatement(query);
            
            rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("finished_count");
            }

        } finally {
            // Tutup sumber daya database setelah digunakan
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return count;
    }
    public static double getAverageSuccessRate() throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double successRate = 0;

        try {
            // Query untuk menghitung tingkat keberhasilan berdasarkan status "Finished" dan "Cancelled"
            String query = "SELECT "
                    + "COUNT(CASE WHEN status = 'Finished' THEN 1 ELSE NULL END) AS finishedCount, "
                    + "COUNT(CASE WHEN status = 'Cancelled' THEN 1 ELSE NULL END) AS cancelledCount "
                    + "FROM transaction "
                    ;
            stmt = conn.prepareStatement(query);
            
            rs = stmt.executeQuery();

            if (rs.next()) {
                int finishedCount = rs.getInt("finishedCount");
                int cancelledCount = rs.getInt("cancelledCount");

                // Hitung total transaksi "Finished" dan "Cancelled"
                int totalFinishedAndCancelled = finishedCount + cancelledCount;

                // Hitung tingkat keberhasilan (success rate)
                if (totalFinishedAndCancelled > 0) {
                    successRate = (double) finishedCount / totalFinishedAndCancelled * 100;
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return successRate;
    }
     public Map<String, Double> getTotalAmountForCurrentMonth() throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Double> totalAmountByMonth = new HashMap<>();

        try {
            // Query untuk mengambil total amount berdasarkan designer dan mengelompokkan data berdasarkan bulan
            // Menggunakan format '%Y-%m' pada kolom 'created_at' untuk mengelompokkan data per bulan
            String query = "SELECT DATE_FORMAT(created_at, '%Y-%m') AS month, SUM(amount) AS total_amount "
                    + "FROM transaction "
                    + "WHERE status = 'Finished' "
                    + "AND DATE_FORMAT(created_at, '%Y-%m') = DATE_FORMAT(NOW(), '%Y-%m') " // Hanya bulan saat ini
                    + "GROUP BY month";
            stmt = conn.prepareStatement(query);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Ubah format bulan agar sesuai dengan format 'MMMM yyyy' (nama bulan dalam bahasa Inggris dan tahun)
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
                String monthName = LocalDate.parse(rs.getString("month") + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(dateFormatter);
                double totalAmount = rs.getDouble("total_amount");

                // Simpan total amount berdasarkan bulan dalam Map
                totalAmountByMonth.put(monthName, totalAmount);
            }

        } finally {
            // Tutup sumber daya database setelah digunakan
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return totalAmountByMonth;
    }
     public static Map<String, Integer> getCountOfCancelledStatus() throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Integer> cancelledCountByMonth = new HashMap<>();

        try {
            // Query to count transactions with "Cancelled" status grouped by month based on designer
            // Format the date to be in the format "April 2024"
            String query = "SELECT DATE_FORMAT(created_at, '%M %Y') AS month, COUNT(*) AS cancelled_count "
                    + "FROM transaction "
                    + "WHERE status = 'Cancelled' "
                    + "GROUP BY month";

            stmt = conn.prepareStatement(query);
            

            rs = stmt.executeQuery();

            while (rs.next()) {
                // Retrieve the month and the count of cancelled transactions for that month
                String month = rs.getString("month");
                int count = rs.getInt("cancelled_count");

                // Debugging: Output the month and count
                System.out.println("Month: " + month + ", Cancelled Count: " + count);

                // Store the count of cancelled transactions by month in the map
                cancelledCountByMonth.put(month, count);
            }

        } finally {
            // Close database resources after use
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return cancelledCountByMonth;
    }
     public static Map<String, Integer> getCountOfFinishedStatusByMonth() throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Integer> finishedCountByMonth = new HashMap<>();

        try {
            // Query to count transactions with "Finished" status grouped by month based on designer
            // Format the date to be in the format "April 2024"
            String query = "SELECT DATE_FORMAT(created_at, '%M %Y') AS month, COUNT(*) AS finished_count "
                    + "FROM transaction "
                    + "WHERE status = 'Finished' "
                    + "GROUP BY month";

            stmt = conn.prepareStatement(query);
            

            rs = stmt.executeQuery();

            while (rs.next()) {
                // Retrieve the month and the count of finished transactions for that month
                String month = rs.getString("month");
                int count = rs.getInt("finished_count");

                // Debugging: Output the month and count
                System.out.println("Month: " + month + ", Finished Count: " + count);

                // Store the count of finished transactions by month in the map
                finishedCountByMonth.put(month, count);
            }

        } finally {
            // Close database resources after use
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return finishedCountByMonth;
    }
     public Map<String, Double> getTotalAmountByMonth() throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Double> totalAmountByMonth = new HashMap<>();

        try {
            // Query untuk mengambil total amount berdasarkan designer dan mengelompokkan data berdasarkan bulan
            // Menggunakan format '%Y-%m' pada kolom 'created_at' untuk mengelompokkan data per bulan
            String query = "SELECT DATE_FORMAT(created_at, '%Y-%m') AS month, SUM(amount) AS total_amount "
                    + "FROM transaction "
                    + "WHERE status = 'Finished' "
                    + "GROUP BY month";
            stmt = conn.prepareStatement(query);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Ubah format bulan agar sesuai dengan format 'MMMM yyyy' (nama bulan dalam bahasa Inggris dan tahun)
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
                String monthName = LocalDate.parse(rs.getString("month") + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(dateFormatter);
                double totalAmount = rs.getDouble("total_amount");

                // Simpan total amount berdasarkan bulan dalam Map
                totalAmountByMonth.put(monthName, totalAmount);
            }

        } finally {
            // Tutup sumber daya database setelah digunakan
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return totalAmountByMonth;
    }
}

