package Test.InsertResult;

import connection.DatabaseConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.Timestamp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import notif.Mail.MailNotification;

public class ServiceResult {

    private static Connection connection = null; // Menyimpan koneksi agar dapat digunakan kembali

    // Method untuk mendapatkan koneksi database
    private static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getInstance().getConnection();
        }
        return connection;
    }

    public static void insertResult(String transactionNumber, File selectedFile, String recipientEmail) {
        FileInputStream fis = null;
        try {
            // Mendapatkan ekstensi file
            String extension = getFileExtension(selectedFile);

            // Query untuk menyimpan data hasil seleksi file ke dalam tabel yang sesuai
            String sql = "";
            if (extension.equalsIgnoreCase("mp4") || extension.equalsIgnoreCase("avi")) {
                sql = "INSERT INTO result (transaction_number, result) VALUES (?, ?)";
            } else if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png")) {
                sql = "INSERT INTO resultImage (transaction_number, result) VALUES (?, ?)";
            } else {
                System.out.println("Unsupported file format.");
                return;
            }

            PreparedStatement p = getConnection().prepareStatement(sql);
            p.setString(1, transactionNumber); // Set parameter transaction_number

            // Membaca file yang dipilih dan menyimpannya sebagai blob
            fis = new FileInputStream(selectedFile);
            p.setBinaryStream(2, fis, (int) selectedFile.length()); // Set parameter result

            p.executeUpdate(); // Eksekusi query

            // Menampilkan pesan sukses
            System.out.println("Data berhasil disimpan ke dalam tabel.");

            // Kirim email notifikasi setelah menyimpan data
            MailNotification mailnotif = new MailNotification();
            mailnotif.sendNotification(recipientEmail, "Notification", "Your order with number " + transactionNumber + " has been sent by the designer.");

            p.close();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Menutup FileInputStream setelah selesai
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static byte[] getImageFromDatabase(String transactionNumber) throws SQLException, IOException {
        byte[] imageData = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ByteArrayOutputStream outputStream = null;

        try {
            String query = "SELECT result FROM resultImage WHERE transaction_number = ?";
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, transactionNumber);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Blob blob = resultSet.getBlob("result");
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    imageData = outputStream.toByteArray();
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                // Koneksi tidak ditutup di sini agar dapat digunakan kembali
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }

        return imageData;
    }

    public static byte[] getVideoFromDatabase(String transactionNumber) throws SQLException, IOException {
        byte[] videoData = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ByteArrayOutputStream outputStream = null;

        try {
            String query = "SELECT result FROM result WHERE transaction_number = ?";
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, transactionNumber);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Blob blob = resultSet.getBlob("result");
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    videoData = outputStream.toByteArray();
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                // Koneksi tidak ditutup di sini agar dapat digunakan kembali
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }

        return videoData;
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOfDot = name.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return "";
        }
        return name.substring(lastIndexOfDot + 1);
    }

    public static byte[] retrieveFileDataFromDatabase(String transactionNumber, String productType) {
        byte[] fileData = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ByteArrayOutputStream outputStream = null;

        try {
            String query = "";
            if (productType.equalsIgnoreCase("Video Editing")) {
                query = "SELECT result FROM result WHERE transaction_number = ?";
            } else if (productType.equalsIgnoreCase("Design Graphic") || productType.equalsIgnoreCase("3D Modelling")) {
                query = "SELECT result FROM resultImage WHERE transaction_number = ?";
            } else {
                System.out.println("Unsupported product type.");
                return null;
            }

            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, transactionNumber);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Blob blob = resultSet.getBlob("result");
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    fileData = outputStream.toByteArray();
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                // Koneksi tidak ditutup di sini agar dapat digunakan kembali
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }

        return fileData;
    }

    public static String getUsernameFromTransaction(String transactionNumber) {
        String username = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT username FROM transaction WHERE transaction_number = ?";
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, transactionNumber);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                username = resultSet.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return username;
    }

    public static String getEmailFromUsername(String username) {
        String email = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT email FROM user WHERE username = ?";
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                email = resultSet.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return email;
    }

    public void checkIfLateAndUpdateStatus(String transactionNumber) {
        // Dapatkan tanggal sekarang
        Date now = new Date();

        try {
            // Query untuk mendapatkan created_at, status, product_name, dan level berdasarkan transactionNumber
            String sql = "SELECT created_at, status, product_name, level FROM transaction WHERE transaction_number = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, transactionNumber);

            // Eksekusi query
            ResultSet r = p.executeQuery();

            // Jika hasil query tersedia
            if (r.next()) {
                Timestamp createdAt = r.getTimestamp("created_at");
                String status = r.getString("status");
                String productName = r.getString("product_name");
                String level = r.getString("level");

                // Hitung batas keterlambatan berdasarkan product_name dan level
                int lateDays = calculateLateDays(productName, level);

                // Hitung batas waktu terakhir berdasarkan created_at dan batas keterlambatan
                Calendar cal = Calendar.getInstance();
                cal.setTime(createdAt);
                cal.add(Calendar.DAY_OF_MONTH, lateDays);
                Date deadline = cal.getTime();

                // Periksa apakah sekarang melewati batas waktu terakhir dan status belum Late
                if (now.after(deadline) && !status.equals("Late")) {
                    // Ubah status transaksi menjadi Late
                    updateTransactionStatusToLate(transactionNumber);
                }
            }

            // Tutup resources
            r.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// Metode untuk menghitung batas keterlambatan berdasarkan product_name dan level
    private int calculateLateDays(String productName, String level) {
        int lateDays = 0;

        // Aturan keterlambatan berdasarkan product_name dan level
        if (productName.equals("Video Editing")) {
            switch (level) {
                case "Basic":
                    lateDays = 1;
                    break;
                case "Standard":
                    lateDays = 2;
                    break;
                case "Pro":
                    lateDays = 3;
                    break;
            }
        } else if (productName.equals("Design Graphic")) {
            switch (level) {
                case "Basic":
                    lateDays = 1;
                    break;
                case "Standard":
                    lateDays = 2;
                    break;
                case "Pro":
                    lateDays = 3;
                    break;
            }
        } else if (productName.equals("3D Modelling")) {
            switch (level) {
                case "Basic":
                    lateDays = 2;
                    break;
                case "Standard":
                    lateDays = 3;
                    break;
                case "Pro":
                    lateDays = 4;
                    break;
            }
        }

        return lateDays;
    }

// Metode existing untuk mengubah status transaksi menjadi Late
    public static void updateTransactionStatusToLate(String transactionNumber) {
        try {
            // Query untuk memperbarui status transaksi menjadi 'Late'
            String sql = "UPDATE transaction SET status = 'Late' WHERE transaction_number = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, transactionNumber);

            // Eksekusi query
            p.executeUpdate();

            // Tutup PreparedStatement
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
