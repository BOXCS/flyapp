package User.Cart.Swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomBooleanRenderer extends DefaultTableCellRenderer {

    // Konstruktor
    public CustomBooleanRenderer() {
        super();
        setHorizontalAlignment(CENTER);
    }

    // Mengatur tampilan sel berdasarkan nilai boolean
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Memanggil metode superclass
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        CheckboxCustom checkBox = new CheckboxCustom();
        checkBox.setSelected((Boolean) value);
        checkBox.setHorizontalAlignment(CENTER); // Set posisi checkbox menjadi center

        // Jika sel tidak dipilih, atur latar belakang sel sesuai dengan latar belakang tabel
        if (!isSelected) {
            checkBox.setBackground(table.getBackground());
        }

        // Kembalikan komponen
        return checkBox;
    }
}
