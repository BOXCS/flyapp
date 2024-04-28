package Dashboard.Designer.SeeOrder.Swing.CellActive;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellActionActive extends javax.swing.JPanel {

    public CellActionActive() {
        initComponents();
    }
    
    public void initEvent(TableActionActiveEvent event, int row) {
        cmdReceipt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onReceipt(row);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdReceipt = new User.SeeOrder.Swing.ButtonDash();

        cmdReceipt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/SeeOrder/icon/revision.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private User.SeeOrder.Swing.ButtonDash cmdReceipt;
    // End of variables declaration//GEN-END:variables
}
