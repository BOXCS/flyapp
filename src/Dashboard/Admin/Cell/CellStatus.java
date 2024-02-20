
package Dashboard.Admin.Cell;

import com.raven.table.TableCustom;
import com.raven.table.cell.TableCustomCell;
import com.raven.table.model.TableRowData;

public class CellStatus extends TableCustomCell {

    public CellStatus() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jrAvailable = new javax.swing.JRadioButton();
        jrUnavailable = new javax.swing.JRadioButton();

        jLabel1.setText("Status :");

        buttonGroup1.add(jrAvailable);
        jrAvailable.setText("Available");

        buttonGroup1.add(jrUnavailable);
        jrUnavailable.setText("Unavailable");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrUnavailable)
                            .addComponent(jrAvailable))))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrAvailable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrUnavailable)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void setData(Object o) {
        if (o.toString().equals("Available")) {
            jrAvailable.setSelected(true);
        } else if (o.toString().equals("Unavailable")) {
            jrUnavailable.setSelected(true);
        } else {
        }
    }

    @Override
    public Object getData() {
        if (jrAvailable.isSelected()) {
            return "Available";
        } else if (jrUnavailable.isSelected()) {
            return "Unavailable";
        } else {
            // Handle situasi lain jika diperlukan
            return null;
        }
    }

    @Override
    public TableCustomCell createComponentCellEditor(TableCustom tc, TableRowData trd, Object o, int i, int i1) {
        CellStatus cell = new CellStatus();
        cell.setData(o);
        return cell;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jrAvailable;
    private javax.swing.JRadioButton jrUnavailable;
    // End of variables declaration//GEN-END:variables

}
