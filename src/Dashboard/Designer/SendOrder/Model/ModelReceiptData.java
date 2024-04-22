package Dashboard.Designer.SendOrder.Model;

import java.sql.Timestamp;

public class ModelReceiptData {

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ModelReceiptData() {
    }

    public ModelReceiptData(String transactionNumber, String productName, String designer, String level, double amount, Timestamp createdAt, String username) {
        this.transactionNumber = transactionNumber;
        this.productName = productName;
        this.designer = designer;
        this.level = level;
        this.amount = amount;
        this.createdAt = createdAt;
        this.username = username;
    }

    private String transactionNumber;
    private String productName;
    private String designer;
    private String level;
    private double amount;
    private Timestamp createdAt;
    private String username;
}
