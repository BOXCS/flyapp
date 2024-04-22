package Dashboard.Designer.Profile.Service;

import Dashboard.Designer.Profile.Model.ModelRatingView;
import connection.DatabaseConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class ServiceProfile {

    private static Connection connection = null; // Menyimpan koneksi agar dapat digunakan kembali

    // Method untuk mendapatkan koneksi database
    private static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getInstance().getConnection();
        }
        return connection;
    }

    public static void insertPortfolio(int designerId, String mediaType, byte[] mediaContent) throws SQLException {
        // Gunakan try-with-resources untuk menangani PreparedStatement dan ResultSet
        try (PreparedStatement stmt = getConnection().prepareStatement("INSERT INTO portfolio (designer_id, media_type, media_content) VALUES (?, ?, ?)")) {
            stmt.setInt(1, designerId);
            stmt.setString(2, mediaType);
            stmt.setBytes(3, mediaContent);

            // Jalankan pernyataan dan periksa hasilnya
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new SQLException("Failed to insert portfolio, affected rows: " + rowsAffected);
            }
        } catch (SQLException e) {
            // Tangani kesalahan dan lempar ulang pengecualian
            e.printStackTrace();
            throw e;
        }
    }

    public static byte[] getVideoFromDatabase(String portfolioId) throws SQLException, IOException {
        byte[] videoData = null;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT media_content FROM portfolio WHERE portfolio_id = ?")) {
            preparedStatement.setString(1, portfolioId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Blob blob = resultSet.getBlob("media_content");
                    if (blob != null) {
                        try (InputStream inputStream = blob.getBinaryStream(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                            videoData = outputStream.toByteArray();
                        }
                    }
                }
            }
        } catch (SQLException | IOException e) {
            // Tangani kesalahan dan lempar ulang pengecualian jika perlu
            e.printStackTrace();
            throw e;
        }
        return videoData;
    }

    public static Map<String, Object> getPortfolioById(String portfolioId) throws SQLException {
        Map<String, Object> portfolioData = null;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM portfolio WHERE portfolio_id = ?")) {
            preparedStatement.setString(1, portfolioId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    portfolioData = new HashMap<>();
                    portfolioData.put("portfolio_id", resultSet.getString("portfolio_id"));
                    portfolioData.put("designer_id", resultSet.getInt("designer_id"));
                    portfolioData.put("media_type", resultSet.getString("media_type"));
                    portfolioData.put("media_content", resultSet.getBytes("media_content"));
                }
            }
        } catch (SQLException e) {
            // Tangani kesalahan dan lempar ulang pengecualian jika perlu
            e.printStackTrace();
            throw e;
        }
        return portfolioData;
    }

    public static List<Map<String, Object>> getPortfoliosByDesignerId(int designerId) throws SQLException {
        List<Map<String, Object>> portfolios = new ArrayList<>();

        // Validasi designerId
        if (designerId <= 0) {
            throw new IllegalArgumentException("designerId must be positive");
        }

        // Gunakan try-with-resources untuk mengelola PreparedStatement dan ResultSet
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM portfolio WHERE designer_id = ?")) {
            preparedStatement.setInt(1, designerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Map<String, Object> portfolioData = new HashMap<>();
                    portfolioData.put("portfolio_id", resultSet.getInt("portfolio_id"));
                    portfolioData.put("designer_id", resultSet.getInt("designer_id"));
                    portfolioData.put("media_type", resultSet.getString("media_type"));
                    portfolioData.put("media_content", resultSet.getBytes("media_content"));
                    portfolios.add(portfolioData);
                }
            }
        } catch (SQLException e) {
            // Tangani kesalahan dan lempar ulang pengecualian
            e.printStackTrace();
            throw e;
        }
        return portfolios;
    }

    public static List<Map<String, Object>> getAllPortfolios() throws SQLException {
        List<Map<String, Object>> portfolios = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM portfolio")) {
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    Map<String, Object> portfolioData = new HashMap<>();
                    portfolioData.put("portfolio_id", resultSet.getInt("portfolio_id"));
                    portfolioData.put("designer_id", resultSet.getInt("designer_id"));
                    portfolioData.put("media_type", resultSet.getString("media_type"));
                    portfolioData.put("media_content", resultSet.getBytes("media_content"));
                    portfolios.add(portfolioData);
                }
            }
        } catch (SQLException e) {
            // Tangani kesalahan dan lempar ulang pengecualian
            e.printStackTrace();
            throw e;
        }
        return portfolios;
    }

    // Metode untuk mengambil informasi dari tabel rating berdasarkan designer_name
    public static List<ModelRatingView> getRatingsByDesigner(String designerName) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ModelRatingView> ratings = new ArrayList<>();

        try {
            // Query untuk mengambil informasi dari tabel rating berdasarkan designer_name
            String query = "SELECT username, product_name, star_count, feedback "
                    + "FROM rating WHERE designer_name = ?";

            stmt = conn.prepareStatement(query);
            stmt.setString(1, designerName);
            rs = stmt.executeQuery();

            // Ambil data dari hasil query
            while (rs.next()) {
                String username = rs.getString("username");
                String productName = rs.getString("product_name");
                int starCount = rs.getInt("star_count");
                String feedback = rs.getString("feedback");

                // Buat objek RatingInfo untuk menyimpan informasi rating
                ModelRatingView ratingInfo = new ModelRatingView(username, productName, starCount, feedback);
                ratings.add(ratingInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return ratings;
    }

    public static int getRatingCountByDesigner(String designerName) throws SQLException {
        int ratingCount = 0;

        // Query untuk menghitung jumlah rating berdasarkan nama desainer
        String query = "SELECT COUNT(*) FROM rating WHERE designer_name = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, designerName);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ratingCount = rs.getInt(1);
                }
            }
        }
        return ratingCount;
    }

    public static double getAverageStarCountByDesigner(String designerName) throws SQLException {
        double averageStarCount = 0.0;

        // Query untuk menghitung rata-rata star_count berdasarkan nama desainer
        String query = "SELECT AVG(star_count) FROM rating WHERE designer_name = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, designerName);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    averageStarCount = rs.getDouble(1);
                }
            }
        }
        return averageStarCount;
    }

}
