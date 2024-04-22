package Test.DisplayImage.Main;

import Test.DisplayImage.Service.ServiceImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DisplayImageFromDatabase {

    private JFrame frame;
    private JLabel imageLabel;
    private final String transactionNumber;

    public DisplayImageFromDatabase(String transactionNumber) {
        this.transactionNumber = transactionNumber;
        initializeFrame();
    }

    // Method untuk inisialisasi frame
    private void initializeFrame() {
        frame = new JFrame("Display Image From Database");
        frame.setSize(1280, 720); // Mengatur ukuran frame menjadi 1280x720
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Hanya menutup frame ini, tidak semua frame
        frame.setLocationRelativeTo(null);

        // Inisialisasi imageLabel dengan ukuran yang sesuai
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(1280, 720)); // Mengatur ukuran JLabel
        frame.getContentPane().add(imageLabel, BorderLayout.CENTER);

        // Menampilkan frame
        frame.setVisible(true);
    }

    // Metode untuk menampilkan gambar dari database berdasarkan transactionNumber
    public void displayImageFromDatabase() {
        // Mendapatkan gambar dari database menggunakan ServiceImage
        byte[] imageData = ServiceImage.getImageFromDatabase(transactionNumber);

        if (imageData != null) {
            // Membuat ImageIcon dari byte array
            ImageIcon imageIcon = new ImageIcon(imageData);
            // Mengambil image dari ImageIcon
            Image image = imageIcon.getImage();
            // Mengubah ukuran image agar sesuai dengan JLabel
            Image scaledImage = image.getScaledInstance(1280, 720, Image.SCALE_SMOOTH); // Mengatur resolusi gambar menjadi 1280x720
            // Membuat ImageIcon baru dengan image yang telah diubah ukurannya
            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

            // Set ImageIcon ke imageLabel
            imageLabel.setIcon(scaledImageIcon);
        } else {
            // Jika data gambar tidak ditemukan
            JOptionPane.showMessageDialog(null, "Image data not found in the database.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DisplayImageFromDatabase("TRS-2437").displayImageFromDatabase());
    }

}
