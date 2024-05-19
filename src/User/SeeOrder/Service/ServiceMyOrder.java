package User.SeeOrder.Service;

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
import notif.Mail.MailNotification;

public class ServiceMyOrder {

    public static void fetchActiveOrders(DefaultTableModel model, String loggedInUsername) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan username pengguna yang login
            String sql = "SELECT type, transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Active' AND username = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, loggedInUsername); // Set parameter username pengguna yang login
            ResultSet r = p.executeQuery();

            while (r.next()) {
                String type = r.getString("type");
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
                model.addRow(new Object[]{type, number, username, product, level, designer, formattedDate, "$" + amount, status});
            }

            r.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fetchPendingOrders(DefaultTableModel model, String loggedInUsername) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Active dan username pengguna yang login
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Pending' AND username = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, loggedInUsername); // Set parameter username pengguna yang login
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

    public static void fetchWaitingOrders(DefaultTableModel model, String loggedInUsername) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Waiting dan username pengguna yang login
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Waiting' AND username = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, loggedInUsername); // Set parameter username pengguna yang login
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

    public static void fetchFinishedOrders(DefaultTableModel model, String loggedInUsername) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Waiting dan username pengguna yang login
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Finished' AND username = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, loggedInUsername); // Set parameter username pengguna yang login
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

    public static void fetchCancelledOrders(DefaultTableModel model, String loggedInUsername) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Waiting dan username pengguna yang login
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Cancelled' AND username = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, loggedInUsername); // Set parameter username pengguna yang login
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

    public static void fetchLateOrders(DefaultTableModel model, String loggedInUsername) {
        model.setRowCount(0); // Menghapus semua baris yang ada pada tabel

        try {
            // Query untuk mendapatkan data dari tabel transaction dengan status Waiting dan username pengguna yang login
            String sql = "SELECT transaction_number, username, product_name, level, designer, created_at, amount, status FROM transaction WHERE status = 'Late' AND username = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, loggedInUsername); // Set parameter username pengguna yang login
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

    public static void addRating(String transactionNumber, int starCount) {
        try {
            // Query untuk menambahkan data rating ke dalam tabel rating
            String sql = "INSERT INTO rating (transaction_number, star_count) VALUES (?, ?)";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, transactionNumber); // Set parameter transaction_number
            p.setInt(2, starCount); // Set parameter star_count
            p.executeUpdate(); // Eksekusi query

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

    public static void insertFootage(String transactionNumber, String footageLink, String information, String designerName, String productOrder, String levelOrder, String emailUser) {
        try {
            // Query untuk mendapatkan data designer, product_name, level, dan username berdasarkan transaction_number
            String queryTransaction = "SELECT designer, product_name, level, username FROM transaction WHERE transaction_number = ?";
            PreparedStatement statementTransaction = DatabaseConnection.getInstance().getConnection().prepareStatement(queryTransaction);
            statementTransaction.setString(1, transactionNumber);
            ResultSet resultSetTransaction = statementTransaction.executeQuery();

            if (resultSetTransaction.next()) {
                // Ambil nilai designer, product_name, level, dan username dari hasil kueri
                String designer = resultSetTransaction.getString("designer");
                String productName = resultSetTransaction.getString("product_name");
                String level = resultSetTransaction.getString("level");
                String username = resultSetTransaction.getString("username");

                // Query untuk mendapatkan email pengguna dari tabel user berdasarkan username
                String queryUser = "SELECT email FROM user WHERE username = ?";
                PreparedStatement statementUser = DatabaseConnection.getInstance().getConnection().prepareStatement(queryUser);
                statementUser.setString(1, username);
                ResultSet resultSetUser = statementUser.executeQuery();

                if (resultSetUser.next()) {
                    // Ambil email pengguna dari hasil kueri
                    String email = resultSetUser.getString("email");

                    // Query untuk menambahkan data footage ke dalam tabel footage
                    String sql = "INSERT INTO footage (transaction_number, footage_link, information, designer, product_name, level, user_email) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
                    p.setString(1, transactionNumber);
                    p.setString(2, footageLink);
                    p.setString(3, information);
                    p.setString(4, designerName);
                    p.setString(5, productOrder);
                    p.setString(6, levelOrder);
                    p.setString(7, email);
                    p.executeUpdate(); // Eksekusi query

                    p.close();
                } else {
                    System.out.println("User email not found.");
                }

                resultSetUser.close();
                statementUser.close();
            } else {
                System.out.println("Transaction number not found.");
            }

            String designerEmail = getDesignerEmail(designerName);

            // Send email notification
            if (designerEmail != null) {
                MailNotification mailNotif = new MailNotification();
                String notificationSubject = "Notification";
                String notificationMessage = "Hello, I want to send my footage for transaction number: " + transactionNumber + "\n\n"
                        + "I ordered " + productOrder + " with level " + levelOrder + " for designer " + designerName + "\n\n"
                        + "This is the link for you to get my footage: " + footageLink + "\n\n"
                        + "And this is the information related to my order: " + information;
                mailNotif.sendNotification(designerEmail, notificationSubject, notificationMessage);
            }

            resultSetTransaction.close();
            statementTransaction.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getDesignerEmail(String designerUsername) {
        // Query to get the designer's email based on the designer's username
        String query = "SELECT email FROM designer WHERE username = ?";
        try (PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, designerUsername);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no email is found or an error occurs
    }

}
