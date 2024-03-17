package User.PlaceOrder.Main;

import PaymentGateaway.PGMain;
import User.PlaceOrder.Service.ServicePricing;
import User.PlaceOrder.component.PanelDetail;
import User.PlaceOrder.component.PanelPricing;
import User.PlaceOrder.model.Model_Data;
import User.PlaceOrder.swing.Title;
import PaymentGateaway.Test.PaymentGatewayUI;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class PlaceOrderMain extends javax.swing.JPanel {

    private ServicePricing servicePricing;
    private PanelPricing pricingPanel;
    private PGMain pgMain;
    private PaymentGatewayUI paymentGateaway;

    // Variabel untuk menyimpan item-item pada masing-masing level
    private List<Model_Data> basicItems;
    private List<Model_Data> standardItems;
    private List<Model_Data> proItems;

//    PanelDetail panelDetail = new PanelDetail();
    PanelPricing panelPricing = new PanelPricing();
    PanelDetail panelDetail = new PanelDetail(this);

    public PlaceOrderMain() throws SQLException {
        initComponents();
        setOpaque(false);

        // Inisialisasi objek ServicePricing
        servicePricing = new ServicePricing();
        pricingPanel = new PanelPricing();
        panelDetail = new PanelDetail(this);

        // Mendapatkan daftar produk dari database
        String[] productList = servicePricing.getProductList();

        // Mengisi ComboBox dengan daftar produk
        productComboBox.setModel(new DefaultComboBoxModel<>(productList));

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
            DecimalFormat df = new DecimalFormat("#,##0.00");
            String formattedPrice = df.format(price);

            // Memisahkan harga menjadi bagian sebelum dan setelah desimal
            String[] priceArray = formattedPrice.split("\\.");

            // Menyimpan bagian harga ke dalam lb1 dan lb2 di PanelPricing
            switch (level) {
                case "Basic":
                    basicPricing.setPriceComponents("$" + priceArray[0], "." + priceArray[1]);
                    basicPricing.setTitleText(level);
                    basicPricing.setPackageItems(servicePricing.getPackageItems(product, level));
                    break;
                case "Pro":
                    proPricing.setPriceComponents("$" + priceArray[0], "." + priceArray[1]);
                    proPricing.setTitleText(level);
                    proPricing.setPackageItems(servicePricing.getPackageItems(product, level));
                    break;
                case "Standard":
                    standardPricing.setPriceComponents("$" + priceArray[0], "." + priceArray[1]);
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

    // Metode untuk mendapatkan bagian desimal dari harga
    private String getDecimalPart(double price) {
        String priceStr = String.valueOf(price);
        int dotIndex = priceStr.indexOf(".");

        // Check if there are multiple points in the string
        if (dotIndex != -1 && priceStr.indexOf(".", dotIndex + 1) != -1) {
            // Remove any extra points after the first one
            priceStr = priceStr.replaceFirst("\\.", "");
        }

        // Add the decimal symbol (.) in the middle
        return (dotIndex != -1) ? "." + priceStr.substring(dotIndex + 1) : "";
    }

    // Helper method to get the corresponding PanelPricing based on the selected level
    public PanelPricing getSelectedPanelPricing(String selectedLevel) {
        switch (selectedLevel) {
            case "Basic":
                return basicPricing;
            case "Standard":
                return standardPricing;
            case "Pro":
                return proPricing;
            // Add more cases if you have additional levels
            default:
                return null;
        }
    }

    // Method to retrieve information from PanelPricing components for a given category and level
    public void getAllPanelPricingInfo(String category, String level) {
        PanelPricing selectedPanel = getSelectedPanelPricing(level);

        if (selectedPanel != null) {
            // Retrieve information from PanelPricing components
            Title title = selectedPanel.getTitle();
            double price = selectedPanel.getPrice();
            List<Model_Data> packageItems = selectedPanel.getListItems();

            // Display the retrieved information (you can modify this based on your needs)
            System.out.println("Category: " + category);
            System.out.println("Level: " + level);
            System.out.println("Title: " + title);
            System.out.println("Price: " + price);

            if (packageItems != null && !packageItems.isEmpty()) {
                System.out.println("Package Items:");
                for (Model_Data item : packageItems) {
                    System.out.println("- " + item.getProduct() + ": $" + item.getPrice());
                }
            } else {
                System.out.println("No package items available for this level.");
            }
        } else {
            System.out.println("Error: Selected PanelPricing is null.");
        }
    }

    // Create a method to handle the Buy action with the retrieved price
    private void processBuyAction(String level, String lb1, String lb2) {
        // Perform actions with the retrieved price, for example, display a message
        JOptionPane.showMessageDialog(this, "You have selected a " + level + " product with price: " + lb1 + lb2, "Purchase Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleBuyAction(String level) {
        // Dapatkan harga dari pricing masing-masing level
        PanelPricing selectedPanel = getSelectedPanelPricing(level);

        if (selectedPanel != null) {
            String lb1 = selectedPanel.getLb1(); // Menggunakan metode getLb1
            String lb2 = selectedPanel.getLb2(); // Menggunakan metode getLb2

            // Lakukan tindakan yang sesuai dengan pembelian
            processBuyAction(level, lb1, lb2);
        } else {
            System.out.println("Error: Selected PanelPricing is null.");
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
        cmdBasic = new User.PlaceOrder.swing.Button();
        cmdStandard = new User.PlaceOrder.swing.Button();
        cmdPro = new User.PlaceOrder.swing.Button();
        jLabel1 = new javax.swing.JLabel();
        productComboBox = new javax.swing.JComboBox<>();
        designerCombobox = new javax.swing.JComboBox<>();

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

        cmdBasic.setBackground(new java.awt.Color(120, 124, 214));
        cmdBasic.setText("Buy Now");
        cmdBasic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBasicActionPerformed(evt);
            }
        });

        cmdStandard.setBackground(new java.awt.Color(120, 124, 214));
        cmdStandard.setText("Buy Now");
        cmdStandard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdStandardActionPerformed(evt);
            }
        });

        cmdPro.setBackground(new java.awt.Color(120, 124, 214));
        cmdPro.setText("Buy Now");
        cmdPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdProActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(basicPricing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(standardPricing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(proPricing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(527, 527, 527)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(cmdBasic, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165)
                .addComponent(cmdStandard, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdPro, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(standardPricing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proPricing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(basicPricing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmdStandard, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmdBasic, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmdPro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

        designerCombobox.setBackground(new java.awt.Color(255, 255, 255));
        designerCombobox.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        designerCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                designerComboboxActionPerformed(evt);
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(designerCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(productComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(designerCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
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
        // Dapatkan produk yang dipilih dari JComboBox
        String selectedProduct = getSelectedProduct();

        // Pastikan produk yang dipilih tidak null
        if (selectedProduct != null) {
            // Panggil metode untuk mendapatkan data username designer berdasarkan produk
            List<String> designers = null;
            try {
                designers = servicePricing.getDesignersByProduct(selectedProduct);
            } catch (SQLException ex) {
                // Tangani kesalahan SQL
                ex.printStackTrace();
                // Tampilkan pesan kesalahan kepada pengguna jika diperlukan
                JOptionPane.showMessageDialog(this, "Error occurred while retrieving designers for the selected product.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Pastikan data username designer tidak null
            if (designers != null && !designers.isEmpty()) {
                // Bersihkan JComboBox designerCombobox
                designerCombobox.removeAllItems();

                // Tambahkan data username designer ke dalam JComboBox designerCombobox
                for (String designer : designers) {
                    designerCombobox.addItem(designer);
                }
            } else {
                // Jika data username designer tidak ditemukan, berikan pesan kepada pengguna
                JOptionPane.showMessageDialog(this, "No designers found for the selected product.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }

            // Perbarui tampilan harga berdasarkan produk yang dipilih
            displayPackageDetails(selectedProduct);
        } else {
            // Jika tidak ada produk yang dipilih, berikan pesan kepada pengguna
            JOptionPane.showMessageDialog(this, "Please select a product first.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_productComboBoxActionPerformed

    private void cmdBasicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBasicActionPerformed
        // Atur properti isSelected pada basicPricing menjadi true
        basicPricing.setIsSelected(true);
        // Atur properti isSelected pada standardPricing dan proPricing menjadi false
        standardPricing.setIsSelected(false);
        proPricing.setIsSelected(false);

        // Initialize the paymentGateway instance
        paymentGateaway = new PaymentGatewayUI(basicPricing, standardPricing, proPricing);

        // Panggil getTotalAmount untuk mendapatkan total amount yang diperlukan
        String totalAmount = paymentGateaway.getTotalAmount();

        // Membuat instance PaymentGatewayUI
        PaymentGatewayUI paymentGatewayUI = new PaymentGatewayUI(basicPricing, standardPricing, proPricing);
        // Memanggil metode di PaymentGatewayUI dengan informasi "Basic"
        paymentGatewayUI.handleBuy("Basic");
        // Menampilkan frame PaymentGatewayUI
        paymentGatewayUI.setVisible(true);
    }//GEN-LAST:event_cmdBasicActionPerformed

    private void cmdStandardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdStandardActionPerformed
        // Atur properti isSelected pada basicPricing menjadi true
        basicPricing.setIsSelected(false);
        // Atur properti isSelected pada standardPricing dan proPricing menjadi false
        standardPricing.setIsSelected(true);
        proPricing.setIsSelected(false);

        // Initialize the paymentGateway instance
        paymentGateaway = new PaymentGatewayUI(basicPricing, standardPricing, proPricing);

        // Panggil getTotalAmount untuk mendapatkan total amount yang diperlukan
        String totalAmount = paymentGateaway.getTotalAmount();

        // Membuat instance PaymentGatewayUI
        PaymentGatewayUI paymentGatewayUI = new PaymentGatewayUI(basicPricing, standardPricing, proPricing);
        // Memanggil metode di PaymentGatewayUI dengan informasi "Standard"
        paymentGatewayUI.handleBuy("Standard");
        // Menampilkan frame PaymentGatewayUI
        paymentGatewayUI.setVisible(true);
    }//GEN-LAST:event_cmdStandardActionPerformed

    private void cmdProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdProActionPerformed
        // Atur properti isSelected pada proPricing menjadi true
        proPricing.setIsSelected(true);
        // Atur properti isSelected pada basicPricing dan standardPricing menjadi false
        basicPricing.setIsSelected(false);
        standardPricing.setIsSelected(false);

        // Initialize the paymentGateway instance
        paymentGateaway = new PaymentGatewayUI(basicPricing, standardPricing, proPricing);

        // Panggil getTotalAmount untuk mendapatkan total amount yang diperlukan
        String totalAmount = paymentGateaway.getTotalAmount();

        // Membuat instance PaymentGatewayUI
        PaymentGatewayUI paymentGatewayUI = new PaymentGatewayUI(basicPricing, standardPricing, proPricing);
        // Memanggil metode di PaymentGatewayUI dengan informasi "Pro"
        paymentGatewayUI.handleBuy("Pro");
        // Menampilkan frame PaymentGatewayUI
        paymentGatewayUI.setVisible(true);
    }//GEN-LAST:event_cmdProActionPerformed

    private void designerComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_designerComboboxActionPerformed
//        // Mendapatkan desainer yang dipilih oleh pengguna
//        String selectedDesigner = (String) designerCombobox.getSelectedItem();
//
//        // Menampilkan informasi terkait dengan desainer yang dipilih
//        if (selectedDesigner != null) {
//            JOptionPane.showMessageDialog(this, "You have selected designer: " + selectedDesigner, "Designer Selection", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(this, "Please select a designer.", "Designer Selection", JOptionPane.INFORMATION_MESSAGE);
//        }
    }//GEN-LAST:event_designerComboboxActionPerformed

    // Metode untuk menampilkan PGMain
    private void showPGMain() {
        // Pastikan objek pgMain sudah diinisialisasi sebelumnya
        if (pgMain == null) {
            pgMain = new PGMain();  // Jangan lupa melewatkan referensi ke frame utama jika diperlukan
        }

        pgMain.setVisible(true);
    }

    public String getSelectedProduct() {
        return (String) productComboBox.getSelectedItem();
    }

    public PanelPricing getBasicPricing() {
        return basicPricing;
    }

    public PanelPricing getStandardPricing() {
        return standardPricing;
    }

    public PanelPricing getProPricing() {
        return proPricing;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private User.PlaceOrder.component.PanelPricing basicPricing;
    private User.PlaceOrder.swing.Button cmdBasic;
    private User.PlaceOrder.swing.Button cmdPro;
    private User.PlaceOrder.swing.Button cmdStandard;
    private javax.swing.JComboBox<String> designerCombobox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private User.PlaceOrder.component.PanelPricing proPricing;
    private javax.swing.JComboBox<String> productComboBox;
    private Dashboard.Swing.RoundPanel roundPanel1;
    private User.PlaceOrder.component.PanelPricing standardPricing;
    // End of variables declaration//GEN-END:variables
}
