package User.SeeOrder.Swing.Cell;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class IconTableCellRenderer extends DefaultTableCellRenderer {

    private Icon revisionIcon;

    public IconTableCellRenderer() {
        // Load icon from file
        revisionIcon = new ImageIcon("revision.png");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Get the default table cell renderer
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Check if the current column is the "Revision" column
        if (column == table.getColumnModel().getColumnIndex("Revision")) {
            // Set the icon for the "Revision" column
            setIcon(revisionIcon);
            setText(""); // Clear text
            setHorizontalAlignment(SwingConstants.CENTER); // Center the icon horizontally
        }

        return cellComponent;
    }
}
