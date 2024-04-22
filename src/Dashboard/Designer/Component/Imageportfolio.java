package Dashboard.Designer.Component;

import Dashboard.Designer.Profile.Service.ServiceProfile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Imageportfolio {

    private JFrame frame;
    private JLabel imageLabel;
    private final String portfolioId;
    private final String watermarkPath = "D:\\Zaky\\NeatBeans Project\\flyapp\\src\\Test\\DisplayImage\\Main\\WatermarkImage.png";

    public Imageportfolio(String portfolioId) {
        this.portfolioId = portfolioId;
        initializeFrame();
    }

    // Method untuk inisialisasi frame
    private void initializeFrame() {
        // Membuat frame undecorated
        frame = new JFrame();
        frame.setUndecorated(true); // Set frame undecorated
        frame.setSize(1280, 720); // Mengatur ukuran frame menjadi 1280x720
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Hanya menutup frame ini, tidak semua frame
        frame.setLocationRelativeTo(null);

        // Membuat panel untuk mengatur tata letak gambar dan watermark
        JPanel panel = new JPanel(new BorderLayout());

        // Inisialisasi imageLabel dengan ukuran yang sesuai
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(1280, 720)); // Mengatur ukuran JLabel

        // Menambahkan label gambar ke panel
        panel.add(imageLabel, BorderLayout.CENTER);

        // Menambahkan tombol close di kanan atas frame
        JButton closeButton = new JButton("X");
        closeButton.setForeground(Color.RED);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Menutup frame saat tombol ditutup
            }
        });
        JPanel closePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closePanel.add(closeButton);
        panel.add(closePanel, BorderLayout.NORTH);

        // Menambahkan panel ke frame
        frame.getContentPane().add(panel);

        // Menampilkan frame
        frame.setVisible(true);
    }

    // Metode untuk menampilkan gambar dari database berdasarkan transactionNumber
    public void displayImageFromDatabase() {
        try {
            // Mendapatkan gambar dari database menggunakan ServiceImage
            byte[] imageData = ServiceProfile.getVideoFromDatabase(portfolioId);
            
            if (imageData != null) {
                // Membuat ImageIcon dari byte array
                ImageIcon imageIcon = new ImageIcon(imageData);
                
                // Mendapatkan ukuran frame JFrame
                int frameWidth = frame.getContentPane().getWidth();
                int frameHeight = frame.getContentPane().getHeight();
                
                // Mengubah ukuran gambar utama agar sesuai dengan ukuran frame JFrame
                Image mainImage = imageIcon.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(mainImage);
                
                // Set ImageIcon ke imageLabel
                imageLabel.setIcon(imageIcon);
                
                try {
                    // Memuat gambar watermark
                    Image watermarkImage = ImageIO.read(new File(watermarkPath));
                    // Mengatur ulang ukuran watermark agar sesuai dengan gambar utama
                    watermarkImage = scaleWatermark(mainImage, watermarkImage);
                    // Menggambar watermark di atas gambar utama
                    Image combinedImage = drawWatermark(mainImage, watermarkImage);
                    // Membuat ImageIcon dari gabungan gambar dan watermark
                    ImageIcon combinedIcon = new ImageIcon(combinedImage);
                    // Set ImageIcon ke imageLabel
                    imageLabel.setIcon(combinedIcon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Jika data gambar tidak ditemukan
                JOptionPane.showMessageDialog(null, "Image data not found in the database.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Imageportfolio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Imageportfolio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method untuk menyesuaikan ukuran watermark dengan gambar utama
    private Image scaleWatermark(Image mainImage, Image watermarkImage) {
        // Mendapatkan ukuran gambar utama
        int mainImageWidth = mainImage.getWidth(null);
        int mainImageHeight = mainImage.getHeight(null);

        // Mendapatkan ukuran yang diinginkan untuk watermark
        int watermarkWidth = (int) (mainImageWidth * 1.2); // Ubah nilai 0.2 sesuai dengan proporsi yang diinginkan
        int watermarkHeight = (int) (watermarkWidth * ((double) watermarkImage.getHeight(null) / watermarkImage.getWidth(null)));

        // Mengatur ulang ukuran watermark agar sesuai dengan ukuran yang diinginkan
        return watermarkImage.getScaledInstance(watermarkWidth, watermarkHeight, Image.SCALE_SMOOTH);
    }

    // Method untuk menggabungkan gambar utama dan watermark
    private Image drawWatermark(Image mainImage, Image watermarkImage) {
        BufferedImage combinedImage = new BufferedImage(mainImage.getWidth(null), mainImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = combinedImage.createGraphics();
        // Gambar gambar utama
        g2d.drawImage(mainImage, 0, 0, null);
        // Mengatur posisi watermark agar lebih ke kiri
        int watermarkX = -130; // Ubah nilai sesuai preferensi Anda
        int watermarkY = -110;
        g2d.drawImage(watermarkImage, watermarkX, watermarkY, null);
        g2d.dispose();
        return combinedImage;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Imageportfolio("6").displayImageFromDatabase());
    }
}
