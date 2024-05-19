package Dashboard.Designer.SendOrder.Component;

import Dashboard.Designer.Profile.Component.PlayPortfolio;
import Dashboard.Designer.SendOrder.Main.SendOrderMain;
import Dashboard.Designer.SendOrder.Service.ServiceSendOrder;
import Dashboard.Swing.scroll.ModernScrollBarUI;
import LoginRegister.Model.ModelUser;
import Test.DisplayImage.Main.DisplayImageFromDatabasePreview;
import java.awt.Window;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jnafilechooser.api.JnaFileChooser;
import video.MainVideoJPreview;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class SendOrderPanel1 extends javax.swing.JPanel {

    private List<File> selectedFiles = new ArrayList<>();
    private Set<String> fileTypes = new HashSet<>();
    private SendOrderMain sendOrderMain;

    // private final ModelUser user;
    private ServiceSendOrder serviceSendOrder;
    private DisplayResult playResult;
    private MainVideoJPreview videoPreview;
    private InsertTransaction insertTransaction;
    private String transactionNumber;

    public SendOrderPanel1(SendOrderMain sendOrderMain) {
        this.sendOrderMain = sendOrderMain;
        initComponents();
        setOpaque(false);
        configureScrollPane();

        // serviceSendOrder = new ServiceSendOrder();
        cmdSend.addActionListener(e -> {
            processSendFiles(lbTransactionNumber.getText());
        });
    }

    // Method to set InsertTransaction
    public void setTransaction(InsertTransaction insertTransaction) {
        this.insertTransaction = insertTransaction;
    }

    // Method to update lbTransactionNumber
    public void updateTransactionNumber(String transactionNumber) {
        lbTransactionNumber.setText(transactionNumber);
    }

    // Method to process sending selected files
    public void processSendFiles(String transactionNumber) {
        if (selectedFiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No files selected. Please select files to send.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (fileTypes.size() > 1) {
            JOptionPane.showMessageDialog(this, "Incompatible file types. Please select either all images or all videos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection connection = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        try {
            connection = ServiceSendOrder.getConnection();
            serviceSendOrder.insertResult(transactionNumber, selectedFiles);

            JOptionPane.showMessageDialog(this, "All selected files sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to send files: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (p != null) {
                    p.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void loadTransactionNumber(String transactionNumber) {
        lbTransactionNumber.setText(transactionNumber);
    }

    private void configureScrollPane() {
        jScrollPane1.setOpaque(false);
        jScrollPane1.setBackground(null);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.getViewport().setBackground(null);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        jScrollPane1.getHorizontalScrollBar().setUI(new ModernScrollBarUI());

        body.setOpaque(false);
        body.setBackground(null);
        addPortfolio1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private String getTransactionNumber() {
        return insertTransaction.getTransactionNumber();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();
        addPortfolio1 = new Dashboard.Designer.Profile.Component.AddPortfolio();
        cmdSend = new Dashboard.Swing.Button();
        lbTransactionNumber = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Deliver the result");

        jScrollPane1.setBorder(null);

        body.setLayout(new java.awt.GridLayout(11, 1, 0, 5));

        addPortfolio1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPortfolio1MouseClicked(evt);
            }
        });
        body.add(addPortfolio1);

        jScrollPane1.setViewportView(body);

        cmdSend.setBackground(new java.awt.Color(132, 132, 215));
        cmdSend.setForeground(new java.awt.Color(255, 255, 255));
        cmdSend.setText("SEND");
        cmdSend.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        lbTransactionNumber.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbTransactionNumber.setForeground(new java.awt.Color(204, 204, 204));
        lbTransactionNumber.setText("TRS-1234");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTransactionNumber)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(297, 297, 297)
                .addComponent(cmdSend, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(314, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbTransactionNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addPortfolio1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPortfolio1MouseClicked
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.setTitle("Select Result");
        Window window = null;
        boolean action = fileChooser.showOpenDialog(window);

        if (action) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileType = getFileType(selectedFile);

            if (fileType == null) {
                JOptionPane.showMessageDialog(this, "File format not supported.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!fileTypes.isEmpty() && !fileTypes.contains(fileType)) {
                JOptionPane.showMessageDialog(this, "Incompatible file types. Please select either all images or all videos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            fileTypes.add(fileType);
            selectedFiles.add(selectedFile);
            addFileToBody(selectedFile);
        }
    }//GEN-LAST:event_addPortfolio1MouseClicked

    private String getFileType(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif")) {
            return "image";
        } else if (extension.equals("mp4") || extension.equals("avi") || extension.equals("mov") || extension.equals("wmv")) {
            return "video";
        } else {
            return null;
        }
    }

    private void addFileToBody(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif")) {
            DisplayImageFilePreview imagePreview = new DisplayImageFilePreview(file);
            body.add(imagePreview);
        } else if (extension.equals("mp4") || extension.equals("avi") || extension.equals("mov") || extension.equals("wmv")) {
            String filePath = file.getAbsolutePath();
            // MainVideoJPreview videoPreview = new MainVideoJPreview(filePath);
            DisplayResult displayResult = new DisplayResult(fileName);
            body.add(displayResult);
        } else {
            JOptionPane.showMessageDialog(this, "File format not supported.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        body.revalidate();
        body.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboard.Designer.Profile.Component.AddPortfolio addPortfolio1;
    private javax.swing.JPanel body;
    private Dashboard.Swing.Button cmdSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTransactionNumber;
    private Dashboard.Swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables

}
