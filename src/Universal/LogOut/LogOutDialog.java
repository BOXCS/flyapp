
package Universal.LogOut;

import Dashboard.Admin.DashboardAdmin;
import Dashboard.Designer.DashboardDesigner;
import Dashboard.User.DashboardUser;
import LoginRegister.Main;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class LogOutDialog extends javax.swing.JDialog {

    private final DashboardUser dashboardUser;
    private final DashboardDesigner dashboardDesigner;
    private final DashboardAdmin dashboardAdmin;
    private final Main mainFrame;
    private final JFrame parentFrame;

    public LogOutDialog(JFrame parent, Object dashboard, Main mainFrame) {
        super(parent, "Log Out", true);
        if (dashboard instanceof DashboardUser) {
            this.dashboardUser = (DashboardUser) dashboard;
            this.dashboardDesigner = null;
            this.dashboardAdmin = null;
        } else if (dashboard instanceof DashboardDesigner) {
            this.dashboardDesigner = (DashboardDesigner) dashboard;
            this.dashboardUser = null;
            this.dashboardAdmin = null;
        } else if (dashboard instanceof DashboardAdmin) {
            this.dashboardAdmin = (DashboardAdmin) dashboard;
            this.dashboardUser = null;
            this.dashboardDesigner = null;
        } else {
            throw new IllegalArgumentException("Invalid dashboard type");
        }
        this.mainFrame = new Main();
        this.parentFrame = parent; // Store the reference to the parent frame
        initComponents();
        setLocationRelativeTo(parent);
        setSize(400, 200); // Set the size of the dialog
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmdCancel = new swing.ButtonOutline();
        cmdLogOut = new swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(132, 132, 215));
        jLabel1.setText("LOG OUT");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Are You Sure Want To Log Out");

        cmdCancel.setForeground(new java.awt.Color(132, 132, 215));
        cmdCancel.setText("Cancel");
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });

        cmdLogOut.setBackground(new java.awt.Color(132, 132, 215));
        cmdLogOut.setForeground(new java.awt.Color(255, 255, 255));
        cmdLogOut.setText("Log Out");
        cmdLogOut.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmdLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(173, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        if (dashboardUser != null) {
            dashboardUser.menu.setSelected(0); // Pindah ke index 0 pada DashboardUser
        } else if (dashboardDesigner != null) {
            dashboardDesigner.menu.setSelected(0); // Pindah ke index 0 pada DashboardDesigner
        } else if (dashboardAdmin != null) {
            dashboardAdmin.menu.setSelected(0); // Pindah ke index 0 pada DashboardAdmin
        }
        dispose(); // Tutup dialog
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLogOutActionPerformed
        mainFrame.setVisible(true); // Tampilkan frame Main
        this.dispose(); // Tutup dialog
        parentFrame.dispose(); // Tutup parent frame untuk log out sepenuhnya
    }//GEN-LAST:event_cmdLogOutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.ButtonOutline cmdCancel;
    private swing.Button cmdLogOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private Dashboard.Swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
