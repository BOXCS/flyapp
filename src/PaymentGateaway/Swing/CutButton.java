package PaymentGateaway.Swing;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class CutButton extends JButton {

    private Shape shape;

    public CutButton(String label) {
        super(label);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillPolygon(new int[]{0, getWidth(), getWidth(), 0},
                new int[]{0, 0, getHeight(), getHeight()}, 4);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getForeground());
        g2.drawPolygon(new int[]{0, getWidth(), getWidth(), 0},
                new int[]{0, 0, getHeight(), getHeight()}, 4);
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Polygon(new int[]{0, getWidth(), getWidth(), 0},
                    new int[]{0, 0, getHeight(), getHeight()}, 4);
        }
        return shape.contains(x, y);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cut Button Example");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        CutButton leftButton = new CutButton("Left Button");
        CutButton rightButton = new CutButton("Right Button");
        panel.add(leftButton);
        panel.add(rightButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
