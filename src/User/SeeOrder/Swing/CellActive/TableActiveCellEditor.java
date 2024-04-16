package User.SeeOrder.Swing.CellActive;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActiveCellEditor extends DefaultCellEditor {

    private TableActionActiveEvent event;

    public TableActiveCellEditor(TableActionActiveEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        CellActionActive action = new CellActionActive();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
    
    
}
