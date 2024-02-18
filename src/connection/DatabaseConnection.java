package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {

    }

    public void connectToDatabase() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/fly_studio", "root", "");
        } catch (SQLException e) {
            e.printStackTrace(); // Atau log pesan kesalahan
            // Handle kesalahan koneksi
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
