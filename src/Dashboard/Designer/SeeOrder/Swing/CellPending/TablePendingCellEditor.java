package Dashboard.Designer.SeeOrder.Swing.CellPending;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TablePendingCellEditor extends DefaultCellEditor {

    private TableActionPendingEvent event;

    public TablePendingCellEditor(TableActionPendingEvent event) {
        super (new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        CellActionPending action = new CellActionPending();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
    
}
