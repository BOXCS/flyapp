package User.Cart.Swing;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CheckboxRender implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof String) {
            String statusString = (String) value;
            boolean status = statusString.equals("true"); // Ubah String menjadi nilai Boolean
            return table.getDefaultRenderer(Boolean.class).getTableCellRendererComponent(table, status, isSelected, hasFocus, row, column);
        } else {
            return table.getDefaultRenderer(Boolean.class).getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

}
