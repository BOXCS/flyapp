package Admin.ViewOrder.Swing.CellCancelled;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableCancelledCellEditor extends DefaultCellEditor {
    
    private TableActionCancelledEvent event;

    public TableCancelledCellEditor(TableActionCancelledEvent event) {
        super(new JCheckBox());
        this.event= event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        CellActionCancelled action = new CellActionCancelled();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
    
    

}

