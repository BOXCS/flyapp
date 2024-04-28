package Dashboard.Designer.SeeOrder.Service;

import LoginRegister.Model.ModelUser;
import connection.DatabaseConnection;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import notif.Mail.MailNotification;

public class ServiceOrderD {

    public static void fetchActiveOrdersByDesigner(DefaultTableModel model, String designerName) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan designer tertentu
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Active' AND designer = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, designerName); // Set parameter designer
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

    // Fungsi untuk mencetak nota berdasarkan nomor transaksi
    public static void printReceipt(String transactionNumber) {
        try {
            // Query untuk mendapatkan data transaksi berdasarkan nomor transaksi
            String sql = "SELECT * FROM transaction WHERE transaction_number = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, transactionNumber); // Set parameter nomor transaksi
            ResultSet r = p.executeQuery();

            // Jika data transaksi ditemukan
            if (r.next()) {
                String username = r.getString("username");
                String product = r.getString("product_name");
                String level = r.getString("level");
                String designer = r.getString("designer");
                Timestamp created_at = r.getTimestamp("created_at");
                double amount = r.getDouble("amount");
                String status = r.getString("status");

                // Format tanggal
                String formattedDate = new SimpleDateFormat("MMM dd, yyyy").format(created_at);

                // Menyiapkan parameter untuk laporan nota
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("transaction_number", transactionNumber);
                parameters.put("username", username);
                parameters.put("product_name", product);
                parameters.put("level", level);
                parameters.put("designer", designer);
                parameters.put("created_at", formattedDate);
                parameters.put("amount", amount);
                parameters.put("status", status);

                // Mengisi laporan nota
                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        "D:\\Zaky\\NeatBeans Project\\flyapp\\src\\nota\\Main\\report1.jasper",
                        parameters,
                        DatabaseConnection.getInstance().getConnection()
                );

                // Menampilkan laporan nota ke pengguna
                JasperViewer.viewReport(jasperPrint, false);

                // Simpan laporan nota ke file PDF
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save PDF File");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setSelectedFile(new File(transactionNumber + ".pdf"));

                int option = fileChooser.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    // Ekspor laporan ke PDF menggunakan JasperExportManager
                    JasperExportManager.exportReportToPdfFile(jasperPrint, file.getAbsolutePath());
                    JOptionPane.showMessageDialog(null, "Receipt successfully exported to PDF.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Operation canceled by the user.", "Cancel", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                // Jika nomor transaksi tidak ditemukan, tampilkan pesan kesalahan
                JOptionPane.showMessageDialog(null, "Transaction number not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            r.close();
            p.close();
        } catch (SQLException | JRException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void approveOrder(String transactionNumber, String userName, ModelUser user) {
        try {
            // Query untuk mengupdate status transaksi menjadi "Approved"
            String sql = "UPDATE transaction SET status = 'Active' WHERE transaction_number = ? AND username = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, transactionNumber); // Set parameter nomor transaksi
            p.setString(2, userName);
            int updatedRows = p.executeUpdate();

            if (updatedRows > 0) {
                JOptionPane.showMessageDialog(null, "Order approved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                String userEmail = getUserEmail(userName);
                
                if (userEmail != null) {
                    MailNotification mailNotif = new MailNotification();
                    String notificationSubject = "Notification";
                    String notificationMessage = user.getUserName() + " has approved your order with transaction number: " + transactionNumber;
                    mailNotif.sendNotification(userEmail, notificationSubject, notificationMessage);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to approve order.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void cancelOrder(String transactionNumber, String userName, ModelUser user) {
        try {
            // Query untuk mengupdate status transaksi menjadi "Approved"
            String sql = "UPDATE transaction SET status = 'Cancelled' WHERE transaction_number = ? AND username = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, transactionNumber); // Set parameter nomor transaksi
            p.setString(2, userName);
            int updatedRows = p.executeUpdate();

            if (updatedRows > 0) {
                JOptionPane.showMessageDialog(null, "Order approved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                String userEmail = getUserEmail(userName);
                
                if (userEmail != null) {
                    MailNotification mailNotif = new MailNotification();
                    String notificationSubject = "Notification";
                    String notificationMessage = user.getUserName() + " has approved your order with transaction number: " + transactionNumber;
                    mailNotif.sendNotification(userEmail, notificationSubject, notificationMessage);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to approve order.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String getUserEmail(String userName) {
    try {
        String sql = "SELECT email FROM user WHERE username = ?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setString(1, userName);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            return r.getString("email");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

}
