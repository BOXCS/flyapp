package Dashboard.Designer.Form;

import LoginRegister.Model.ModelUser;
import java.awt.GridLayout;

public class FormDesigner extends javax.swing.JPanel {

    private ModelUser user;

    public FormDesigner(ModelUser user) {
        this.user = user;
        initComponents();

        this.setLayout(null);

        setCustomLayout();

        setOpaque(false);
    }

    // Fungsi untuk mengatur tata letak yang diinginkan
    private void setCustomLayout() {
        // Atur posisi dan ukuran komponen profile1
        profile1.setBounds(29, 77, 340, profile1.getPreferredSize().height);

        // Atur posisi dan ukuran komponen earning1
        earning1.setBounds(29, profile1.getY() + profile1.getHeight() + 35, 340, earning1.getPreferredSize().height);

        // Atur posisi dan ukuran komponen welcomeProfile1
        int x = profile1.getX() + profile1.getWidth() + 50; // Atur posisi X (mendekati profile1 dengan jarak 50)
        int y = profile1.getY() - 50 ; // Posisi Y sama dengan profile1
        int width = welcomeProfile1.getPreferredSize().width; // Gunakan ukuran lebar yang diinginkan
        int height = welcomeProfile1.getPreferredSize().height; // Gunakan ukuran tinggi yang diinginkan

        // Atur posisi dan ukuran welcomeProfile1
        welcomeProfile1.setBounds(x, y, width, height);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        earning1 = new Dashboard.Designer.Component.Earning(user);
        profile1 = new Dashboard.Designer.Component.Profile(user);
        welcomeProfile1 = new Dashboard.Designer.Component.WelcomeProfile(user);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(profile1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(earning1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(114, 114, 114)
                .addComponent(welcomeProfile1, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(welcomeProfile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addComponent(earning1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(177, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboard.Designer.Component.Earning earning1;
    private Dashboard.Designer.Component.Profile profile1;
    private Dashboard.Designer.Component.WelcomeProfile welcomeProfile1;
    // End of variables declaration//GEN-END:variables
}
