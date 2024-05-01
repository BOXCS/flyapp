package Dashboard.User.ViewPortfolio.Service;

import Dashboard.User.ViewPortfolio.Model.ModelViewPortfolio;
import connection.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;

public class ServiceViewPortfolio {

    private static Connection connection = null; // Menyimpan koneksi agar dapat digunakan kembali

    // Method untuk mendapatkan koneksi database
    private static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getInstance().getConnection();
        }
        return connection;
    }

    // Method untuk mengambil data dari tabel portfolio
    public List<ModelViewPortfolio> fetchDataPortfolio() {
        List<ModelViewPortfolio> portfolios = new ArrayList<>();

        PreparedStatement p = null;
        ResultSet r = null;

        try {
            // Ubah query SQL untuk bergabung dengan tabel designer dan mengambil username
            String sql = "SELECT p.portfolio_id, p.designer_id, d.username AS designer_username, p.media_content, p.media_type "
                    + "FROM portfolio p "
                    + "JOIN designer d ON p.designer_id = d.designer_id";
            p = getConnection().prepareStatement(sql);

            r = p.executeQuery();
            while (r.next()) {
                int portfolio_id = r.getInt("portfolio_id");
                int designerId = r.getInt("designer_id");
                String username = r.getString("designer_username");
                byte[] mediaContent = r.getBytes("media_content");
                String mediaType = r.getString("media_type");

                ModelViewPortfolio portfolio = new ModelViewPortfolio(portfolio_id, designerId, username, mediaContent, mediaType);
                portfolios.add(portfolio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (r != null) {
                    r.close();
                }
                if (p != null) {
                    p.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return portfolios;
    }

}
