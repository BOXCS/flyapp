package User.Revision.Main;

import User.PlaceOrder.Service.ServicePricing;
import User.Revision.Swing.EventRevision;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import raven.alerts.MessageAlerts;
import raven.popup.GlassPanePopup;

public class RevisionMain extends javax.swing.JFrame {

    private final ServicePricing servicePricing;
    private String transactionNumber;
    private String designerName;
    private String productName;
    private String level;

    public RevisionMain() throws SQLException {
        initComponents();
        init();
        servicePricing = new ServicePricing();

        // Set placeholder
        areaRevision.setForeground(Color.GRAY);
        areaRevision.setText("Provide us with detailed revision");
        areaRevision.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (areaRevision.getText().equals("Provide us with detailed revision")) {
                    areaRevision.setText("");
                    areaRevision.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (areaRevision.getText().isEmpty()) {
                    areaRevision.setForeground(Color.GRAY);
                    areaRevision.setText("Provide us with detailed revision");
                }
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }

    private void init() {
        GlassPanePopup.install(this);
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
        int remainingRevisions = servicePricing.getRemainingRevisionCount(transactionNumber);
        revisionCount.setText("Remaining revisions: " + remainingRevisions);
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        revisionCount = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaRevision = new javax.swing.JTextArea();
        cmdSend = new User.Revision.Swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Revision For Your Order");

        revisionCount.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        revisionCount.setForeground(new java.awt.Color(255, 255, 255));
        revisionCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        revisionCount.setText("remaining revision :");

        areaRevision.setColumns(20);
        areaRevision.setRows(5);
        jScrollPane1.setViewportView(areaRevision);

        cmdSend.setBackground(new java.awt.Color(132, 132, 215));
        cmdSend.setForeground(new java.awt.Color(255, 255, 255));
        cmdSend.setText("SEND");
        cmdSend.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmdSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(revisionCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(294, 294, 294)
                                .addComponent(cmdSend, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 29, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(revisionCount)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdSend, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSendActionPerformed
        String revisionArea = areaRevision.getText();

        try {
            // Ambil nilai remainingRevisions sebelum menambahkan revisi
            int remainingRevisions = servicePricing.getRemainingRevisionCount(transactionNumber);

            // Cek apakah masih ada revisi yang tersisa
            if (remainingRevisions > 0) {
                // Lakukan penambahan revisi
                servicePricing.insertNewRevision(transactionNumber, designerName, productName, level, revisionArea, remainingRevisions);

                // Tampilkan pesan berhasil
                MessageAlerts.getInstance().showMessage("Revision Success", "Wait for Update", MessageAlerts.MessageType.SUCCESS);

                // Update label revisionCount
                revisionCount.setText("Remaining revisions: " + (remainingRevisions - 1)); // Kurangi 1 karena telah ditambahkan revisi baru
            } else {
                // Tampilkan pesan bahwa revisi telah habis
                MessageAlerts.getInstance().showMessage("Revision Limit Reached", "No more revisions left.", MessageAlerts.MessageType.ERROR);
            }
        } catch (Exception e) {
            Logger.getLogger(RevisionMain.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_cmdSendActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RevisionMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RevisionMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RevisionMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RevisionMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RevisionMain revisionMain = new RevisionMain();
                    revisionMain.setVisible(true);
                    
                    revisionMain.cmdSend.requestFocusInWindow();
                } catch (SQLException ex) {
                    Logger.getLogger(RevisionMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaRevision;
    private User.Revision.Swing.Button cmdSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel revisionCount;
    // End of variables declaration//GEN-END:variables
}
