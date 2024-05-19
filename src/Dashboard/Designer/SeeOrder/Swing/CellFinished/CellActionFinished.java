
package Dashboard.Designer.SeeOrder.Swing.CellFinished;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellActionFinished extends javax.swing.JPanel {

    public CellActionFinished() {
        initComponents();
    }
    
    public void initEvent(TableActionFinishedEvent event, int row) {
        cmdPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onPlay(row);
            }
        });
        cmdAddPort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onPortfolio(row);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdPlay = new swing.ButtonDash();
        cmdAddPort = new swing.ButtonDash();

        cmdPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/SeeOrder/icon/play.png"))); // NOI18N
        cmdPlay.setToolTipText("View Result");

        cmdAddPort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard/Designer/SeeOrder/icon/Plus.png"))); // NOI18N
        cmdAddPort.setToolTipText("Add to Portfolio");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdAddPort, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdAddPort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.ButtonDash cmdAddPort;
    private swing.ButtonDash cmdPlay;
    // End of variables declaration//GEN-END:variables
}
