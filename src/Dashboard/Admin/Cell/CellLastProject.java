package Dashboard.Admin.Cell;

import com.raven.table.TableCustom;
import com.raven.table.cell.TableCustomCell;
import com.raven.table.model.TableRowData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CellLastProject extends TableCustomCell {

    private String pathImage;
    
    public CellLastProject() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jdchooser = new com.toedter.calendar.JDateChooser();

        jLabel1.setText("Last Project :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(87, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jdchooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jdchooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void setData(Object o) {
        if (o instanceof String) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse((String) o);
                jdchooser.setDate(date);
            } catch (ParseException e) {
                e.printStackTrace(); // Atau penanganan kesalahan lainnya
            }
        }
    }

    @Override
    public Object getData() {
        Date selectedDate = jdchooser.getDate();
        return selectedDate;
    }

    @Override
    public TableCustomCell createComponentCellEditor(TableCustom tc, TableRowData trd, Object o, int i, int i1) {
        CellLastProject cell = new CellLastProject();
        cell.setData(o);
        return cell;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private com.toedter.calendar.JDateChooser jdchooser;
    // End of variables declaration//GEN-END:variables

    
}
