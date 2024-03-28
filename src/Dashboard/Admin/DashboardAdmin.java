
package Dashboard.Admin;

import Admin.AddDesigner.Main.AddDesignerMain;
import Dashboard.Admin.Event.MenuEvent;
import Dashboard.Admin.form.Form;
import Dashboard.Admin.form.FormHome;
import LoginRegister.Model.ModelUser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import raven.popup.GlassPanePopup;

public class DashboardAdmin extends javax.swing.JFrame {

    private final ModelUser user;
    
    public DashboardAdmin(ModelUser user) {
        this.user = user;
        GlassPanePopup.install(this);
        initComponents();
        getContentPane().setBackground(new Color(63, 109, 217));
        
        bg = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = new ImageIcon(getClass().getResource("/png/Group 17.png")).getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }     
        };
        MenuEvent event = new MenuEvent() {
            @Override
            public void menuSelected(int index) {
                if (index == 0) {
                    showForm(new FormHome());
                } else if (index == 2) {
                    showForm(new AddDesignerMain());
                } else {
                    showForm(new Form(index + ""));
                    
                }
            }
        };
        menu.initMenu(event);
        menu.setSelected(0);
        setExtendedState(MAXIMIZED_BOTH);
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
        menu = new Dashboard.Admin.Component.Menu();
        body = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(51, 51, 51));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        bg.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, 730));

        body.setOpaque(false);
        body.setLayout(new java.awt.BorderLayout());
        bg.add(body, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 10, 1173, 730));

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
     * @param args the command line arguments
     */
    public static void Adminmain(ModelUser user) {
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
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardAdmin(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JPanel body;
    private javax.swing.JLabel jLabel1;
    private Dashboard.Admin.Component.Menu menu;
    // End of variables declaration//GEN-END:variables
}
