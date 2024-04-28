package User.Cart.Swing.CellStatus;

import User.Cart.Swing.CheckboxCustom;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CellStatusRenderer extends DefaultTableCellRenderer {

    private final CheckboxCustom checkBoxRenderer;

    public CellStatusRenderer() {
        this.checkBoxRenderer = new CheckboxCustom();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Set nilai checkbox
        if (value instanceof Boolean) {
            checkBoxRenderer.setSelected((Boolean) value);
        } else {
            checkBoxRenderer.setSelected(false);
        }

        // Atur latar belakang sesuai dengan latar belakang baris
        if (isSelected) {
            checkBoxRenderer.setBackground(table.getSelectionBackground());
        } else {
            if (row % 2 == 0) {
                checkBoxRenderer.setBackground(Color.GREEN); // Latar belakang putih untuk baris genap
            } else {
                checkBoxRenderer.setBackground(table.getBackground()); // Latar belakang asli untuk baris ganjil
            }
        }

        return checkBoxRenderer;
    }

}
