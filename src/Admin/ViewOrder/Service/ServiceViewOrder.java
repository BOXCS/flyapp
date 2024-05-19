package Admin.ViewOrder.Service;

import connection.DatabaseConnection;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import java.util.Map;
import javax.swing.JFileChooser;
import net.sf.jasperreports.engine.JasperExportManager;
//import notif.Mail.MailNotification;

public class ServiceViewOrder {

    public static void fetchActiveOrders(DefaultTableModel model) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan username pengguna yang login
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Active'";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet r = p.executeQuery();

            while (r.next()) {
                String number = r.getString("transaction_number");
                String username = r.getString("username");
                String product = r.getString("product_name");
                String level = r.getString("level");
                String designer = r.getString("designer");
                Timestamp created_at = r.getTimestamp("created_at");
                double amount = r.getDouble("amount");
                String status = r.getString("status");

                // Mengubah format tanggal menjadi "Feb 11, 2024"
                String formattedDate = new SimpleDateFormat("MMM dd, yyyy").format(created_at);

                // Menambahkan data ke dalam tabel
                model.addRow(new Object[]{number, username, product, level, designer, formattedDate, "$" + amount, status});
            }

            r.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void fetchWaitingOrders(DefaultTableModel model) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan username pengguna yang login
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Waiting'";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet r = p.executeQuery();

            while (r.next()) {
                String number = r.getString("transaction_number");
                String username = r.getString("username");
                String product = r.getString("product_name");
                String level = r.getString("level");
                String designer = r.getString("designer");
                Timestamp created_at = r.getTimestamp("created_at");
                double amount = r.getDouble("amount");
                String status = r.getString("status");

                // Mengubah format tanggal menjadi "Feb 11, 2024"
                String formattedDate = new SimpleDateFormat("MMM dd, yyyy").format(created_at);

                // Menambahkan data ke dalam tabel
                model.addRow(new Object[]{number, username, product, level, designer, formattedDate, "$" + amount, status});
            }

            r.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void fetchPendingOrders(DefaultTableModel model) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan username pengguna yang login
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Pending'";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet r = p.executeQuery();

            while (r.next()) {
                String number = r.getString("transaction_number");
                String username = r.getString("username");
                String product = r.getString("product_name");
                String level = r.getString("level");
                String designer = r.getString("designer");
                Timestamp created_at = r.getTimestamp("created_at");
                double amount = r.getDouble("amount");
                String status = r.getString("status");

                // Mengubah format tanggal menjadi "Feb 11, 2024"
                String formattedDate = new SimpleDateFormat("MMM dd, yyyy").format(created_at);

                // Menambahkan data ke dalam tabel
                model.addRow(new Object[]{number, username, product, level, designer, formattedDate, "$" + amount, status});
            }

            r.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void fetchFinishedOrders(DefaultTableModel model) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan username pengguna yang login
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Finished'";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet r = p.executeQuery();

            while (r.next()) {
                String number = r.getString("transaction_number");
                String username = r.getString("username");
                String product = r.getString("product_name");
                String level = r.getString("level");
                String designer = r.getString("designer");
                Timestamp created_at = r.getTimestamp("created_at");
                double amount = r.getDouble("amount");
                String status = r.getString("status");

                // Mengubah format tanggal menjadi "Feb 11, 2024"
                String formattedDate = new SimpleDateFormat("MMM dd, yyyy").format(created_at);

                // Menambahkan data ke dalam tabel
                model.addRow(new Object[]{number, username, product, level, designer, formattedDate, "$" + amount, status});
            }

            r.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void fetchCancelledOrders(DefaultTableModel model) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan username pengguna yang login
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Cancelled'";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet r = p.executeQuery();

            while (r.next()) {
                String number = r.getString("transaction_number");
                String username = r.getString("username");
                String product = r.getString("product_name");
                String level = r.getString("level");
                String designer = r.getString("designer");
                Timestamp created_at = r.getTimestamp("created_at");
                double amount = r.getDouble("amount");
                String status = r.getString("status");

                // Mengubah format tanggal menjadi "Feb 11, 2024"
                String formattedDate = new SimpleDateFormat("MMM dd, yyyy").format(created_at);

                // Menambahkan data ke dalam tabel
                model.addRow(new Object[]{number, username, product, level, designer, formattedDate, "$" + amount, status});
            }

            r.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void fetchLateOrders(DefaultTableModel model) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan username pengguna yang login
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Late'";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet r = p.executeQuery();

            while (r.next()) {
                String number = r.getString("transaction_number");
                String username = r.getString("username");
                String product = r.getString("product_name");
                String level = r.getString("level");
                String designer = r.getString("designer");
                Timestamp created_at = r.getTimestamp("created_at");
                double amount = r.getDouble("amount");
                String status = r.getString("status");

                // Mengubah format tanggal menjadi "Feb 11, 2024"
                String formattedDate = new SimpleDateFormat("MMM dd, yyyy").format(created_at);

                // Menambahkan data ke dalam tabel
                model.addRow(new Object[]{number, username, product, level, designer, formattedDate, "$" + amount, status});
            }

            r.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
