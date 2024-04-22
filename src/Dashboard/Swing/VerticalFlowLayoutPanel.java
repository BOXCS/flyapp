package Dashboard.Swing;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class VerticalFlowLayoutPanel extends JPanel {

    public VerticalFlowLayoutPanel() {
        // Membuat panel dengan FlowLayout yang diubah agar vertikal
        setLayout(new FlowLayout(FlowLayout.LEADING) {
            @Override
            public void layoutContainer(java.awt.Container target) {
                super.layoutContainer(target);

                // Mengubah orientasi layout menjadi vertikal
                // Dengan cara mengatur orientasi komponen dengan metode layouting horizontal
                for (int i = 0; i < target.getComponentCount(); i++) {
                    java.awt.Component comp = target.getComponent(i);
                    comp.setBounds(comp.getX(), comp.getY(), comp.getWidth(), comp.getHeight());
                }
            }
        });
    }

}
