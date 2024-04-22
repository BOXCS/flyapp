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

    public static void saveRatingToDatabase(String transactionNumber, String designerName, int rating, String feedback, String productName) throws SQLException {
        PreparedStatement p = null;
        ResultSet rs = null;
        String username = null;

        try {
            // Kueri untuk mendapatkan username dan product_name berdasarkan transaction_number
            String queryGetUserData = "SELECT username, product_name FROM transaction WHERE transaction_number = ?";
            p = getConnection().prepareStatement(queryGetUserData);
            p.setString(1, transactionNumber);
            rs = p.executeQuery();

            if (rs.next()) {
                // Dapatkan username dan product_name dari hasil kueri
                username = rs.getString("username");
                productName = rs.getString("product_name");
            }

            // Tutup statement dan result set setelah mendapatkan username dan product_name
            rs.close();
            p.close();

            // Jika username ditemukan, simpan rating ke database
            if (username != null) {
                // Kueri untuk menyimpan rating ke database
                String sql = "INSERT INTO rating (transaction_number, designer_name, star_count, feedback, username, product_name) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";

                p = getConnection().prepareStatement(sql);
                p.setString(1, transactionNumber);
                p.setString(2, designerName);
                p.setInt(3, rating);
                p.setString(4, feedback);
                p.setString(5, username);
                p.setString(6, productName); // Tambahkan product_name ke kueri

                int rowsAffected = p.executeUpdate();

                // Jika peringkat berhasil disimpan, perbarui status transaksi menjadi "Finished"
                if (rowsAffected > 0) {
                    // Perbarui status dalam tabel transaction
                    String updateStatusSql = "UPDATE transaction SET status = 'Finished' WHERE transaction_number = ?";
                    try (PreparedStatement updateP = getConnection().prepareStatement(updateStatusSql)) {
                        updateP.setString(1, transactionNumber);
                        updateP.executeUpdate();
                    }

                    // Perbarui status desainer ke "Available"
                    updateDesignerStatus(designerName, "Available");

                    System.out.println("Rating saved to database with rating: " + rating + " and product name: " + productName);
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

    // Method untuk memperbarui status desainer
    private static void updateDesignerStatus(String designer, String status) throws SQLException {
        String updateQuery = "UPDATE designer SET status = ? WHERE username = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, status);
            updateStatement.setString(2, designer);
            updateStatement.executeUpdate();
        }
    }
}
