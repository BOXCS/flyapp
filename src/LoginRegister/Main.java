package LoginRegister;

import Dashboard.Admin.DashboardAdmin;
import Dashboard.User.DashboardUser;
import Dashboard.Designer.DashboardDesigner;
import LoginRegister.Component.Message;
import LoginRegister.Component.PanelLoading;
import LoginRegister.Component.PanelLoginRegister;
import LoginRegister.Component.PanelVerifyCode;
import LoginRegister.Model.ModelLogin;
import LoginRegister.Model.ModelMessage;
import LoginRegister.Model.ModelUser;
import LoginRegister.Service.ServiceLogin;
import LoginRegister.Service.ServiceMail;
import LoginRegister.Service.TransactionMonitor;
import User.PlaceOrder.Main.PlaceOrderMain;
import User.PlaceOrder.component.PanelPricing;
import connection.DatabaseConnection;
import java.awt.Dimension;
//import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.mail.Service;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import swing.ComponentResizer;
import java.net.URL;
import java.sql.Connection;

public class Main extends javax.swing.JFrame {

    private JLabel bgLabel;
    private MigLayout layout;
    private PanelCover cover;
    private PanelLoading loading;
    private PanelVerifyCode verifyCode;
    private PanelLoginRegister loginRegister;
    private boolean isLogin;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    private final DecimalFormat df = new DecimalFormat("##0.###");
    private ServiceLogin service;
    private TransactionMonitor monitor;
    private Connection conn;

    public Main() {
        initComponents();
        init();
        setExtendedState(MAXIMIZED_BOTH);

        Connection conn = DatabaseConnection.getInstance().getConnection();
        monitor = new TransactionMonitor(conn);
        monitor.startMonitoring();
    }

    private void init() {
        ComponentResizer com = new ComponentResizer();
        com.registerComponent(this);
        com.setMinimumSize(new Dimension(800, 500));
        com.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        com.setSnapSize(new Dimension(10, 10));
        service = new ServiceLogin();
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        loading = new PanelLoading();
        verifyCode = new PanelVerifyCode();
        Message ms = new Message();
        ActionListener eventRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        };
        ActionListener eventLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        };
        loginRegister = new PanelLoginRegister(eventRegister, eventLogin);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }
                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) {
                    loginRegister.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                bg.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }

        };
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);  //  for smooth animation
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/png/Group 17.png"));

        // Inisialisasi JLabel dan atur ikonnya
        bgLabel = new JLabel(bgIcon);
        bgLabel.setSize(bgIcon.getIconWidth(), bgIcon.getIconHeight());

        // Tambahkan JLabel ke JLayeredPane
        bg.add(bgLabel, Integer.valueOf(0));
        bgLabel.setSize(bg.getWidth(), bg.getHeight());
        bg.setLayout(layout);
        bg.setLayer(ms, JLayeredPane.POPUP_LAYER);
        bg.setLayer(loading, JLayeredPane.POPUP_LAYER);
        bg.setLayer(verifyCode, JLayeredPane.POPUP_LAYER);
        bg.setLayer(loginRegister, JLayeredPane.PALETTE_LAYER);
        bg.setLayer(cover, JLayeredPane.PALETTE_LAYER);  // Pindahkan ke lapisan yang lebih rendah
        bg.add(cover, "width " + coverSize + "%, pos 0al 0 n 100%");
        bg.add(loginRegister, "width " + loginSize + "%, pos 1al 0 n 100%");
        bg.add(loading, "pos 0 0 100% 100%");
        bg.add(verifyCode, "pos 0 0 100% 100%");
        bg.add(ms, "pos 0.5al -30");
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
        verifyCode.addEventButtonOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ModelUser user = loginRegister.getUser();
                    if (service.verifyCodeWithUser(user.getUserID(), verifyCode.getInputCode())) {
                        service.doneVerify(user.getUserID());
                        showMessage(Message.MessageType.SUCCESS, "Register Success");
                        verifyCode.setVisible(false);
                    } else {
                        showMessage(Message.MessageType.ERROR, "Verify code incorrect");
                    }
                } catch (SQLException ae) {
                    showMessage(Message.MessageType.ERROR, "Error");
                }
            }
        });
    }

    private void register() {
        ModelUser user = loginRegister.getUser();
        String password = user.getPassword();

        try {
            if (service.checkDuplicateUser(user.getUserName())) {
                showMessage(Message.MessageType.ERROR, "User name already exists");
            } else if (service.checkDuplicateEmail(user.getEmail())) {
                showMessage(Message.MessageType.ERROR, "Email already exists");
            } else if (!service.isPasswordValid(password)) {
                showMessage(Message.MessageType.ERROR, "Password must have at least 8 characters, including a digit and a special character");
            } else {
                service.insertUser(user);
                sendMain(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage(Message.MessageType.ERROR, "Error Register");
        }
    }

    private void login() {
        ModelLogin data = loginRegister.getDataLogin();
        try {
            ModelUser user = service.login(data);
            if (user != null) {
//                monitor.stopMonitoring();
                this.dispose();
                if ("admin".equalsIgnoreCase(user.getRole())) {
                    DashboardAdmin.Adminmain(user); 
                } else if ("designer".equalsIgnoreCase(user.getRole())) {
                    DashboardDesigner.Designermain(user); 
                } else {
                    DashboardUser.Usermain(user);
                }
            } else {
                showMessage(Message.MessageType.ERROR, "Email or Password incorrect");
            }

        } catch (SQLException e) {
            showMessage(Message.MessageType.ERROR, "Error Login");
            e.printStackTrace();
        }
    }

    private void sendMain(ModelUser user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                loading.setVisible(true);
                ModelMessage ms = new ServiceMail().sendMain(user.getEmail(), user.getVerifyCode());
                if (ms.isSuccess()) {
                    loading.setVisible(false);
                    verifyCode.setVisible(true);
                } else {
                    loading.setVisible(false);
                    showMessage(Message.MessageType.ERROR, ms.getMessage());
                }
            }
        }).start();
    }

    private void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        // Tambahkan komponen Message ke lapisan teratas
        bg.setLayer(ms, JLayeredPane.MODAL_LAYER);
        bg.add(ms, "pos 0.5al -30", 0);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    bg.add(ms, "pos 0.5al -30", 0); //  Insert to bg fist index 0
                    ms.setVisible(true);
                    bg.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                bg.repaint();
                bg.revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    bg.remove(ms);
                    bg.repaint();
                    bg.revalidate();
                } else {
                    ms.setShow(true);
                }
            }

        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    animator.start();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }).start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(10, 7, 14));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1440, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) throws SQLException {
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        try {
            DatabaseConnection.getInstance().connectToDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new splash.SplashScreen(null,true).setVisible(true);
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
