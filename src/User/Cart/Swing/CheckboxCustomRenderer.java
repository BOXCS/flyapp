package User.Cart.Swing;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class CheckboxCustomRenderer extends DefaultTableCellRenderer {

    private final CheckboxCustom checkbox = new CheckboxCustom();

    public CheckboxCustomRenderer() {
        checkbox.setHorizontalAlignment(CENTER);
        checkbox.setBackground(new Color(0, 0, 0, 0)); // Set latar belakang menjadi transparan
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Jika kolom adalah kolom status, gunakan renderer khusus
        if (column == 4) {
            return checkbox;
        } else {
            // Jika bukan kolom status, gunakan renderer default
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}
