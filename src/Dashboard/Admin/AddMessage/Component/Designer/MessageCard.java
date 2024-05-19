package Dashboard.Admin.AddMessage.Component.Designer;

import Dashboard.Admin.AddMessage.Model.ModelMessage;
import Dashboard.Admin.AddMessage.Service.ServiceMessage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;

public class MessageCard extends javax.swing.JPanel {

    private int messageId;
    private ServiceMessage serviceMessage;

    public MessageCard() {
        initComponents();
    }

    public MessageCard(int messageId) {
        initComponents();
        this.messageId = messageId;

        serviceMessage = new ServiceMessage();

        try {
            ModelMessage message = serviceMessage.getMessageById(messageId);
            lbTitle.setText(message.getTitle());
            lbDesc.setText(message.getDescription());
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle exception
        }

        cmdDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageAlerts.getInstance().showMessage("Caution", "Delete this message?", MessageAlerts.MessageType.DEFAULT, MessageAlerts.YES_NO_OPTION, new PopupCallbackAction() {
                    @Override
                    public void action(PopupController pc, int i) {
                        if (i == MessageAlerts.YES_OPTION) {
                            try {
                                serviceMessage.deleteMessage(messageId); // Panggil metode untuk menghapus pesan dari database
                                // Jika penghapusan berhasil, hapus card dari panel atau kontainer tempat card ini ditampilkan
                                getParent().remove(MessageCard.this);
                                revalidate();
                                repaint();
                            } catch (SQLException ex) {
                                ex.printStackTrace(); // Handle exception
                            }
                        }
                    }
                });
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        lbDesc = new javax.swing.JLabel();
        cmdDelete = new swing.ButtonDash();
        cmdEdit = new swing.ButtonDash();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(132, 132, 215), 2, true));
        setOpaque(false);

        lbTitle.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle.setText("Title");

        lbDesc.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbDesc.setForeground(new java.awt.Color(255, 255, 255));
        lbDesc.setText("Description");

        cmdDelete.setForeground(new java.awt.Color(255, 255, 255));
        cmdDelete.setText("X");
        cmdDelete.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N

        cmdEdit.setForeground(new java.awt.Color(0, 0, 0));
        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard/icon/edit-20.png"))); // NOI18N
        cmdEdit.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDesc, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                    .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDesc)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addComponent(cmdEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.ButtonDash cmdDelete;
    private swing.ButtonDash cmdEdit;
    private javax.swing.JLabel lbDesc;
    private javax.swing.JLabel lbTitle;
    // End of variables declaration//GEN-END:variables
}
