package User.PlaceOrder.component;

import PaymentGateaway.Component.PGDetail;
import PaymentGateaway.PGMain;
import User.PlaceOrder.Main.PlaceOrderMain;
import User.PlaceOrder.model.Model_Data;
import User.PlaceOrder.swing.PricingList;
import User.PlaceOrder.swing.Title;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
//import java.util.List;
import javax.swing.DefaultListModel;

public class PanelPricing extends javax.swing.JPanel {

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    private PricingList<Model_Data> list = new PricingList();
    private PGDetail selectedPanelPricing;
    private PGMain pgMain;
    private PlaceOrderMain POMain;
    private boolean selected;
    private boolean isSelected;

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
        title1.setForeground(color1);
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
//        panelDetail.setButtonColor(color2);
    }

    public void addItem(Model_Data data) {
        panelDetail.getList().addItem(data);
    }

    public void clearItems() {
        panelDetail.getList().clearItems();
    }

    // Metode untuk memeriksa apakah panel pricing dipilih atau tidak
    public boolean isSelected() {
        return selected;
    }

    // Metode untuk menetapkan status pilihan panel pricing
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Title getTitle() {
        return title1;
    }

    public PanelPricing getselectedPanelPricing(PlaceOrderMain POMain) {
        return null;
    }

    public PanelPricing() {
        initComponents();
        setOpaque(false);
        setPreferredSize(new Dimension(300, 430));
//        panelDetail.setButtonColor(color2);
        title1.setForeground(color1);
    }
    private Color color1 = new Color(20, 203, 144);
    private Color color2 = new Color(17, 139, 40);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDetail = new User.PlaceOrder.component.PanelDetail(POMain);
        jLayeredPane1 = new javax.swing.JLayeredPane();
        lb1 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        title1 = new User.PlaceOrder.swing.Title();

        jLayeredPane1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        lb1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lb1.setForeground(new java.awt.Color(255, 255, 255));
        lb1.setText("$59.");
        jLayeredPane1.add(lb1);

        lb2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lb2.setForeground(new java.awt.Color(255, 255, 255));
        lb2.setText("99");
        lb2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        jLayeredPane1.add(lb2);

        jLayeredPane2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        title1.setForeground(new java.awt.Color(0, 0, 0));
        title1.setText("TITLE");
        jLayeredPane2.add(title1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLayeredPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(panelDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        int x = 25;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(g);
        g2.fillRoundRect(x, 0, getWidth() - x, getHeight(), 25, 25);
        fillBox(g2);
        super.paintComponent(grphcs);
    }

    private void fillBox(Graphics2D g2) {
        g2.setColor(new Color(255, 255, 255, 50));
        g2.fillOval(50, 25, 100, 100);
        g2.fillOval(getWidth() - 115, getHeight() - 115, 100, 100);
        g2.fillOval(getWidth() - 180, getHeight() - 50, 150, 150);
    }

    // Metode untuk menetapkan item-item ke dalam JList
    public void setListItems(List<Model_Data> packageItems) {
        try {
            // Clear existing items
            if (list != null) {
                list.clearItems();
            } else {
                System.out.println("Error: PricingList is null.");
                return;
            }

            // Add new items to the CustomList<Model_Data>
            DefaultListModel<Model_Data> model = (DefaultListModel<Model_Data>) list.getModel();

            // Gunakan metode setListItems dari PricingList untuk menambahkan item
            if (packageItems != null) {
                packageItems.forEach(model::addElement);
            } else {
                System.out.println("Warning: packageItems is null. No items to add.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PricingList<Model_Data> getList() {
        return list;
    }

    public void setPrice(String title, double price) {
        this.title1.setText(title);
        DecimalFormat df = new DecimalFormat("#,#00");
        String formattedPrice = df.format(price);
        String[] priceArray = formattedPrice.split("\\.");

        lb1.setText("$" + priceArray[0] + ".");
        lb2.setText(priceArray.length > 1 ? priceArray[1] : "");
    }

    // Metode untuk mendapatkan harga dari panel
    public double getPrice() {
        try {
            // Ambil nilai lb1 dan lb2 sebagai teks
            String lb1Text = lb1.getText().replaceAll("[^\\d.]", ""); // Hapus semua karakter kecuali digit dan titik
            String lb2Text = lb2.getText().replaceAll("[^\\d]", "");  // Hapus semua karakter kecuali digit

            // Gabungkan bagian lb1 dan lb2 untuk membentuk harga
            String priceText = lb1Text + (lb2Text.isEmpty() ? "" : "." + lb2Text);

            // Parsing teks harga menjadi tipe data double
            return Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            // Tangani kesalahan parsing
            e.printStackTrace();
            return 0.0; // Mengembalikan nilai default jika parsing gagal
        }
    }

    // Metode untuk menetapkan harga pada lb1 dan lb2
    public void setPriceComponents(String lb1Text, String lb2Text) {
        lb1.setText(lb1Text);
        lb2.setText(lb2Text);
    }

    public String getLb1() {
        return lb1.getText();
    }

    public String getLb2() {
        return lb2.getText();
    }

    // Tambahkan metode berikut di kelas PanelPricing
    public void setTitleText(String text) {
        title1.setText(text);
    }

    public void setPackageItems(List<Model_Data> packageItems) {
        // Menyimpan item-item ke dalam JList masing-masing level
        setListItems(packageItems);

        // Juga, Anda dapat menambahkan logika lain yang berkaitan dengan tampilan PanelDetail
        // Contoh: Menetapkan daftar item ke PanelDetail jika diperlukan
        panelDetail.setListItems(packageItems);
    }

    public List<Model_Data> getListItems() {
        DefaultListModel<Model_Data> model = (DefaultListModel<Model_Data>) list.getModel();
        List<Model_Data> items = new ArrayList<>();

        for (int i = 0; i < model.getSize(); i++) {
            items.add(model.getElementAt(i));
        }

        return items;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private User.PlaceOrder.component.PanelDetail panelDetail;
    private User.PlaceOrder.swing.Title title1;
    // End of variables declaration//GEN-END:variables

}
