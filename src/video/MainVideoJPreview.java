package video;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class MainVideoJPreview extends JFrame {

    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private final JButton pauseButton;
    private final JButton skipForwardButton;
    private final JButton skipBackwardButton;

    public MainVideoJPreview(String videoFilePath) {
        setTitle("Video Player");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menemukan lokasi instalasi VLC
        new NativeDiscovery().discover();

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

        // Mulai memainkan video dari file ketika frame dibuat
        playVideoFromFile(videoFilePath);
    }

    // Method untuk memainkan video dari file
    public void playVideoFromFile(String videoFilePath) {
        // Mainkan video dari file
        mediaPlayerComponent.mediaPlayer().media().start(videoFilePath);
        mediaPlayerComponent.mediaPlayer().controls().play();
    }

    public static void main(String[] args) {
        // Ganti "path/to/video.mp4" dengan path ke file video yang ingin Anda putar
        String videoFilePath = "video";
        SwingUtilities.invokeLater(() -> new MainVideoJPreview(videoFilePath));
    }
}
