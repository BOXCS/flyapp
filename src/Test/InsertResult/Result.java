package Test.InsertResult;

import connection.DatabaseConnection;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import jnafilechooser.api.JnaFileChooser;
import raven.alerts.MessageAlerts;
import raven.popup.GlassPanePopup;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;

public class Result extends javax.swing.JFrame {

    private File selectedFile;
    private ServiceResult serviceResult;

    public Result() {
        initComponents();
        init();
    }

    private void init() {
        GlassPanePopup.install(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooserButton = new javax.swing.JButton();
        sendButton = new javax.swing.JButton();
        transactionNumber = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileChooserButton.setText("Choose File");
        fileChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserButtonActionPerformed(evt);
            }
        });

        sendButton.setText("SEND");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fileChooserButton)
                            .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(transactionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(transactionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(fileChooserButton)
                .addGap(38, 38, 38)
                .addComponent(sendButton)
                .addGap(71, 71, 71))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        // Getting the transaction number entered by user
        String transaction = transactionNumber.getText();

        // Periksa apakah nomor transaksi terlambat dan perbarui status jika perlu
        serviceResult.checkIfLateAndUpdateStatus(transaction);

        // Check if a file has been selected
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Please select a file first.");
        } else {
            // Handling SEND button action here
            // Call insertResult method from ServiceResult to save data to the database

            String username = ServiceResult.getUsernameFromTransaction(transaction);

            String recipientEmail = ServiceResult.getEmailFromUsername(username);

            ServiceResult.insertResult(transaction, selectedFile, recipientEmail);
            MessageAlerts.getInstance().showMessage("Send Success", "Data Successfully Send to Database", MessageAlerts.MessageType.SUCCESS, MessageAlerts.OK_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.OK_OPTION) {
                    System.out.println("Click OK");
                }
            });
        }
    }//GEN-LAST:event_sendButtonActionPerformed

    private void fileChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserButtonActionPerformed
        // Handle file chooser button action here
        JnaFileChooser fileChooser = new JnaFileChooser();
        Window Window = null;
        boolean action = fileChooser.showOpenDialog(Window);

        if (action) {
            System.out.println(fileChooser.getSelectedFile());
            selectedFile = fileChooser.getSelectedFile(); // Simpan file yang dipilih untuk digunakan saat mengirim
            JOptionPane.showMessageDialog(this, "You selected: " + selectedFile.getAbsolutePath());
        }
    }//GEN-LAST:event_fileChooserButtonActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        try {
            DatabaseConnection.getInstance().connectToDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Result().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton fileChooserButton;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField transactionNumber;
    // End of variables declaration//GEN-END:variables
}
