package Dashboard.Designer.Component;

import Dashboard.Designer.Profile.Service.ServiceProfile;
import connection.DatabaseConnection;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class VideoPortfolio extends JFrame {

    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private final JButton pauseButton;
    private final JButton skipForwardButton;
    private final JButton skipBackwardButton;
    private final String portfolioId;
    private ServiceProfile serviceProfle;

    public VideoPortfolio(String portfolioId) {
        this.portfolioId = portfolioId;
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Hanya menutup frame ini, tidak semua frame
        setLocationRelativeTo(null);

        // Mencari lokasi instalasi VLC
        new uk.co.caprica.vlcj.factory.discovery.NativeDiscovery().discover();

        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        getContentPane().add(mediaPlayerComponent, BorderLayout.CENTER);

        // Membuat tombol-tombol kontrol
        pauseButton = new JButton("Play/Pause");
        skipForwardButton = new JButton("Skip Forward 5s");
        skipBackwardButton = new JButton("Skip Backward 5s");

        JPanel controlPanel = new JPanel();
        controlPanel.add(pauseButton);
        controlPanel.add(skipBackwardButton);
        controlPanel.add(skipForwardButton);

        getContentPane().add(controlPanel, BorderLayout.SOUTH);

        // Mengatur aksi untuk tombol-tombol kontrol
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.mediaPlayer().controls().pause();
            }
        });

        skipForwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.mediaPlayer().controls().skipTime(5000); // Skip 5 detik ke depan
            }
        });

        skipBackwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.mediaPlayer().controls().skipTime(-5000); // Skip 5 detik ke belakang
            }
        });

        // Tambahkan window listener untuk menangani penutupan frame
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Panggil release() untuk melepaskan sumber daya media player
                mediaPlayerComponent.mediaPlayer().release();
            }
        });

        setVisible(true);

        // Mulai memainkan video dari database ketika frame dibuat
        playVideoFromDatabase();
    }

    public void playVideoFromDatabase() {
        // Mendapatkan file video dari database
        byte[] videoData = null;
        try {
            videoData = serviceProfle.getVideoFromDatabase(portfolioId);
        } catch (SQLException ex) {
            Logger.getLogger(VideoPortfolio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VideoPortfolio.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Memainkan video dari data byte array
        if (videoData != null) {
            playVideo(videoData);
        } else {
            System.err.println("Video data not found in the database.");
        }
    }

    public void playVideo(byte[] videoData) {
        try {
            // Create a temporary file
            File tempFile = File.createTempFile("temp_video", ".mp4");

            // Write the byte array data to the temporary file
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(videoData);
            }

            // Play the video from the temporary file
            mediaPlayerComponent.mediaPlayer().media().start(tempFile.getAbsolutePath());
            mediaPlayerComponent.mediaPlayer().controls().play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
