package PaymentGateaway.Model;

import User.PlaceOrder.model.Model_Data;
import java.util.List;

public class Model_PG {

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Model_Data> getPackageItems() {
        return packageItems;
    }

    public void setPackageItems(List<Model_Data> packageItems) {
        this.packageItems = packageItems;
    }

    public Model_PG(String productName, String levelName, double price, List<Model_Data> packageItems) {
        this.productName = productName;
        this.levelName = levelName;
        this.price = price;
        this.packageItems = packageItems;
    }
    
    private String productName;
    private String levelName;
    private double price;
    private List<Model_Data> packageItems;
}
