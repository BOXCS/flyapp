package User.SeeOrder.Rating.Service;

import connection.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;

public class ServiceRating {

    private static Connection connection = null; // Menyimpan koneksi agar dapat digunakan kembali

    // Method untuk mendapatkan koneksi database
    private static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getInstance().getConnection();
        }
        return connection;
    }

    public static void saveRatingToDatabase(String transactionNumber, String designerName, int rating, String feedback) throws SQLException {
        PreparedStatement p = null;
        try {
            String sql = "INSERT INTO rating (transaction_number, designer_name, star_count, feedback) VALUES (?, ?, ?, ?)";

            p = getConnection().prepareStatement(sql);
            p.setString(1, transactionNumber);
            p.setString(2, designerName);
            p.setInt(3, rating);
            p.setString(4, feedback);

            p.executeUpdate();

            System.out.println("Rating saved to database: " + rating);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (p != null) {
                p.close();
            }
        }
    }

    // Method untuk memeriksa apakah transactionNumber sudah dinilai sebelumnya
    public static boolean isTransactionAlreadyRated(String transactionNumber) throws SQLException {
        PreparedStatement p = null;
        ResultSet rs = null;
        boolean alreadyRated = false;
        try {
            String sql = "SELECT COUNT(*) AS count FROM rating WHERE transaction_number = ?";

            p = getConnection().prepareStatement(sql);
            p.setString(1, transactionNumber);
            rs = p.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                alreadyRated = (count > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (p != null) {
                p.close();
            }
        }
        return alreadyRated;
    }
}
