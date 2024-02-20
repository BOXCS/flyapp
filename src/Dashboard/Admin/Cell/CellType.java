package Dashboard.Admin.Cell;

import com.raven.table.TableCustom;
import com.raven.table.cell.TableCustomCell;
import com.raven.table.model.TableRowData;

public class CellType extends TableCustomCell {

    public CellType() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jrVideo = new javax.swing.JRadioButton();
        jrDG = new javax.swing.JRadioButton();
        jr3D = new javax.swing.JRadioButton();

        jLabel1.setText("Type of Content :");

        buttonGroup1.add(jrVideo);
        jrVideo.setText("Video Editing");
        jrVideo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        buttonGroup1.add(jrDG);
        jrDG.setText("Design Graphic");

        buttonGroup1.add(jr3D);
        jr3D.setText("3D Modelling");

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
                            .addComponent(jrDG)
                            .addComponent(jrVideo)
                            .addComponent(jr3D))))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrVideo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrDG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jr3D)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void setData(Object o) {
        if (o.toString().equals("Video Editing")) {
            jrVideo.setSelected(true);
        } else if (o.toString().equals("Design Graphic")) {
            jrDG.setSelected(true);
        } else if (o.toString().equals("3D Modelling")) {
            jr3D.setSelected(true);
        } else {
        }
    }

    @Override
    public Object getData() {
        if (jrVideo.isSelected()) {
            return "Video Editing";
        } else if (jrDG.isSelected()) {
            return "Design Graphic";
        } else if (jr3D.isSelected()) {
            return "3D Modelling";
        } else {
            // Handle situasi lain jika diperlukan
            return null;
        }
    }

    @Override
    public TableCustomCell createComponentCellEditor(TableCustom tc, TableRowData trd, Object o, int i, int i1) {
        CellType cell = new CellType();
        cell.setData(o);
        return cell;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jr3D;
    private javax.swing.JRadioButton jrDG;
    private javax.swing.JRadioButton jrVideo;
    // End of variables declaration//GEN-END:variables
}
