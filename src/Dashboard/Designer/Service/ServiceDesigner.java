package Dashboard.Designer.Service;

import Dashboard.Designer.Model.ModelDesigner;
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

public class ServiceDesigner {

    private static Connection connection = null; // Menyimpan koneksi agar dapat digunakan kembali

    // Method untuk mendapatkan koneksi database
    private static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getInstance().getConnection();
        }
        return connection;
    }

    // Method to get designer's information
    public static ModelDesigner getDesignerInfo(String username) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ModelDesigner info = new ModelDesigner();

        try {
            // Query untuk mengambil typeContent, instagram, Status, dan member_since dari tabel designer
            String query = "SELECT typeContent, instagram, Status FROM designer WHERE username = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                info.setTypeContent(rs.getString("typeContent"));
                info.setInstagram(rs.getString("instagram"));
                info.setStatus(rs.getString("Status"));
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

        return info;
    }

    // Method to calculate average rating (star_count) for a designer
    public static double getAverageRating(String username) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double averageRating = 0;

        try {
            // Query to calculate average star_count from rating table
            String query = "SELECT AVG(star_count) AS average_rating FROM rating WHERE designer_name = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                averageRating = rs.getDouble("average_rating");
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return averageRating;
    }

    // Method to calculate average success rate for a designer
    public static double getAverageSuccessRate(String username) throws SQLException {
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
                    + "WHERE designer = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
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

    // Method to calculate total amount for a designer
    public static double getTotalAmount(String username) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double totalAmount = 0;

        try {
            // Query to calculate total amount from transaction table
            String query = "SELECT SUM(amount) AS total_amount FROM transaction WHERE designer = ? AND status = 'Finished'";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalAmount = rs.getDouble("total_amount");
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return totalAmount;
    }

    public Map<String, Double> getTotalAmountForCurrentMonth(String username) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Double> totalAmountByMonth = new HashMap<>();

        try {
            // Query untuk mengambil total amount berdasarkan designer dan mengelompokkan data berdasarkan bulan
            // Menggunakan format '%Y-%m' pada kolom 'created_at' untuk mengelompokkan data per bulan
            String query = "SELECT DATE_FORMAT(created_at, '%Y-%m') AS month, SUM(amount) AS total_amount "
                    + "FROM transaction "
                    + "WHERE designer = ? AND status = 'Finished' "
                    + "AND DATE_FORMAT(created_at, '%Y-%m') = DATE_FORMAT(NOW(), '%Y-%m') " // Hanya bulan saat ini
                    + "GROUP BY month";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
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

    public static int getCountOfFinishedStatus(String username) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Query untuk menghitung jumlah transaksi dengan status "Finished" untuk desainer tertentu
            String query = "SELECT COUNT(*) AS finished_count FROM transaction WHERE designer = ? AND status = 'Finished'";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
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

    public static String getMostFrequentLevel(String username) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String mostFrequentLevel = null;

        try {
            // Query untuk mengelompokkan transaksi berdasarkan level dan menghitung jumlah transaksi di setiap level
            String query = "SELECT level, COUNT(*) AS count FROM transaction "
                    + "WHERE designer = ? "
                    + "GROUP BY level "
                    + "ORDER BY count DESC "
                    + "LIMIT 1";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
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

    public static Map<String, Integer> getCountOfCancelledStatus(String username) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Integer> cancelledCountByMonth = new HashMap<>();

        try {
            // Query to count transactions with "Cancelled" status grouped by month based on designer
            // Format the date to be in the format "April 2024"
            String query = "SELECT DATE_FORMAT(created_at, '%M %Y') AS month, COUNT(*) AS cancelled_count "
                    + "FROM transaction "
                    + "WHERE designer = ? AND status = 'Cancelled' "
                    + "GROUP BY month";

            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

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

    public static Map<String, Integer> getCountOfFinishedStatusByMonth(String username) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Integer> finishedCountByMonth = new HashMap<>();

        try {
            // Query to count transactions with "Finished" status grouped by month based on designer
            // Format the date to be in the format "April 2024"
            String query = "SELECT DATE_FORMAT(created_at, '%M %Y') AS month, COUNT(*) AS finished_count "
                    + "FROM transaction "
                    + "WHERE designer = ? AND status = 'Finished' "
                    + "GROUP BY month";

            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

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

    public static String getMemberSince(String username) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String formattedMemberSince = null;

        try {
            // Query untuk mengambil kolom member_since dari tabel designer berdasarkan username
            String query = "SELECT member_since FROM designer WHERE username = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Ambil nilai member_since dari hasil query sebagai Timestamp
                Timestamp memberSinceTimestamp = rs.getTimestamp("member_since");

                // Ubah Timestamp menjadi LocalDateTime
                LocalDateTime memberSinceDateTime = memberSinceTimestamp.toLocalDateTime();

                // Ubah LocalDateTime ke format "April 2024" menggunakan DateTimeFormatter
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
                formattedMemberSince = memberSinceDateTime.format(dateFormatter);
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

        return formattedMemberSince;
    }

    public Map<String, Double> getTotalAmountByMonth(String username) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Double> totalAmountByMonth = new HashMap<>();

        try {
            // Query untuk mengambil total amount berdasarkan designer dan mengelompokkan data berdasarkan bulan
            // Menggunakan format '%Y-%m' pada kolom 'created_at' untuk mengelompokkan data per bulan
            String query = "SELECT DATE_FORMAT(created_at, '%Y-%m') AS month, SUM(amount) AS total_amount "
                    + "FROM transaction "
                    + "WHERE designer = ? AND status = 'Finished' "
                    + "GROUP BY month";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
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

    public static LocalDateTime getLastDesignDate(String username) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LocalDateTime lastDesignDate = null;

        try {
            // Query untuk mengambil informasi created_at dari transaksi dengan status "Finished" untuk desainer tertentu
            // Urutkan hasil berdasarkan created_at dengan urutan menurun dan batasi hasil hanya satu baris
            String query = "SELECT created_at FROM transaction WHERE designer = ? AND status = 'Finished' ORDER BY created_at DESC LIMIT 1";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            // Jika hasil query ditemukan, dapatkan created_at sebagai LocalDateTime
            if (rs.next()) {
                lastDesignDate = rs.getTimestamp("created_at").toLocalDateTime();
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

        return lastDesignDate;
    }

}
