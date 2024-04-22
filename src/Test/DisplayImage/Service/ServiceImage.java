package Test.DisplayImage.Service;

import connection.DatabaseConnection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceImage {

    // Method untuk mendapatkan koneksi database
    private static Connection getConnection() {
        return DatabaseConnection.getInstance().getConnection(); // Tangani eksepsi dengan memberikan pesan kesalahan atau melakukan tindakan pemulihan
    }

    // Method untuk menampilkan gambar dari database berdasarkan transactionNumber
    public static byte[] getImageFromDatabase(String transactionNumber) {
        byte[] imageData = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ByteArrayOutputStream outputStream = null;

        try {
            connection = getConnection();
            if (connection != null) {
                String query = "SELECT result FROM resultimage WHERE transaction_number = ?";
                preparedStatement = connection.prepareStatement(query);
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
            } else {
                System.err.println("Database connection is null.");
            }
        } catch (SQLException | IOException e) {
            // Tangani eksepsi dengan memberikan pesan kesalahan atau melakukan tindakan pemulihan
            e.printStackTrace();
        } finally {
            // Tutup semua sumber daya dengan aman
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (SQLException | IOException e) {
                // Tangani eksepsi dengan memberikan pesan kesalahan atau melakukan tindakan pemulihan
                e.printStackTrace();
            }
        }

        return imageData;
    }

    // Method to retrieve multiple images from the database based on the transaction number
    public static List<byte[]> getImagesFromDatabase(String transactionNumber) {
        List<byte[]> imagesData = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            if (connection != null) {
                String query = "SELECT result FROM resultimage WHERE transaction_number = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, transactionNumber);
                resultSet = preparedStatement.executeQuery();

                // Iterate through each result and read the image data
                while (resultSet.next()) {
                    Blob blob = resultSet.getBlob("result");
                    if (blob != null) {
                        InputStream inputStream = blob.getBinaryStream();
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        imagesData.add(outputStream.toByteArray());
                        outputStream.close();
                    }
                }
            } else {
                System.err.println("Database connection is null.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            // Safely close all resources
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

        // Return the list of image data
        return imagesData;
    }
}
