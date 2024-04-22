package Test.DisplayImage.Main;

import Test.DisplayImage.Service.ServiceImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.util.List;
import javax.swing.ImageIcon;

public class DisplayImageFilePreview {

    private JFrame frame;
    private JLabel imageLabel;
    private final String transactionNumber;
    private final String watermarkPath = "D:\\Zaky\\NeatBeans Project\\flyapp\\src\\Test\\DisplayImage\\Main\\WatermarkImage.png";
    private List<ImageIcon> images; // List to hold multiple ImageIcons
    private int currentIndex = 0; // Current index of the displayed image

    public DisplayImageFilePreview(String transactionNumber) {
        this.transactionNumber = transactionNumber;
        images = new ArrayList<>();
        initializeFrame();
        loadImagesFromDatabase();
        displayCurrentImage();
    }

    // Method to initialize the frame
    private void initializeFrame() {
        frame = new JFrame();
        frame.setUndecorated(true); // Set frame undecorated
        frame.setSize(1280, 720); // Set frame size
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create a panel for layout
        JPanel panel = new JPanel(new BorderLayout());

        // Initialize imageLabel
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(1280, 720));
        panel.add(imageLabel, BorderLayout.CENTER);

        // Add close button
        JButton closeButton = new JButton("X");
        closeButton.setForeground(Color.RED);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the frame
            }
        });
        JPanel closePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closePanel.add(closeButton);
        panel.add(closePanel, BorderLayout.NORTH);

        // Create navigation buttons
        JButton leftButton = new JButton("<");
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigateImages(-1); // Navigate left
            }
        });
        JButton rightButton = new JButton(">");
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigateImages(1); // Navigate right
            }
        });
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        navPanel.add(leftButton);
        navPanel.add(rightButton);
        panel.add(navPanel, BorderLayout.SOUTH);

        // Add panel to frame
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    // Method to load images from database
    private void loadImagesFromDatabase() {
        // Retrieve the list of byte[] images from the database
        List<byte[]> imageBytesList = ServiceImage.getImagesFromDatabase(transactionNumber);

        // Initialize the list to hold ImageIcon objects
        images = new ArrayList<>();

        // Check if the list of images is empty
        if (imageBytesList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No images found in the database.");
        } else {
            // Convert each byte[] array to an ImageIcon and add it to the list
            for (byte[] imageBytes : imageBytesList) {
                ImageIcon imageIcon = new ImageIcon(imageBytes);
                images.add(imageIcon);
            }
        }
    }

    // Method to display the current image with watermark
    public void displayCurrentImage() {
        if (images == null || images.isEmpty()) {
            return;
        }

        // Get the current ImageIcon
        ImageIcon currentIcon = images.get(currentIndex);

        // Resize and add watermark
        int frameWidth = frame.getContentPane().getWidth();
        int frameHeight = frame.getContentPane().getHeight();

        // Scale the image to fit the frame
        Image mainImage = currentIcon.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);

        try {
            // Load the watermark image
            Image watermarkImage = ImageIO.read(new File(watermarkPath));

            // Resize the watermark
            watermarkImage = scaleWatermark(mainImage, watermarkImage);

            // Combine the main image and the watermark
            Image combinedImage = drawWatermark(mainImage, watermarkImage);

            // Set the combined image as the icon for the imageLabel
            imageLabel.setIcon(new ImageIcon(combinedImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Navigate through images based on the direction provided
    private void navigateImages(int direction) {
        if (images != null && !images.isEmpty()) {
            // Update the current index based on the direction (-1 for left, 1 for right)
            currentIndex = (currentIndex + direction + images.size()) % images.size();

            // Display the current image
            displayCurrentImage();
        }
    }

    // Method to scale the watermark based on the main image size
    private Image scaleWatermark(Image mainImage, Image watermarkImage) {
        int mainImageWidth = mainImage.getWidth(null);
        int mainImageHeight = mainImage.getHeight(null);

        // Calculate the desired size of the watermark
        int watermarkWidth = (int) (mainImageWidth * 0.2);
        int watermarkHeight = (int) (watermarkWidth * ((double) watermarkImage.getHeight(null) / watermarkImage.getWidth(null)));

        // Scale the watermark to the desired size
        return watermarkImage.getScaledInstance(watermarkWidth, watermarkHeight, Image.SCALE_SMOOTH);
    }

    // Method to combine the main image and watermark
    private Image drawWatermark(Image mainImage, Image watermarkImage) {
        BufferedImage combinedImage = new BufferedImage(mainImage.getWidth(null), mainImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = combinedImage.createGraphics();

        // Draw the main image
        g2d.drawImage(mainImage, 0, 0, null);

        // Define the position of the watermark
        int watermarkX = combinedImage.getWidth() - watermarkImage.getWidth(null) - 10; // Right side with some margin
        int watermarkY = combinedImage.getHeight() - watermarkImage.getHeight(null) - 10; // Bottom side with some margin

        // Draw the watermark
        g2d.drawImage(watermarkImage, watermarkX, watermarkY, null);
        g2d.dispose();
        return combinedImage;
    }

    public static void main(String[] args) {
        // Initialize the display with the transaction number
        SwingUtilities.invokeLater(() -> new DisplayImageFilePreview("TRS-5840").displayCurrentImage());
    }
}
