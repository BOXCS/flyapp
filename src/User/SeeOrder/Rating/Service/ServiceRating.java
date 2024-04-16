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
        ResultSet rs = null;
        String username = null;

        try {
            // Kueri untuk mendapatkan username berdasarkan transactionNumber
            String queryGetUsername = "SELECT username FROM transaction WHERE transaction_number = ?";
            p = getConnection().prepareStatement(queryGetUsername);
            p.setString(1, transactionNumber);
            rs = p.executeQuery();

            if (rs.next()) {
                // Dapatkan username dari hasil kueri
                username = rs.getString("username");
            }

            // Tutup statement dan result set setelah mendapatkan username
            rs.close();
            p.close();

            // Jika username ditemukan, simpan rating ke database
            if (username != null) {
                // Kueri untuk menyimpan rating ke database
                String sql = "INSERT INTO rating (transaction_number, designer_name, star_count, feedback, username) VALUES (?, ?, ?, ?, ?)";

                p = getConnection().prepareStatement(sql);
                p.setString(1, transactionNumber);
                p.setString(2, designerName);
                p.setInt(3, rating);
                p.setString(4, feedback);
                p.setString(5, username);

                int rowsAffected = p.executeUpdate();

                // Jika peringkat berhasil disimpan, perbarui status transaksi menjadi "Finished"
                if (rowsAffected > 0) {
                    // Perbarui status dalam tabel transactions
                    String updateStatusSql = "UPDATE transaction SET status = 'Finished' WHERE transaction_number = ?";
                    try (PreparedStatement updateP = getConnection().prepareStatement(updateStatusSql)) {
                        updateP.setString(1, transactionNumber);
                        updateP.executeUpdate();
                    }

                    // Perbarui status desainer ke "Available"
                    updateDesignerStatus(designerName, "Available");

                    System.out.println("Rating saved to database: " + rating);
                }
            } else {
                System.err.println("Username not found for transactionNumber: " + transactionNumber);
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

    private static void updateDesignerStatus(String designer, String status) throws SQLException {
        String updateQuery = "UPDATE designer SET Status = ? WHERE username = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, status);
            updateStatement.setString(2, designer);
            updateStatement.executeUpdate();
        }
    }
}
