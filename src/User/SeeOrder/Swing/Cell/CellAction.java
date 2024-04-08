package User.SeeOrder.Swing.Cell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellAction extends javax.swing.JPanel {

    private boolean isRated = false;
    
    public CellAction() {
        initComponents();
    }

    public void initEvent(TableActionEvent event, int row) {
        cmdPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    event.onPlay(row);
            }
        });
        cmdRevision.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    event.onRevision(row);
            }
        });
        cmdRate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    event.onRate(row);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdPlay = new User.SeeOrder.Swing.ButtonDash();
        cmdRevision = new User.SeeOrder.Swing.ButtonDash();
        cmdRate = new User.SeeOrder.Swing.ButtonDash();

        cmdPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/SeeOrder/icon/play.png"))); // NOI18N

        cmdRevision.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/SeeOrder/icon/revision.png"))); // NOI18N

        cmdRate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/SeeOrder/icon/rating.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdPlay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdRevision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdPlay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdRevision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private User.SeeOrder.Swing.ButtonDash cmdPlay;
    private User.SeeOrder.Swing.ButtonDash cmdRate;
    private User.SeeOrder.Swing.ButtonDash cmdRevision;
    // End of variables declaration//GEN-END:variables
}
