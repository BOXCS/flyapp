package Dashboard.Admin.RatingView.Service;

import Dashboard.Designer.Profile.Model.ModelRatingView;
import connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiceViewRating {

    private static Connection connection = null; // Menyimpan koneksi agar dapat digunakan kembali

    // Method untuk mendapatkan koneksi database
    private static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getInstance().getConnection();
        }
        return connection;
    }

    // Method untuk mengambil data dari tabel rating_view
    public static List<ModelRatingView> getRatingData() throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ModelRatingView> ratingV = new ArrayList<>();

        try {
            conn = getConnection();
            // Query untuk mengambil data dari tabel rating_view
            String query = "SELECT star_count, feedback, designer_name, username, product_name FROM rating";
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int starCount = resultSet.getInt("star_count");
                String feedback = resultSet.getString("feedback");
                String designerName = resultSet.getString("designer_name");
                String username = resultSet.getString("username");
                String productName = resultSet.getString("product_name");

                ModelRatingView ratingView = new ModelRatingView(starCount, feedback, designerName, username, productName);
                ratingV.add(ratingView);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }

        return ratingV;
    }
}
