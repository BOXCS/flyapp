package Test.DisplayImage.Main;

import Test.DisplayImage.Service.ServiceImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class DisplayImageFromDatabasePreview {

    private JFrame frame;
    private JLabel imageLabel;
    private final String transactionNumber;
    private List<ImageIcon> images; // List to hold multiple ImageIcons
    private int currentIndex = 0; // Current index of the displayed image
    private boolean imagesLoaded = false;
    private final String watermarkPath = "D:\\Zaky\\NeatBeans Project\\flyapp\\src\\Test\\DisplayImage\\Main\\WatermarkImage.png";
    private final int frameWidth = 1280;
    private final int frameHeight = 720;

    public DisplayImageFromDatabasePreview(String transactionNumber) {
        this.transactionNumber = transactionNumber;
        images = new ArrayList<>();
        initializeFrame();
    }

    // Method to initialize the frame
    private void initializeFrame() {
        frame = new JFrame();
        frame.setUndecorated(true); // Set frame undecorated
        frame.setSize(frameWidth, frameHeight); // Set frame size
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create a panel for layout
        JPanel panel = new JPanel(new BorderLayout());

        // Initialize imageLabel
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(frameWidth, frameHeight));
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
        // Display the frame after everything is set up
        frame.setVisible(true);

        // Load images after the frame is shown
        SwingUtilities.invokeLater(this::loadImagesFromDatabase);
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
                addWatermark(imageIcon); // Add watermark
                images.add(imageIcon);
            }

            // Display the current image after the images are loaded
            imagesLoaded = true;
            displayCurrentImage();
            // Display the number of loaded images
            System.out.println("Number of images loaded: " + images.size());
        }
    }

    // Method to add watermark to the image
    private void addWatermark(ImageIcon imageIcon) {
        try {
            Image image = imageIcon.getImage();
            BufferedImage bufferedImage = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.drawImage(image, 0, 0, frameWidth, frameHeight, null);

            // Load watermark image
            Image watermark = ImageIO.read(new File(watermarkPath));
            graphics2D.drawImage(watermark, bufferedImage.getWidth() - watermark.getWidth(null), 0, null);

            // Save the image with watermark
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            byte[] imageInByte = baos.toByteArray();
            imageIcon.setImage(Toolkit.getDefaultToolkit().createImage(imageInByte));
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to display the current image
    public void displayCurrentImage() {
        if (!imagesLoaded) {
            return;
        }

        if (images == null || images.isEmpty()) {
            return;
        }

        // Get the current ImageIcon
        ImageIcon currentIcon = images.get(currentIndex);

        // Resize the image to fit the frame
        Image mainImage = currentIcon.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);

        // Set the scaled image as the icon for the imageLabel
        imageLabel.setIcon(new ImageIcon(mainImage));
    }

    // Navigate through images based on the direction provided
    private void navigateImages(int direction) {
        if (!imagesLoaded) {
            return;
        }

        if (images != null && !images.isEmpty()) {
            // Update the current index based on the direction (-1 for left, 1 for right)
            currentIndex = (currentIndex + direction + images.size()) % images.size();

            // Display the current image
            displayCurrentImage();
        }
    }

    public static void main(String[] args) {
        // Initialize the display with the transaction number
        SwingUtilities.invokeLater(() -> new DisplayImageFromDatabasePreview("TRS-2437"));
    }
}
