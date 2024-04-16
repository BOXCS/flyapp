package notif.Panel;

public class Item extends javax.swing.JPanel {

    public Item(String name, String des, String time) {
        initComponents();
        lbName.setText(name);
        lbDes.setText(des);
        lbTime.setText(time);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbName = new javax.swing.JLabel();
        lbDes = new javax.swing.JLabel();
        lbTime = new javax.swing.JLabel();

        setOpaque(false);

        lbName.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lbName.setForeground(new java.awt.Color(106, 106, 106));
        lbName.setText("Name");

        lbDes.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lbDes.setForeground(new java.awt.Color(134, 134, 134));
        lbDes.setText("Description");

        lbTime.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lbTime.setForeground(new java.awt.Color(134, 134, 134));
        lbTime.setText("a day ago");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDes))
                    .addComponent(lbTime))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbName)
                    .addComponent(lbDes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTime)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbDes;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbTime;
    // End of variables declaration//GEN-END:variables
}
