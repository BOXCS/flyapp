package User.Cart.Swing.CellStatus;

import User.Cart.Swing.CheckboxCustom;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;

public class TableStatusCellEditor extends DefaultCellEditor {

    private TableStatusEvent event;

    public TableStatusCellEditor(TableStatusEvent event) {
        super(new CheckboxCustom());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        CheckboxCustom checkBox = (CheckboxCustom) super.getTableCellEditorComponent(table, value, isSelected, row, column);
        checkBox.setSelected((Boolean) value);
        CellStatus action = new CellStatus();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return checkBox;
    }

}
