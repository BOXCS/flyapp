package Dashboard.Designer.Component;

import Dashboard.Admin.AddMessage.Model.ModelMessage;
import Dashboard.Admin.AddMessage.Service.ServiceMessage;
import LoginRegister.Model.ModelUser;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ViewMessageDesigner extends javax.swing.JPanel {

    private ModelUser user;

    public ViewMessageDesigner() {
        initComponents();
    }

    public ViewMessageDesigner(ModelUser user) {
        initComponents();
        this.user = user;

        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));

        ServiceMessage serviceMessage = new ServiceMessage();
        try {
            List<ModelMessage> messages = serviceMessage.getAllMessagesByUser(user);
            for (ModelMessage message : messages) {
                System.out.println("Title: " + message.getTitle() + ", Description: " + message.getDescription());
                MessageCardDesigner messageCard = new MessageCardDesigner(message);
                addMessageCard(messageCard);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addMessageCard(MessageCardDesigner messageCard) {
        // Mendapatkan nilai kepentingan pesan (Important, Medium, Low)
        String priority = messageCard.getMessage().getMessageStatus();

        // Menentukan posisi penambahan pesan berdasarkan prioritasnya
        int insertIndex = body.getComponentCount();
        switch (priority) {
            case "Important":
                // Important ditambahkan di awal
                insertIndex = 0;
                break;
            case "Medium":
                // Medium ditambahkan setelah Important
                for (int i = 0; i < body.getComponentCount(); i++) {
                    if (((MessageCardDesigner) body.getComponent(i)).getMessage().getMessageStatus().equals("Important")) {
                        insertIndex = i + 1;
                        break;
                    }
                }
                break;
            case "Low":
                // Low ditambahkan setelah Medium
                for (int i = 0; i < body.getComponentCount(); i++) {
                    if (((MessageCardDesigner) body.getComponent(i)).getMessage().getMessageStatus().equals("Medium")) {
                        insertIndex = i + 1;
                        break;
                    }
                }
                break;
            default:
                break;
        }

        // Menambahkan pesan ke panel dengan memperhitungkan urutan prioritas
        body.add(messageCard, insertIndex);
        body.add(Box.createVerticalStrut(20)); // Menambahkan VerticalStrut setelah messageCard yang baru ditambahkan
        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new javax.swing.JPanel();

        setOpaque(false);

        body.setOpaque(false);

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 175, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    // End of variables declaration//GEN-END:variables
}
