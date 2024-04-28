
package Admin.Report.Revision.Main;

import Admin.Report.Revision.Component.InfoTN;

public class ReportRevision extends javax.swing.JPanel {
    
    private InfoTN infoTN;
    private String transactionNumber;

    public ReportRevision() {
        initComponents();
        setOpaque(false);
        this.setLayout(null);
        
        setCustomLayout();
    }
    
    private void setCustomLayout() {
        scanTN1.setBounds(0, 25, scanTN1.getPreferredSize().width, scanTN1.getPreferredSize().height);
        infoTN1.setBounds(0, scanTN1.getY() + 200, scanTN1.getWidth(), infoTN1.getPreferredSize().height);
    }
    
    public void updateInfoTN(String transactionNumber) {
        infoTN.loadReportRevision(transactionNumber);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scanTN1 = new Admin.Report.Revision.Component.ScanTN(this, infoTN);
        infoTN1 = new Admin.Report.Revision.Component.InfoTN(transactionNumber);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoTN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scanTN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(761, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(scanTN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(infoTN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(157, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Admin.Report.Revision.Component.InfoTN infoTN1;
    private Admin.Report.Revision.Component.ScanTN scanTN1;
    // End of variables declaration//GEN-END:variables
}
