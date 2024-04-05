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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ServiceResult {

    private static Connection connection = null; // Menyimpan koneksi agar dapat digunakan kembali

    // Method untuk mendapatkan koneksi database
    private static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DatabaseConnection.getInstance().getConnection();
        }
        return connection;
    }

    public static void insertResult(String transactionNumber, File selectedFile) {
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

}
