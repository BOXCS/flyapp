package User.PlaceOrder.model;

public class Model_Data {

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    private boolean status;
    private double price;
    private String level;
    private String itemName;
    private String product;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Model_Data(boolean status, double price, String level, String itemName) {
        this.status = status;
        this.price = price;
        this.level = level;
        this.itemName = itemName;
    }

    public Model_Data(boolean status, String itemName) {
        this.status = status;
        this.itemName = itemName;
    }

    public Model_Data(String itemName) {
        this.itemName = itemName;
    }

    public Model_Data() {
    }
}
