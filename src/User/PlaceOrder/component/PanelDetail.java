package User.PlaceOrder.component;

import PaymentGateaway.Component.PGDetail;
import PaymentGateaway.Model.Model_PG;
import PaymentGateaway.PGMain;
import PaymentGateaway.Test.PaymentGatewayUI;
import User.PlaceOrder.Main.PlaceOrderMain;
import User.PlaceOrder.Service.ServicePricing;
import User.PlaceOrder.model.Model_Data;
import User.PlaceOrder.shadow.ShadowRenderer;
import User.PlaceOrder.swing.PricingList;
import User.PlaceOrder.swing.Title;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class PanelDetail extends javax.swing.JPanel {

    private ServicePricing servicePricing;
    private PanelPricing panelPricing;
    private PlaceOrderMain POMain;
    private ActionListener event;
    private String category;
    private String level;

    private String selectedProduct;
    private String selectedLevel;

    public PanelDetail() {
        initComponents();
        setOpaque(false);
    }

    public PanelDetail(PlaceOrderMain POMain) {
        this.POMain = POMain;
        initComponents();
        setOpaque(false);
    }

    public void setSelectedProductAndLevel(String product, String level) {
        this.selectedProduct = product;
        this.selectedLevel = level;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        list = new User.PlaceOrder.swing.PricingList<>();
        jLayeredPane1 = new javax.swing.JLayeredPane();

        list.setBackground(new java.awt.Color(255, 255, 255));
        list.setForeground(new java.awt.Color(0, 0, 0));
        list.setOpaque(false);

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(276, 57));

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(list, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(list, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        list.getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    // Display the price in a dialog box
    private void displayPrice(String pricingType, double price) {
        // Perform actions with the retrieved price, for example, display a message
        JOptionPane.showMessageDialog(this, "You have selected " + pricingType + " with price: $" + price, "Purchase Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g = (Graphics2D) grphcs;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BufferedImage img = new BufferedImage(getWidth() - 10, 250, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = img.getWidth();
        int height = img.getHeight() - 1;
        int radius = 25;
        g2.setColor(new Color(250, 250, 250));
        Path2D.Float f = new Path2D.Float();
        f.moveTo(0, radius);
        f.curveTo(0, radius, 0, 0, radius, 0);
        f.lineTo(width - radius, 0);
        f.curveTo(width - radius, 0, width, 0, width - 5, radius);
        f.lineTo(width - 50, height - radius);
        f.curveTo(width - 50, height - radius, width - 50 - 5, height, width - radius - 60, height);
        f.lineTo(0, height);
        g2.fill(f);
        int px[] = {0, 25, 25};
        int py[] = {height, height + 30, height};
        g.setColor(new Color(250, 250, 250));
        g.fillPolygon(px, py, px.length);
        //  Create Shadow
        g.drawImage(new ShadowRenderer(10, 0.5f, Color.BLACK).createShadow(img), -5, 5, width + 10, height + 5, null);
        g.drawImage(img, 0, 0, null);
        super.paintComponent(grphcs);
    }

//    public swing.Button getCmdBuy() {
//        return cmdBuy;
//    }

    public PricingList getList() {
        return list;
    }

    public void clearItems() {
        // Clear items in the list
        list.clearItems();
    }

    // Method to set items into the JList in PanelDetail
    public void setListItems(List<Model_Data> packageItems) {
        try {
            // Clear existing items
            if (list != null) {
                list.clearItems();
            } else {
                System.out.println("Error: PricingList is null.");
                return;
            }

            // Add new items to the PricingList<Model_Data>
            DefaultListModel<Model_Data> model = (DefaultListModel<Model_Data>) list.getModel();

            // Use the setListItems method from PricingList to add items
            if (packageItems != null) {
                packageItems.forEach(model::addElement);
            } else {
                System.out.println("Error: packageItems is null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePricingList(ServicePricing servicePricing, String productName, String levelName) {
        // Memanggil ServicePricing untuk mendapatkan daftar item paket
        List<Model_Data> packageItems = servicePricing.getPackageItems(productName, levelName);

        // Menetapkan daftar item pada PricingList
        list.setListItems(packageItems);

        // Memanggil repaint untuk memperbarui tampilan
        list.repaint();
    }

    public void setPanelPricing(PanelPricing panelPricing) {
        this.panelPricing = panelPricing;
    }

    // Create a method to handle the Buy action with the retrieved price
    private void processBuyAction(double price) {
        // Perform actions with the retrieved price, for example, display a message
        JOptionPane.showMessageDialog(this, "You have selected a product with price: $" + price, "Purchase Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    private User.PlaceOrder.swing.PricingList<Model_Data> list;
    // End of variables declaration//GEN-END:variables
}
