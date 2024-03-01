package User.PlaceOrder.Main;

import User.Model.ModelProduct;
import java.util.HashMap;
import java.util.Map;

public class PlaceOrderMain extends javax.swing.JPanel {

    Map<String, Runnable> categoryActions;
    
    public PlaceOrderMain() {
        initComponents();
        setOpaque(false);
        
        categoryActions = new HashMap<>();
        categoryActions.put("Video Editing", this::VideoEditing);
        categoryActions.put("Design Graphic", this::DesignGraphic);
        categoryActions.put("3D Modelling", this::Modelling3D);

    }

    private void VideoEditing() {
        Basic.setData(new ModelProduct(1, "Basic Package", "$10", "Leaning java swing ui design like and Subscribe thank for watch"));
        Standard.setData(new ModelProduct(2, "Standard Package", "$30", "Standard Editing works"));
        Premium.setData(new ModelProduct(3, "Premium Package", "$75", "Complex Editing works"));
    }

    private void DesignGraphic() {
        Basic.setData(new ModelProduct(1, "Basic Package", "$10", "Simple Design works"));
        Standard.setData(new ModelProduct(2, "Standard Package", "$25", "Standard Design works"));
        Premium.setData(new ModelProduct(3, "Premium Package", "$50", "Complex Design works"));
    }

    private void Modelling3D() {
        Basic.setData(new ModelProduct(1, "Basic Package", "$25", "Simple Modelling works"));
        Standard.setData(new ModelProduct(2, "Standard Package", "$75", "Standard Modelling works"));
        Premium.setData(new ModelProduct(3, "Premium Package", "$100", "Complex Modelling works"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        roundPanel1 = new Dashboard.Swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        Basic = new Dashboard.Swing.scroll.ProductCard();
        Standard = new Dashboard.Swing.scroll.ProductCard();
        Premium = new Dashboard.Swing.scroll.ProductCard();
        jLabel1 = new javax.swing.JLabel();
        combobox = new javax.swing.JComboBox<>();

        jPanel1.setOpaque(false);

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));
        roundPanel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Package");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(Basic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130)
                        .addComponent(Standard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(Premium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(540, 540, 540)
                        .addComponent(jLabel2)))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Premium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Standard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Basic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Place Order");

        combobox.setBackground(new java.awt.Color(255, 255, 255));
        combobox.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        combobox.setForeground(new java.awt.Color(0, 0, 0));
        combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Video Editing", "Design Graphic", "3D Modelling" }));
        combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxActionPerformed(evt);
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
                        .addComponent(jLabel1)
                        .addGap(0, 1043, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(488, 488, 488)
                .addComponent(combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(110, 110, 110)
                .addComponent(combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
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

    private void comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxActionPerformed
        String selectedCategory = (String) combobox.getSelectedItem();

    // Use a map for cleaner and more maintainable code:
    Map<String, Runnable> actionMap = new HashMap<>();
    actionMap.put("Video Editing", () -> VideoEditing());
    actionMap.put("Design Graphic", () -> DesignGraphic());
    actionMap.put("3D Modelling", () -> Modelling3D());

    // Get the appropriate action based on the selected item:
    Runnable action = actionMap.get(selectedCategory);

    // Execute the action if it exists, otherwise handle the default case:
    if (action != null) {
        action.run();
    } else {
        // Handle the default case here, e.g., display an error message or do nothing
        System.out.println("Invalid category selected: " + selectedCategory);
    }
    }//GEN-LAST:event_comboboxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboard.Swing.scroll.ProductCard Basic;
    private Dashboard.Swing.scroll.ProductCard Premium;
    private Dashboard.Swing.scroll.ProductCard Standard;
    private javax.swing.JComboBox<String> combobox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private Dashboard.Swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
