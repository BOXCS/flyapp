package User.SeeOrder.Swing.CellWaiting;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableWaitingCellEditor extends DefaultCellEditor {

    private TableActionWaitingEvent event;
    
    public TableWaitingCellEditor(TableActionWaitingEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        CellActionWaiting action = new CellActionWaiting();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
    
}
