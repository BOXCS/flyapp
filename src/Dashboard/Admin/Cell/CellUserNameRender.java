package Dashboard.Admin.Cell;

import Dashboard.Admin.Model.ModelName;
import com.raven.table.cell.Cell;

public class CellUserNameRender extends Cell {

    public CellUserNameRender(ModelName data) {
        initComponents();
        lb.setText(data.toString());
        image.setImage(data.getProfile());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        image = new Dashboard.Swing.ImageAvatar();
        lb = new javax.swing.JLabel();

        image.setBorderSize(2);
        image.setBorderSpace(1);
        image.setGradientColor1(new java.awt.Color(63, 109, 217));
        image.setGradientColor2(new java.awt.Color(199, 42, 42));
        image.setImage(new javax.swing.ImageIcon(getClass().getResource("/Dashboard/icon/BG.png"))); // NOI18N
        image.setPreferredSize(new java.awt.Dimension(0, 0));

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText("Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboard.Swing.ImageAvatar image;
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
