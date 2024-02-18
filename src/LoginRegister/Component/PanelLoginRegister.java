
package LoginRegister.Component;

import LoginRegister.Model.ModelLogin;
import LoginRegister.Model.ModelUser;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import swing.MyTextField;
import swing.MyPasswordField;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class PanelLoginRegister extends javax.swing.JLayeredPane {

    public ModelLogin getDataLogin() {
        return dataLogin;
    }

    /**
     * @return the user
     */
    public ModelUser getUser() {
        return user;
    }

    private ModelUser user;
    private ModelLogin dataLogin;
    
    public PanelLoginRegister(ActionListener eventRegister, ActionListener eventLogin) {
        initComponents();
        initRegister(eventRegister);
        initLogin(eventLogin);
        login.setVisible(false);
        register.setVisible(true);
    }
    
    private void initRegister(ActionListener eventRegister) {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));
        register.setBackground(new Color(3, 0, 45, 50));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(255, 255, 255));
        register.add(label);
        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/LoginRegister/icon/user.png")));
        txtUser.setHint("Username");
        register.add(txtUser, "w 60%");
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/LoginRegister/icon/mail.png")));
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/LoginRegister/icon/pass.png")));
        txtPass.setHint("Password");
        register.add(txtPass, "w 60%");
        JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setOpaque(false);
        showPasswordCheckBox.setForeground(Color.WHITE); // Ubah warna teks menjadi putih
        showPasswordCheckBox.setOpaque(false); // Menghilangkan latar belakang
        showPasswordCheckBox.setBorderPaintedFlat(true); // Menghilangkan latar belakang dan menampilkan kotak centang saja
        showPasswordCheckBox.addActionListener(e -> {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            txtPass.setEchoChar(checkBox.isSelected() ? '\u0000' : '•'); // Menyembunyikan atau menampilkan karakter kata sandi
        });

        register.add(showPasswordCheckBox, "w 60%, wrap");
        swing.Button cmd = new swing.Button();
        cmd.setBackground(new Color(132, 132, 215));
        cmd.setForeground(new Color(255, 255, 255));
        cmd.addActionListener(eventRegister);
        cmd.setText("SIGN UP");
        register.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = txtUser.getText().trim();
                String email = txtEmail.getText().trim();
                String password = String.valueOf(txtPass.getPassword());
                user = new ModelUser(password, userName, email, 0);
            }
        });
    }
    
    private void initLogin(ActionListener eventLogin) {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        login.setBackground(new Color(3, 0, 45, 50));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(255, 255, 255));
        login.add(label);
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/LoginRegister/icon/mail.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/LoginRegister/icon/pass.png")));
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");
        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(250, 250, 250));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdForget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelForgotPassword panelForgotPassword = new PanelForgotPassword();

                // Buat JDialog baru
                JDialog dialog = new JDialog();
                dialog.setUndecorated(true);
                dialog.setModal(true); // Tetapkan dialog sebagai modal jika diperlukan
                dialog.setTitle("Forgot Password");
                dialog.getContentPane().add(panelForgotPassword); // Tambahkan panel ke dalam content pane dialog
                dialog.pack(); // Sesuaikan ukuran dialog berdasarkan komponen yang ada di dalamnya
                dialog.setLocationRelativeTo(null); // Menempatkan dialog ke tengah panel utama
                dialog.setVisible(true); // Tampilkan dialog
            }
        });
        login.add(cmdForget);
        swing.Button cmd = new swing.Button();
        cmd.setBackground(new Color(132, 132, 215));
        cmd.setForeground(new Color(255, 255, 255));
        cmd.addActionListener(eventLogin);
        cmd.setText("SIGN IN");
        login.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                String password = String.valueOf(txtPass.getPassword());
                dataLogin = new ModelLogin(email, password);
            }
        });
    }
    
    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(show);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(3, 0, 45));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(10, 7, 14));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
