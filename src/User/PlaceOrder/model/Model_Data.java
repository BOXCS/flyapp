package User.PlaceOrder.model;

public class Model_Data {

    private boolean status;
    private String description;
    private double price;
    private String level;
    private boolean isEnabled;  
    private String itemName;   

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Model_Data(boolean status, String description, double price, String level, boolean isEnabled, String itemName) {
        this.status = status;
        this.description = description;
        this.price = price;
        this.level = level;
        this.isEnabled = isEnabled;
        this.itemName = itemName;
    }

    public Model_Data(boolean status, String description) {
        this.status = status;
        this.description = description;
    }
    
    public Model_Data(String itemName, boolean isEnabled) {
        this.isEnabled = isEnabled;
        this.itemName = itemName;
    }

    public Model_Data() {
    }
}
