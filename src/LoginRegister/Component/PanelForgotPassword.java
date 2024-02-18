package LoginRegister.Component;

import LoginRegister.Model.ModelUser;
import LoginRegister.Service.ServiceForgetPassword;
import java.awt.Window;
import javax.swing.SwingUtilities;

public class PanelForgotPassword extends javax.swing.JPanel {

    private ServiceForgetPassword serviceForgot;
    private ModelUser user;

    public PanelForgotPassword() {
        initComponents();
        serviceForgot = new ServiceForgetPassword();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        icon = new javax.swing.JLabel();
        lbForgotPassword = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        txtEmail = new swing.MyTextField();
        btnSend = new swing.Button();
        cmdBacktoLogin = new swing.ButtonDash();

        setBackground(new java.awt.Color(0, 0, 0));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(icon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbForgotPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 86, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(cmdBacktoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbForgotPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbEmail)
                .addGap(18, 18, 18)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdBacktoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
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
    private swing.MyTextField txtEmail;
    // End of variables declaration//GEN-END:variables
}
