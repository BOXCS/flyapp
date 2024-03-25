package PaymentGateaway.Test;

import java.awt.HeadlessException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

public class CetakNota extends javax.swing.JFrame {

    HashMap<String, Object> param = new HashMap<>();

    public CetakNota() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cetaknota = new javax.swing.JButton();
        string = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cetaknota.setText("Cetak Nota");
        cetaknota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetaknotaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(cetaknota, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(string, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(string, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(cetaknota, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cetaknotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetaknotaActionPerformed
        try {
            String NamaFile = "src/nota/Main/report1.jasper"; // Sesuaikan dengan lokasi file laporan
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "fly_studio";
            String driver = "com.mysql.cj.jdbc.Driver"; // Gunakan kelas driver yang diperbarui
            String userName = "root";
            String password = "";

            // Mendapatkan koneksi ke database
            Connection koneksi = DriverManager.getConnection(url + dbName, userName, password);

            // Memisahkan angka dari awalan "TRS-"
            String transactionNumberText = string.getText();

            // Memvalidasi input sesuai format "TRS-XXXX"
            if (transactionNumberText.matches("TRS-\\d{4}")) {
                // Memasukkan nomor transaksi ke parameter
                param.put("transaction_number", transactionNumberText); // Mengirimkan string lengkap, termasuk "TRS-"

                // Mengisi dan menampilkan laporan
                JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi);
                JasperViewer.viewReport(JPrint, false);
            } else {
                // Jika tidak valid, tampilkan pesan kesalahan
                JOptionPane.showMessageDialog(this, "Error: Transaction number must be in the format TRS-XXXX, where XXXX is a 4-digit number.");
            }
        } catch (HeadlessException | NumberFormatException | SQLException | net.sf.jasperreports.engine.JRException e) {
            // Tampilkan pesan kesalahan jika terjadi kesalahan lain
            e.printStackTrace(); // mencetak stack trace lengkap ke konsol
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_cetaknotaActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(CetakNota.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CetakNota.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CetakNota.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CetakNota.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CetakNota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cetaknota;
    private javax.swing.JTextField string;
    // End of variables declaration//GEN-END:variables
}
