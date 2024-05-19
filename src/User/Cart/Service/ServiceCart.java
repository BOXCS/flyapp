package User.Cart.Service;

import LoginRegister.Model.ModelUser;
import connection.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;

public class ServiceCart {

    public static void fetchCartOrders(DefaultTableModel model, String loggedInUsername) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan username pengguna yang login
            String sql = "SELECT transaction_number, product_name, designer, level, amount, status FROM cart WHERE username = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, loggedInUsername); // Set parameter username pengguna yang login
            ResultSet r = p.executeQuery();

            while (r.next()) {
                String transaction_number = r.getString("transaction_number");
                String product = r.getString("product_name");
                String designer = r.getString("designer");
                String level = r.getString("level");
                double amount = r.getDouble("amount");
                boolean status = r.getBoolean("status");

                // Menambahkan data ke dalam tabel
                model.addRow(new Object[]{transaction_number, product, level, designer, "$" + amount, status});
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

    public boolean deleteCartItem(String transactionNumber) {
        // Query SQL untuk menghapus item dari tabel cart berdasarkan transaction_number
        String query = "DELETE FROM cart WHERE transaction_number = ?";
        try {
            // Persiapkan statement SQL
            try (PreparedStatement pstmt = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
                // Set parameter
                pstmt.setString(1, transactionNumber); // Filter berdasarkan nomor transaksi

                // Eksekusi perintah delete
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

    // Metode untuk mendapatkan daftar desainer yang tersedia untuk produk tertentu
    public static List<String> getAvailableDesignersForProduct(String productName) {
        List<String> availableDesigners = new ArrayList<>();
        try {
            // Query untuk mendapatkan desainer yang tersedia untuk produk tertentu
            String sql = "SELECT username FROM designer WHERE typeContent = ? AND Status = 'Available'";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, productName); // Set parameter nama produk
            ResultSet r = p.executeQuery();

            // Tambahkan nama desainer yang tersedia ke dalam list
            while (r.next()) {
                availableDesigners.add(r.getString("username"));
            }

            r.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableDesigners;
    }

    public boolean updateDesigner(String transactionNumber, String designer) {
        // Query SQL untuk memperbarui desainer dalam tabel cart
        String updateCartQuery = "UPDATE cart SET designer = ? WHERE transaction_number = ?";
        // Query SQL untuk memperbarui desainer dalam tabel transaction
        String updateTransactionQuery = "UPDATE transaction SET designer = ? WHERE transaction_number = ?";

        try {
            // Mendapatkan koneksi dan memulai transaksi
            Connection connection = DatabaseConnection.getInstance().getConnection();
            connection.setAutoCommit(false); // Memulai transaksi

            try (PreparedStatement pstmtCart = connection.prepareStatement(updateCartQuery); PreparedStatement pstmtTransaction = connection.prepareStatement(updateTransactionQuery)) {

                // Set parameter untuk query pada tabel cart
                pstmtCart.setString(1, designer);
                pstmtCart.setString(2, transactionNumber);

                // Set parameter untuk query pada tabel transaction
                pstmtTransaction.setString(1, designer);
                pstmtTransaction.setString(2, transactionNumber);

                // Eksekusi perintah update pada kedua tabel
                int rowsAffectedCart = pstmtCart.executeUpdate();
                int rowsAffectedTransaction = pstmtTransaction.executeUpdate();

                // Log hasil update
                System.out.println("Rows affected in cart: " + rowsAffectedCart);
                System.out.println("Rows affected in transaction: " + rowsAffectedTransaction);

                // Jika ada baris yang terpengaruh di kedua tabel, commit transaksi
                if (rowsAffectedCart > 0 && rowsAffectedTransaction > 0) {
                    connection.commit();
                    return true;
                } else {
                    // Jika tidak, rollback transaksi
                    connection.rollback();
                    return false;
                }
            } catch (SQLException ex) {
                // Jika ada kesalahan, rollback transaksi
                connection.rollback();
                ex.printStackTrace();
                return false;
            } finally {
                connection.setAutoCommit(true); // Kembali ke autocommit
            }
        } catch (SQLException ex) {
            // Tangani kesalahan jika terjadi
            ex.printStackTrace();
            return false;
        }
    }

    public boolean isDesignerAvailable(String designerUsername) {
        // Query SQL untuk memeriksa apakah desainer tersedia (status 'Available')
        String query = "SELECT COUNT(*) AS count FROM designer WHERE username = ? AND Status = 'Available'";
        try {
            // Persiapkan statement SQL
            try (PreparedStatement pstmt = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
                // Set parameter
                pstmt.setString(1, designerUsername); // Filter berdasarkan username desainer

                // Eksekusi query
                ResultSet rs = pstmt.executeQuery();

                // Periksa hasil query
                if (rs.next()) {
                    int count = rs.getInt("count");
                    // Jika count lebih besar dari 0, berarti desainer tersedia
                    return count > 0;
                }

                rs.close();
            }
        } catch (SQLException ex) {
            // Tangani kesalahan jika terjadi
            ex.printStackTrace();
        }
        // Jika terjadi kesalahan atau desainer tidak tersedia, return false
        return false;
    }

}
