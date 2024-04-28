package Admin.Report.Revision.Component;

import Admin.Report.Revision.Model.ModelReportRevision;
import Admin.Report.Revision.Service.ServiceReportRevision;

public class InfoTN extends javax.swing.JPanel {
    
    private ModelReportRevision reportRevision;
    private final ServiceReportRevision serviceReportRevision = new ServiceReportRevision();
    private final String transactionNumber;

    public InfoTN() {
        this.transactionNumber = null;
        initComponents();
        setOpaque(false);
    }

    public InfoTN(String transactionNumber) {
        this.transactionNumber = transactionNumber;
        initComponents();
        
        setOpaque(false);
        loadReportRevision(transactionNumber);
    }
    
    public void loadReportRevision(String transactionNumber) {
        reportRevision = serviceReportRevision.getRevisionData(transactionNumber);
        
        if (reportRevision != null) {
            lbTransactionNumb.setText(reportRevision.getTransactionNumber());
            lbDesigner.setText(reportRevision.getDesignerName());
            lbProduct.setText(reportRevision.getProductName());
            lbLevel.setText(reportRevision.getLevel());
            lbRevisionLeft.setText(reportRevision.getRevisionLeft());
            lbLastRevision.setText(reportRevision.getLastRevision().toString());
        } else {
            System.out.println("No data found");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Dashboard.Swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbDesigner = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbProduct = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbLevel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbRevisionLeft = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbLastRevision = new javax.swing.JLabel();
        lbTransactionNumb = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Transaction Number : ");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Designer Name :");

        lbDesigner.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbDesigner.setForeground(new java.awt.Color(255, 255, 255));
        lbDesigner.setText("Designer");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Product Name :");

        lbProduct.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbProduct.setForeground(new java.awt.Color(255, 255, 255));
        lbProduct.setText("Product");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Level :");

        lbLevel.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbLevel.setForeground(new java.awt.Color(255, 255, 255));
        lbLevel.setText("Level");

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Revision Left :");

        lbRevisionLeft.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbRevisionLeft.setForeground(new java.awt.Color(255, 255, 255));
        lbRevisionLeft.setText("Revision");

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Last Revision :");

        lbLastRevision.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbLastRevision.setForeground(new java.awt.Color(255, 255, 255));
        lbLastRevision.setText("Timestamp");

        lbTransactionNumb.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbTransactionNumb.setForeground(new java.awt.Color(255, 255, 255));
        lbTransactionNumb.setText("Transaction Number");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addComponent(lbTransactionNumb))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbDesigner))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbProduct))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbLevel))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRevisionLeft))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbLastRevision)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbTransactionNumb))
                .addGap(37, 37, 37)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbDesigner))
                .addGap(37, 37, 37)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbProduct))
                .addGap(37, 37, 37)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbLevel))
                .addGap(37, 37, 37)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbRevisionLeft))
                .addGap(37, 37, 37)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lbLastRevision))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lbDesigner;
    private javax.swing.JLabel lbLastRevision;
    private javax.swing.JLabel lbLevel;
    private javax.swing.JLabel lbProduct;
    private javax.swing.JLabel lbRevisionLeft;
    private javax.swing.JLabel lbTransactionNumb;
    private Dashboard.Swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
