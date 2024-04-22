package Dashboard.Designer.SendOrder.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DisplayImageFilePreview extends JPanel {

    private File file;

    public DisplayImageFilePreview(File file) {
        this.file = file;
        setPreferredSize(new Dimension(200, 200)); // Sesuaikan ukuran panel dengan kebutuhan

        // Jika file adalah gambar, tampilkan sebagai gambar
        if (isImageFile(file)) {
            displayImage(file);
        } // Jika file adalah video, tampilkan sebagai ikon video
        else if (isVideoFile(file)) {
            displayVideoIcon();
        } // Jika file bukan gambar atau video, tampilkan pesan kesalahan
        else {
            displayErrorMessage();
        }
    }

    private boolean isImageFile(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif");
    }

    private boolean isVideoFile(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return extension.equals("mp4") || extension.equals("avi") || extension.equals("mov") || extension.equals("wmv");
    }

    private void displayImage(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            if (image != null) {
                if (hasWatermark()) {
                    image = drawWatermark(image);
                }
                ImageIcon icon = new ImageIcon(image);
                JLabel label = new JLabel(icon);
                add(label);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayVideoIcon() {
        // Tampilkan ikon video
        JLabel label = new JLabel("Video Icon");
        add(label);
    }

    private void displayErrorMessage() {
        // Tampilkan pesan kesalahan
        JLabel label = new JLabel("Unsupported File Format");
        add(label);
    }

    private boolean hasWatermark() {
        // Cek apakah watermark tersedia
        File watermarkFile = new File("D:\\Zaky\\NeatBeans Project\\flyapp\\src\\Test\\DisplayImage\\Main\\WatermarkImage.png"); // Ganti dengan path ke file watermark Anda
        return watermarkFile.exists();
    }

    private BufferedImage drawWatermark(BufferedImage image) {
        try {
            File watermarkFile = new File("D:\\Zaky\\NeatBeans Project\\flyapp\\src\\Test\\DisplayImage\\Main\\WatermarkImage.png"); // Ganti dengan path ke file watermark Anda
            BufferedImage watermark = ImageIO.read(watermarkFile);

            Graphics2D g2d = image.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(watermark, 0, 0, image.getWidth(), image.getHeight(), null);
            g2d.dispose();

            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return image;
        }
    }
}
