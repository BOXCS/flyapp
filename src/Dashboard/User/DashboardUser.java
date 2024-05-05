package Dashboard.User;

import Dashboard.User.Event.MenuEvent;
import Dashboard.User.Form.Form;
import Dashboard.User.ViewPortfolio.Main.ViewPortMain;
import LoginRegister.Model.ModelUser;
import User.Cart.Main.CartMain;
import notif.Panel.Notification;
import User.JobApply.Designer.DesignerApply;
import User.PlaceOrder.Main.PlaceOrderMain;
import User.SeeOrder.Main.SeeOrderMain;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.SplineInterpolator;
import java.util.Arrays;
import raven.popup.GlassPanePopup;

public class DashboardUser extends javax.swing.JFrame {

    private final ModelUser user;

    public DashboardUser(ModelUser user) {
        this.user = user;
        initComponents();
        init();
        getContentPane().setBackground(new Color(63, 109, 217));

        bg = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = new ImageIcon(getClass().getResource("/png/Group 17.png")).getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        MenuEvent event = (int index) -> {
            switch (index) {
                case 0 -> showForm(new ViewPortMain(Arrays.asList(11, 12, 13)));
                case 1 -> {
                    try {
                        showForm(new PlaceOrderMain(user));
                    } catch (SQLException ex) {
                        Logger.getLogger(DashboardUser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                case 2 -> showForm(new CartMain(user));
                case 3 -> showForm(new SeeOrderMain(user));
                case 4 -> showForm(new DesignerApply());
                default -> showForm(new Form(index + ""));
            }
        };
        menu.initMenu(event);
        menu.setSelected(0);
        setExtendedState(MAXIMIZED_BOTH);
    }

    private void init() {
        GlassPanePopup.install(this);
    }

    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.revalidate();
        body.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        menu = new Dashboard.User.Component.Menu();
        body = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        bg.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, -1, 720));

        body.setOpaque(false);
        body.setLayout(new java.awt.BorderLayout());
        bg.add(body, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 16, 1173, 710));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/Group 17.png"))); // NOI18N
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param user
     */
    public static void Usermain(ModelUser user) {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new DashboardUser(user).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JPanel body;
    private javax.swing.JLabel jLabel1;
    private Dashboard.User.Component.Menu menu;
    // End of variables declaration//GEN-END:variables
}
