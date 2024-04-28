package User.Cart.Service;

import LoginRegister.Model.ModelUser;
import connection.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

public class ServiceCart {

    public static void fetchCartOrders(DefaultTableModel model, String loggedInUsername) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan username pengguna yang login
            String sql = "SELECT product_name, designer, level, amount, status FROM cart WHERE username = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, loggedInUsername); // Set parameter username pengguna yang login
            ResultSet r = p.executeQuery();

            while (r.next()) {
                String product = r.getString("product_name");
                String designer = r.getString("designer");
                String level = r.getString("level");
                double amount = r.getDouble("amount");
                boolean status = r.getBoolean("status");

                // Menambahkan data ke dalam tabel
                model.addRow(new Object[]{product, level, designer, "$" + amount, status});
            }

            r.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metode untuk memperbarui status transaksi dalam database
    public boolean updateTransactionStatus(String productName, String transactionSig) {
        // Query SQL untuk memperbarui status transaksi
        String query = "UPDATE transaction SET status = 'Waiting', type = ? WHERE product_name = ? AND status = 'Carted'";
        try {
            // Persiapkan statement SQL
            try (PreparedStatement pstmt = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
                // Set parameter
                pstmt.setString(1, transactionSig); // Update type ke transactionSig yang baru di-generate
                pstmt.setString(2, productName); // Filter berdasarkan nama produk

                // Eksekusi perintah update
                int rowsAffected = pstmt.executeUpdate();
                
                // Jika ada baris yang terpengaruh, return true (berhasil)
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            // Tangani kesalahan jika terjadi
            ex.printStackTrace();
            return false;
        }
    }
}
