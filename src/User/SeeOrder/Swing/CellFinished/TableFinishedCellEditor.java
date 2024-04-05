package User.SeeOrder.Swing.CellFinished;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableFinishedCellEditor extends DefaultCellEditor {

    private TableActionFinishedEvent event;
    
    public TableFinishedCellEditor(TableActionFinishedEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        CellActionFinished action = new CellActionFinished();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }

}
