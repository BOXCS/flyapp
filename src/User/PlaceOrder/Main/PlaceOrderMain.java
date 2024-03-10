package User.PlaceOrder.Main;

import User.PlaceOrder.Service.ServicePricing;
import User.PlaceOrder.component.PanelDetail;
import User.PlaceOrder.component.PanelPricing;
import User.PlaceOrder.model.Model_Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class PlaceOrderMain extends javax.swing.JPanel {

    private ServicePricing servicePricing;
    private PanelPricing pricingPanel;

    // Variabel untuk menyimpan item-item pada masing-masing level
    private List<Model_Data> basicItems;
    private List<Model_Data> standardItems;
    private List<Model_Data> proItems;
    PanelDetail panelDetail = new PanelDetail();
    PanelPricing panelPricing = new PanelPricing();

    public PlaceOrderMain() throws SQLException {
        initComponents();
        setOpaque(false);
        // Inisialisasi objek ServicePricing
        servicePricing = new ServicePricing();
        pricingPanel = new PanelPricing();

//
//        // Mendapatkan daftar produk dari database
        String[] productList = servicePricing.getProductList();
//
//        // Mengisi ComboBox dengan daftar produk
        productComboBox.setModel(new DefaultComboBoxModel<>(productList));
//
// Mendapatkan dan menampilkan item untuk masing-masing level
        displayPackageItems("Basic", servicePricing.getPackageItems("Video Editing", "Basic"));
        displayPackageItems("Standard", servicePricing.getPackageItems("Design Graphic", "Standard"));
        displayPackageItems("Pro", servicePricing.getPackageItems("3D Modelling", "Pro"));
    }

    // Metode untuk menampilkan harga dan item-item terkait pada antarmuka pengguna
    private void displayPackageDetails(String product) {
        // Mendapatkan daftar level paket untuk produk yang dipilih
        String[] levelList = servicePricing.getLevelList(product);

        // Menampilkan daftar level pada panel atau ComboBox level jika Anda ingin menambahkannya
        // ...
        // Mendapatkan harga dan item-item terkait untuk level paket yang dipilih
        for (String level : levelList) {
            double price = servicePricing.getPrice(product, level);

            // Format harga dengan DecimalFormat
            DecimalFormat df = new DecimalFormat("#,#00");
            String formattedPrice = df.format(price);

            // Memisahkan harga menjadi bagian sebelum dan setelah desimal
            String[] priceArray = formattedPrice.split("\\.");

            // Menyimpan bagian harga ke dalam lb1 dan lb2 di PanelPricing
            switch (level) {
                case "Basic":
                    basicPricing.setPriceComponents("$" + priceArray[0] + ".", priceArray.length == 2 ? priceArray[1] : "");
                    basicPricing.setTitleText(level);
                    basicPricing.setPackageItems(servicePricing.getPackageItems(product, level));
                    break;
                case "Pro":
                    proPricing.setPriceComponents("$" + priceArray[0] + ".", priceArray.length == 2 ? priceArray[1] : "");
                    proPricing.setTitleText(level);
                    proPricing.setPackageItems(servicePricing.getPackageItems(product, level));
                    break;
                case "Standard":
                    standardPricing.setPriceComponents("$" + priceArray[0] + ".", priceArray.length == 2 ? priceArray[1] : "");
                    standardPricing.setTitleText(level);
                    standardPricing.setPackageItems(servicePricing.getPackageItems(product, level));
                    break;
                // Add more cases if you have additional levels
            }

            // Menampilkan item-item pada antarmuka pengguna
            // Panggil metode displayPackageItems untuk menampilkan item-item
            displayPackageItems(level, servicePricing.getPackageItems(product, level));
        }
    }

    // Metode untuk menampilkan item-item pada antarmuka pengguna
    private void displayPackageItems(String level, List<Model_Data> packageItems) {
        // Memastikan daftar item paket tidak kosong sebelum menampilkan
        if (packageItems == null || packageItems.isEmpty()) {
            // Mungkin tambahkan log atau tangani sesuai kebutuhan
            System.out.println("Daftar item paket kosong atau null.");
            return;
        }

        // Menyimpan item-item ke dalam JList masing-masing level
        switch (level) {
            case "Basic":
                basicPricing.setListItems(packageItems);
                break;
            case "Pro":
                proPricing.setListItems(packageItems);
                break;
            case "Standard":
                standardPricing.setListItems(packageItems);
                break;
            // Tambahkan lebih banyak kasus jika Anda memiliki tingkatan tambahan
            default:
                // Menangani tingkatan yang tidak dikenali
                System.out.println("Tingkatan tidak dikenali: " + level);
                // Atau tambahkan tanggapan lain sesuai kebutuhan
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        roundPanel1 = new Dashboard.Swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        standardPricing = new User.PlaceOrder.component.PanelPricing();
        proPricing = new User.PlaceOrder.component.PanelPricing();
        basicPricing = new User.PlaceOrder.component.PanelPricing();
        jLabel1 = new javax.swing.JLabel();
        productComboBox = new javax.swing.JComboBox<>();

        jPanel1.setOpaque(false);

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));
        roundPanel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Package");

        standardPricing.setColor1(new java.awt.Color(145, 145, 220));
        standardPricing.setColor2(new java.awt.Color(120, 124, 214));

        proPricing.setColor1(new java.awt.Color(145, 145, 220));
        proPricing.setColor2(new java.awt.Color(120, 124, 214));

        basicPricing.setColor1(new java.awt.Color(145, 145, 220));
        basicPricing.setColor2(new java.awt.Color(120, 124, 214));

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(basicPricing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(standardPricing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(proPricing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(527, 527, 527)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(standardPricing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proPricing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(basicPricing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Place Order");

        productComboBox.setBackground(new java.awt.Color(255, 255, 255));
        productComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(productComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(productComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void productComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productComboBoxActionPerformed
        String selectedProduct = (String) productComboBox.getSelectedItem();
        String selectedLevel = "Basic";  // Ganti dengan level yang sesuai
        if (selectedProduct != null) {
            displayPackageDetails(selectedProduct);

            // Gunakan selectedProduct dan selectedLevel dalam pemanggilan updatePricingList
            panelDetail.updatePricingList(servicePricing, selectedProduct, selectedLevel);
        }
    }//GEN-LAST:event_productComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private User.PlaceOrder.component.PanelPricing basicPricing;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private User.PlaceOrder.component.PanelPricing proPricing;
    private javax.swing.JComboBox<String> productComboBox;
    private Dashboard.Swing.RoundPanel roundPanel1;
    private User.PlaceOrder.component.PanelPricing standardPricing;
    // End of variables declaration//GEN-END:variables
}
