package LoginRegister.Component;

import LoginRegister.Model.ModelUser;
import LoginRegister.Service.ServiceForgetPassword;
import java.awt.Window;
import javax.swing.SwingUtilities;
import swing.Glass;

public class PanelForgotPassword extends javax.swing.JPanel {

    private ServiceForgetPassword serviceForgot;
    private ModelUser user;
    private Glass glass;

    public PanelForgotPassword() {
        initComponents();
        serviceForgot = new ServiceForgetPassword();
        glass = new Glass();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        icon = new javax.swing.JLabel();
        lbForgotPassword = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        txtEmail = new swing.MyTextField();
        btnSend = new swing.Button();
        cmdBacktoLogin = new swing.ButtonDash();

        roundPanel1.setBackground(new java.awt.Color(69, 67, 102));

        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LoginRegister/icon/Fly.png"))); // NOI18N

        lbForgotPassword.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lbForgotPassword.setForeground(new java.awt.Color(204, 204, 204));
        lbForgotPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbForgotPassword.setText("Forgot Password");

        lbEmail.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbEmail.setForeground(new java.awt.Color(204, 204, 204));
        lbEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEmail.setText("Email");

        btnSend.setBackground(new java.awt.Color(132, 132, 215));
        btnSend.setForeground(new java.awt.Color(255, 255, 255));
        btnSend.setText("Send");
        btnSend.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        cmdBacktoLogin.setForeground(new java.awt.Color(255, 255, 255));
        cmdBacktoLogin.setText("Back To Login");
        cmdBacktoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBacktoLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(cmdBacktoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(roundPanel1Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(lbForgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(roundPanel1Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(roundPanel1Layout.createSequentialGroup()
                            .addGap(90, 90, 90)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(328, Short.MAX_VALUE)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdBacktoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel1Layout.createSequentialGroup()
                    .addGap(0, 7, Short.MAX_VALUE)
                    .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(lbForgotPassword)
                    .addGap(12, 12, 12)
                    .addComponent(lbEmail)
                    .addGap(18, 18, 18)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 110, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        String recipientEmail = txtEmail.getText().trim();

        // Validasi Email
        if (isValidEmail(recipientEmail)) {
            // Kirim Email dengan Password yang diperoleh dari Database
            sendPasswordByEmail(recipientEmail);
        } else {
            // Jika email tidak valid
            System.out.println("Invalid Email");
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void cmdBacktoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBacktoLoginActionPerformed
        Window window = SwingUtilities.getWindowAncestor(this); // Mendapatkan window (dialog/frame) yang menaungi panel ini
        if (window != null) {
            window.setVisible(false); // Menutup dialog atau frame yang menaungi panel
        }
    }//GEN-LAST:event_cmdBacktoLoginActionPerformed

    private boolean isValidEmail(String email) {
        // Tambahkan logika validasi email di sini
        // Contoh sederhana: menggunakan regular expression untuk validasi
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailPattern);
    }

    private void sendPasswordByEmail(String recipientEmail) {
        String password = getPasswordByEmailFromDatabase(recipientEmail);

        // Kirim Email dengan Password yang diperoleh dari Database
        if (password != null && !password.isEmpty()) {
            serviceForgot.sendPasswordEmail(recipientEmail, password);
            System.out.println("Email sent with the password if it exists in the database.");
        } else {
            System.out.println("Email not found or password not available.");
        }
    }

    private String getPasswordByEmailFromDatabase(String recipientEmail) {
        // Memanggil metode untuk mendapatkan kata sandi dari database
        return serviceForgot.getPasswordByEmail(recipientEmail);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnSend;
    private swing.ButtonDash cmdBacktoLogin;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbForgotPassword;
    private Dashboard.Swing.RoundPanel roundPanel1;
    private swing.MyTextField txtEmail;
    // End of variables declaration//GEN-END:variables
}
