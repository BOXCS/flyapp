package Admin.ViewOrder.Swing.CellLate;

import Admin.ViewOrder.Swing.CellCancelled.*;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableLateCellEditor extends DefaultCellEditor {
    
    private TableActionLateEvent event;

    public TableLateCellEditor(TableActionLateEvent event) {
        super(new JCheckBox());
        this.event= event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        CellActionLate action = new CellActionLate();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
    
    

}

