package video;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
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

public class VideoJava extends JFrame {

    private EmbeddedMediaPlayerComponent mediaPlayerComp;
    private final JButton pause;
    private final JButton skip;
    private final JButton rewind;

    public VideoJava(String videoFilePath) {
        setTitle("Video Player");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        new NativeDiscovery().discover();
        
        mediaPlayerComp = new EmbeddedMediaPlayerComponent();
        getContentPane().add(mediaPlayerComp, BorderLayout.CENTER);
        
        pause = new JButton("Pause/Play");
        skip = new JButton("Skip forward 5s");
        rewind = new JButton("rewind 5s");
        
        JPanel controlPanel = new JPanel();
        controlPanel.add(pause);
        controlPanel.add(skip);
        controlPanel.add(rewind);
        
        getContentPane().add(controlPanel, BorderLayout.SOUTH);
        
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComp.mediaPlayer().controls().pause();
            }
        });
        skip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComp.mediaPlayer().controls().skipTime(5000);
            }
        });
        rewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComp.mediaPlayer().controls().skipTime(-5000);
            }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComp.mediaPlayer().release();
            }
            
        });
        
        setVisible(true);
        
        playVideo(videoFilePath);
    }
    
    public void playVideo(String videoFilePath) {
        mediaPlayerComp.mediaPlayer().media().start(videoFilePath);
        mediaPlayerComp.mediaPlayer().controls().play();
    }
    
    public static void main(String[] args) {
        String videoFilePath = "D:\\Zaky\\NeatBeans Project\\flyapp\\src\\video\\WhatsApp Video 2023-08-20 at 16.43.33.mp4";
        SwingUtilities.invokeLater(() -> new VideoJava(videoFilePath));
    }
    
}
