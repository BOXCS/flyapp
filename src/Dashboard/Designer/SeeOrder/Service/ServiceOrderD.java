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
import java.sql.Connection;
import java.sql.Blob;

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

    public static void fetchWaitingOrdersByDesigner(DefaultTableModel model, String designerName) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan designer tertentu
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Waiting' AND designer = ?";
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

    public static void fetchPendingOrdersByDesigner(DefaultTableModel model, String designerName) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan designer tertentu
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Pending' AND designer = ?";
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

    public static void fetchFinishedOrdersByDesigner(DefaultTableModel model, String designerName) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan designer tertentu
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Finished' AND designer = ?";
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

    public static void fetchCancelledOrdersByDesigner(DefaultTableModel model, String designerName) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan designer tertentu
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Cancelled' AND designer = ?";
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

    public static void fetchLateOrdersByDesigner(DefaultTableModel model, String designerName) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan designer tertentu
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Late' AND designer = ?";
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
    public static void printReceipt(String type) {
        try {
            // Query to get transaction data based on transaction type
            String sql = "SELECT * FROM transactionreport WHERE type = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, type); // Set the transaction type parameter
            ResultSet r = p.executeQuery();

            // If transaction data is found
            if (r.next()) {
                String transactionNumber = r.getString("transaction_number");
                String username = r.getString("username");
                String product = r.getString("product_name");
                String level = r.getString("level");
                String designer = r.getString("designer");
                Timestamp created_at = r.getTimestamp("created_at");
                double amount = r.getDouble("amount");
                String typeSig = r.getString("type");
//                String status = r.getString("status");

                // Format date
                String formattedDate = new SimpleDateFormat("MMM dd, yyyy").format(created_at);

                // Prepare parameters for the report
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("transaction_number", transactionNumber);
                parameters.put("username", username);
                parameters.put("product_name", product);
                parameters.put("level", level);
                parameters.put("designer", designer);
                parameters.put("created_at", formattedDate);
                parameters.put("amount", amount);
                parameters.put("type", typeSig);
//                parameters.put("status", status);

                // Select the report file based on transaction type
                String reportFilePath = "C:\\Users\\aisya\\OneDrive\\Documents\\NetBeansProjects\\flyapp_secondary\\src\\nota\\Main\\report1.jasper";

                // Fill the report with data and parameters
                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        reportFilePath,
                        parameters,
                        DatabaseConnection.getInstance().getConnection()
                );

                // Check if the JasperPrint object has pages
                if (jasperPrint.getPages().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "The report document has no pages.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Display the report to the user
                JasperViewer.viewReport(jasperPrint, false);

                // Save the report to a PDF file
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save PDF File");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setSelectedFile(new File(transactionNumber + ".pdf"));

                int option = fileChooser.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    // Export the report to PDF using JasperExportManager
                    JasperExportManager.exportReportToPdfFile(jasperPrint, file.getAbsolutePath());
                    JOptionPane.showMessageDialog(null, "Receipt successfully exported to PDF.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Operation canceled by the user.", "Cancel", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                // If transaction number not found, show error message
                JOptionPane.showMessageDialog(null, "Transaction type not found.", "Error", JOptionPane.ERROR_MESSAGE);
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

    public static void cancelOrder(String transactionNumber, String userName, String reason) {
        try {
            // Query untuk mengupdate status transaksi menjadi "Cancelled"
            String updateSql = "UPDATE transaction SET status = 'Cancelled' WHERE transaction_number = ? AND username = ?";
            PreparedStatement updateStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(updateSql);
            updateStatement.setString(1, transactionNumber); // Set parameter nomor transaksi
            updateStatement.setString(2, userName);
            int updatedRows = updateStatement.executeUpdate();

            if (updatedRows > 0) {
                // Insert ke tabel canceltransaction
                String insertSql = "INSERT INTO canceltransaction (transaction_number, reason) VALUES (?, ?)";
                PreparedStatement insertStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(insertSql);
                insertStatement.setString(1, transactionNumber);
                insertStatement.setString(2, reason);
                insertStatement.executeUpdate();
                insertStatement.close();

                JOptionPane.showMessageDialog(null, "Order cancelled successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                String userEmail = getUserEmail(userName);

                if (userEmail != null) {
                    MailNotification mailNotif = new MailNotification();
                    String notificationSubject = "Order Cancellation Notification";
                    String notificationMessage = "We're Sorry your order " + transactionNumber + " has been cancelled.\n\n"
                            + "Reason: " + reason + "\n\n"
                            + "Please contact our admin for more info.";
                    mailNotif.sendNotification(userEmail, notificationSubject, notificationMessage);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to cancel order.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void insertIntoPortfolio(String transactionNumber, int designerId, String productType) {
        String selectSql;
        String insertSql = "INSERT INTO portfolio (designer_id, media_content, media_type) VALUES (?, ?, ?)";

        // Determine which table to query based on product type
        if (productType.equalsIgnoreCase("Video Editing")) {
            selectSql = "SELECT result AS media_content FROM result WHERE transaction_number = ?";
        } else if (productType.equalsIgnoreCase("Design Graphic") || productType.equalsIgnoreCase("3D Modelling")) {
            selectSql = "SELECT result AS media_content FROM resultimage WHERE transaction_number = ?";
        } else {
            JOptionPane.showMessageDialog(null, "Unsupported product type.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection con = null;
        PreparedStatement selectStmt = null;
        PreparedStatement insertStmt = null;
        ResultSet rs = null;

        try {
            con = DatabaseConnection.getInstance().getConnection();
            selectStmt = con.prepareStatement(selectSql);
            insertStmt = con.prepareStatement(insertSql);

            // Fetch the media content
            selectStmt.setString(1, transactionNumber);
            rs = selectStmt.executeQuery();
            if (rs.next()) {
                Blob mediaContent = rs.getBlob("media_content");

                // Insert into portfolio
                insertStmt.setInt(1, designerId);
                insertStmt.setBlob(2, mediaContent);
                insertStmt.setString(3, productType.equalsIgnoreCase("Video Editing") ? "video" : "image");
                insertStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Media content added to portfolio successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No media content found for the selected transaction.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close only the ResultSet and PreparedStatement, not the Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (selectStmt != null) {
                    selectStmt.close();
                }
                if (insertStmt != null) {
                    insertStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
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
