package Dashboard.Designer.SeeOrder.Swing.CellWaiting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellActionWaiting extends javax.swing.JPanel {

    public CellActionWaiting() {
        initComponents();
    }
    
    public void initEvent(TableActionWaitingEvent event, int row) {
        cmdApprove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onApprove(row);
            }
        });
        cmdCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onCancel(row);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdApprove = new User.SeeOrder.Swing.ButtonDash();
        cmdCancel = new User.SeeOrder.Swing.ButtonDash();

        cmdApprove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard/Designer/SeeOrder/icon/check.png"))); // NOI18N

        cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard/Designer/SeeOrder/icon/cancel.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdApprove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdApprove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private User.SeeOrder.Swing.ButtonDash cmdApprove;
    private User.SeeOrder.Swing.ButtonDash cmdCancel;
    // End of variables declaration//GEN-END:variables
}
