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
        try {
            // Disini, Anda bisa melakukan inisialisasi koneksi, misalnya saat aplikasi dimulai
            connectToDatabase();
        } catch (SQLException e) {
            // Tangani kesalahan koneksi jika diperlukan
            e.printStackTrace();
        }
    }

    public void connectToDatabase() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/fly_studio", "root", "");
        } catch (SQLException e) {
            // Tangani kesalahan koneksi dengan memberikan pesan kesalahan yang bermakna atau logging
            System.err.println("Failed to connect to database: " + e.getMessage());
            throw e;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Tangani kesalahan penutupan koneksi
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
