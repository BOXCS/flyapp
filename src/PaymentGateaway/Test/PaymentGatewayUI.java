package PaymentGateaway.Test;

import User.PlaceOrder.Service.ServicePricing;
import User.PlaceOrder.component.PanelPricing;
import java.sql.SQLException;
import javax.swing.JButton;
import java.text.DecimalFormat;
import java.util.Random;

public class PaymentGatewayUI extends javax.swing.JFrame {

    // Variabel untuk komponen-komponen UI Payment Gateway
    private JButton btnCheckOut;

    // Variabel untuk menyimpan referensi ke objek-objek PanelPricing
    private PanelPricing basicPricing;
    private PanelPricing standardPricing;
    private PanelPricing proPricing;

    public PaymentGatewayUI(PanelPricing basicPricing, PanelPricing standardPricing, PanelPricing proPricing) {
        initComponents();

        // Inisialisasi referensi ke objek-objek PanelPricing
        this.basicPricing = basicPricing;
        this.standardPricing = standardPricing;
        this.proPricing = proPricing;

        init();
        initComponents();

        // Set default close operation menjadi DISPOSE_ON_CLOSE
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void init() {
        lblTotalAmount.setText(getTotalAmount());

        // Menghasilkan nomor acak antara 0 dan 9999
        int randomNum = new Random().nextInt(10000);

        // Mengatur teks lblTransactionNumber dengan format "TRS-XXXX"
        lblTransactionNumber.setText("TRS-" + String.format("%04d", randomNum));

        // Mengatur total amount sesuai dengan harga yang dipilih
        lblTotalAmount.setText(getTotalAmount());

        // Mengatur teks pada tombol Checkout
        cmdCheckout.setText("Checkout " + getTotalAmount());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pGDetail1 = new PaymentGateaway.Component.PGDetail();
        jLabel2 = new javax.swing.JLabel();
        lblTransactionNumber = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTotalAmount = new javax.swing.JLabel();
        lblCard = new javax.swing.JLabel();
        lblCVV = new javax.swing.JLabel();
        txtCVV = new PaymentGateaway.Swing.PasswordField();
        txtCard = new PaymentGateaway.Swing.TextField();
        dateChooserExpiredDate = new com.toedter.calendar.JDateChooser();
        lblExpiredDate = new javax.swing.JLabel();
        cmdCheckout = new PaymentGateaway.Swing.ButtonGradient();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LoginRegister/icon/Fly.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Transaction Number:");

        lblTransactionNumber.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblTransactionNumber.setForeground(new java.awt.Color(255, 255, 255));
        lblTransactionNumber.setText("TRS-1234");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total Amount:");

        lblTotalAmount.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblTotalAmount.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalAmount.setText("$99.99");

        javax.swing.GroupLayout pGDetail1Layout = new javax.swing.GroupLayout(pGDetail1);
        pGDetail1.setLayout(pGDetail1Layout);
        pGDetail1Layout.setHorizontalGroup(
            pGDetail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pGDetail1Layout.createSequentialGroup()
                .addGroup(pGDetail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pGDetail1Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTransactionNumber))
                    .addGroup(pGDetail1Layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalAmount)))
                .addGap(171, 171, 171))
        );
        pGDetail1Layout.setVerticalGroup(
            pGDetail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pGDetail1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pGDetail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTransactionNumber))
                .addGap(18, 18, 18)
                .addGroup(pGDetail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblTotalAmount))
                .addGap(20, 20, 20))
        );

        lblCard.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblCard.setForeground(new java.awt.Color(255, 255, 255));
        lblCard.setText("Card Number :");

        lblCVV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblCVV.setForeground(new java.awt.Color(255, 255, 255));
        lblCVV.setText("CVV :");

        txtCVV.setLabelText("CVV");

        txtCard.setLabelText("Card Number");

        lblExpiredDate.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblExpiredDate.setForeground(new java.awt.Color(255, 255, 255));
        lblExpiredDate.setText("Expiration Date :");

        cmdCheckout.setText("buttonGradient1");
        cmdCheckout.setColor1(new java.awt.Color(145, 145, 220));
        cmdCheckout.setColor2(new java.awt.Color(120, 124, 214));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(pGDetail1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(180, 180, 180))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cmdCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCVV)
                            .addComponent(lblCard)
                            .addComponent(lblExpiredDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCVV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateChooserExpiredDate, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(228, 228, 228))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pGDetail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCard))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCVV)
                    .addComponent(txtCVV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(dateChooserExpiredDate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblExpiredDate)))
                .addGap(18, 18, 18)
                .addComponent(cmdCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String getTransactionNumber() {
        return lblTransactionNumber.getText();
    }

    // Metode untuk menangani aksi button Check Out
    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {
        // Menjalankan proses check out
        processCheckOut();
    }

    // Metode untuk mengambil total amount dari panel pricing yang dipilih
    public String getTotalAmount() {
        // Inisialisasi total amount
        double totalAmount = 0.0;

        // Cek properti isSelected untuk menentukan panel pricing mana yang dipilih
        if (basicPricing.isIsSelected()) {
            totalAmount += basicPricing.getPrice();
        }
        if (standardPricing.isIsSelected()) {
            totalAmount += standardPricing.getPrice();
        }
        if (proPricing.isIsSelected()) {
            totalAmount += proPricing.getPrice();
        }

        // Format total amount dengan DecimalFormat
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(totalAmount);
    }

    public void handleBuy(String pricingType) {
        // Lakukan proses pembelian berdasarkan jenis harga yang diberikan
        if (pricingType.equals("Basic")) {
            // Lakukan proses pembelian Basic
        } else if (pricingType.equals("Standard")) {
            // Lakukan proses pembelian Standard
        } else if (pricingType.equals("Pro")) {
            // Lakukan proses pembelian Pro
        } else {
            // Tampilkan pesan kesalahan jika jenis harga tidak dikenali
            System.out.println("Jenis harga tidak dikenali: " + pricingType);
        }
    }

    // Metode untuk menangani proses check out
    private void processCheckOut() {
        String cardNumber = txtCard.getText();
        String cvv = txtCVV.getText();
        String expiredDate = dateChooserExpiredDate.getDate().toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private PaymentGateaway.Swing.ButtonGradient cmdCheckout;
    private com.toedter.calendar.JDateChooser dateChooserExpiredDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCVV;
    private javax.swing.JLabel lblCard;
    private javax.swing.JLabel lblExpiredDate;
    private javax.swing.JLabel lblTotalAmount;
    private javax.swing.JLabel lblTransactionNumber;
    private PaymentGateaway.Component.PGDetail pGDetail1;
    private PaymentGateaway.Swing.PasswordField txtCVV;
    private PaymentGateaway.Swing.TextField txtCard;
    // End of variables declaration//GEN-END:variables
}
