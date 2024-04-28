
package Admin.Report.Revision.Component;

public class RevisionPanel extends javax.swing.JPanel {

    public RevisionPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        revisionComp1 = new Admin.Report.Revision.Component.RevisionComp();
        jScrollPane1 = new javax.swing.JScrollPane();
        roundPanel1 = new Dashboard.Swing.RoundPanel();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));
        roundPanel1.setLayout(new java.awt.GridLayout(10, 0));
        jScrollPane1.setViewportView(roundPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private Admin.Report.Revision.Component.RevisionComp revisionComp1;
    private Dashboard.Swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
