package Dashboard.Designer.SendOrder.Service;

import Dashboard.Designer.SendOrder.Model.ModelReceiptData;
import java.sql.ResultSet;
import connection.DatabaseConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import notif.Mail.MailNotification;

public class ServiceSendOrder {

    // Method untuk mendapatkan koneksi database
    public static Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance().getConnection();
    }

    public static ModelReceiptData getReceiptData(String transactionNumber) {
        // Query mysql
        String query = "SELECT transaction_number, product_name, designer, level, amount, created_at, username "
                + "FROM transaction "
                + "WHERE transaction_number = ?";

        ModelReceiptData receiptData = null;
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet r = null;
        try {
            conn = getConnection();
            p = conn.prepareStatement(query);

            // Set parameter
            p.setString(1, transactionNumber);

            // Execute query
            r = p.executeQuery();

            // Process result
            if (r.next()) {
                String transactionNum = r.getString("transaction_number");
                String productName = r.getString("product_name");
                String designer = r.getString("designer");
                String level = r.getString("level");
                double amount = r.getDouble("amount");
                Timestamp createdAt = r.getTimestamp("created_at");
                String username = r.getString("username");

                receiptData = new ModelReceiptData(transactionNum, productName, designer, level, amount, createdAt, username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receiptData;
    }

    public String getRecipientEmail(String transactionNumber) {
        String username = getUsername(transactionNumber);
        return getEmailByUsername(username);
    }

    private String getUsername(String transactionNumber) {
        String username = null;
        String queryUsername = "SELECT username FROM transaction WHERE transaction_number = ?";
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet r = null;
        try {
            conn = getConnection();
            p = conn.prepareStatement(queryUsername);
            p.setString(1, transactionNumber);
            r = p.executeQuery();
            if (r.next()) {
                username = r.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Tutup ResultSet, PreparedStatement, dan Connection secara manual
            try {
                if (r != null) {
                    r.close();
                }
                if (p != null) {
                    p.close();
                }
                // Hapus penutupan koneksi
                // if (conn != null) {
                //     conn.close();
                // }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return username;
    }

    private String getEmailByUsername(String username) {
        String recipientEmail = null;
        String queryEmail = "SELECT email FROM user WHERE username = ?";
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet r = null;
        try {
            conn = getConnection();
            p = conn.prepareStatement(queryEmail);
            p.setString(1, username);
            r = p.executeQuery();
            if (r.next()) {
                recipientEmail = r.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Tutup ResultSet, PreparedStatement, dan Connection secara manual
            try {
                if (r != null) {
                    r.close();
                }
                if (p != null) {
                    p.close();
                }
                // Hapus penutupan koneksi
                // if (conn != null) {
                //     conn.close();
                // }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return recipientEmail;
    }

    public static void insertResult(String transactionNumber, List<File> files) {
        PreparedStatement p = null;
        Connection connection = null;
        try {
            connection = getConnection();
            // Menentukan tipe data yang akan ditambahkan
            for (File file : files) {
                // Dapatkan ekstensi file
                String extension = getFileExtension(file);

                if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png")) {
                    // Jika file adalah gambar

                    // SQL query untuk memasukkan data ke dalam tabel 'resultImage'
                    String sql = "INSERT INTO resultimage (transaction_number, result) VALUES (?, ?)";

                    // Siapkan statement
                    p = connection.prepareStatement(sql);
                    p.setString(1, transactionNumber); // Set parameter transaction_number

                    // Baca file gambar dan set sebagai binary stream
                    try (FileInputStream fis = new FileInputStream(file)) {
                        p.setBinaryStream(2, fis, (int) file.length()); // Set parameter result
                        // Eksekusi query SQL
                        p.executeUpdate();
                    }

                } else if (extension.equalsIgnoreCase("mp4") || extension.equalsIgnoreCase("avi")) {
                    // Jika file adalah video
                    // SQL query untuk memasukkan data ke dalam tabel 'resultVideo'
                    String sql = "INSERT INTO result (transaction_number, result) VALUES (?, ?)";

                    // Siapkan statement
                    p = connection.prepareStatement(sql);
                    p.setString(1, transactionNumber); // Set parameter transaction_number

                    // Baca file video dan set sebagai binary stream
                    try (FileInputStream fis = new FileInputStream(file)) {
                        p.setBinaryStream(2, fis, (int) file.length()); // Set parameter result
                        // Eksekusi query SQL
                        p.executeUpdate();
                    }

                } else {
                    System.out.println("Unsupported file format.");
                    return;
                }
            }

            // Tampilkan pesan sukses
            System.out.println("Data berhasil disimpan ke dalam tabel.");

            // Update status transaksi menjadi "Pending"
            String updateSql = "UPDATE transaction SET status = ? WHERE transaction_number = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                updateStatement.setString(1, "Pending");
                updateStatement.setString(2, transactionNumber);
                updateStatement.executeUpdate();
            }

//            // Kirim notifikasi email
//            MailNotification mailNotif = new MailNotification();
//            mailNotif.sendNotification(recipientEmail, "Notification", "Your order with number " + transactionNumber + " has been sent by the designer.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFileExtension(File file) {
        // Dapatkan ekstensi file
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            return fileName.substring(index + 1);
        }
        return "";
    }

//    private static File combineImagesToZip(List<File> imageFiles) throws IOException {
//        // File zip yang akan disimpan di direktori sementara
//        File zipFile = File.createTempFile("combinedImages", ".zip");
//
//        // Membuat ZIP output stream
//        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
//            // Iterasi melalui daftar file gambar
//            for (File imageFile : imageFiles) {
//                // Pastikan file adalah gambar (dengan ekstensi .jpg atau .png)
//                if (imageFile.getName().endsWith(".jpg") || imageFile.getName().endsWith(".png")) {
//                    // Membuat entri ZIP untuk setiap gambar
//                    ZipEntry entry = new ZipEntry(imageFile.getName());
//                    zos.putNextEntry(entry);
//
//                    // Baca file gambar dan tulis ke ZIP output stream
//                    try (FileInputStream fis = new FileInputStream(imageFile)) {
//                        byte[] buffer = new byte[1024];
//                        int bytesRead;
//                        while ((bytesRead = fis.read(buffer)) != -1) {
//                            zos.write(buffer, 0, bytesRead);
//                        }
//                    }
//                }
//            }
//        }
//        return zipFile;
//    }
}
