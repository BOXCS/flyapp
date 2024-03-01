package User.Model;

public class ModelProduct {

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductPackage() {
        return productPackage;
    }

    public void setProductPackage(String productPackage) {
        this.productPackage = productPackage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public ModelProduct(int productID, String productType, String productPackage, String price, String description, int adminID) {
        this.productID = productID;
        this.productType = productType;
        this.productPackage = productPackage;
        this.price = price;
        this.description = description;
        this.adminID = adminID;
    }

    public ModelProduct(int productID, String productType, String productPackage, String price, String description) {
        this.productID = productID;
        this.productType = productType;
        this.productPackage = productPackage;
        this.price = price;
        this.description = description;
    }
    
    public ModelProduct(int productID, String productPackage, String price, String description) {
        this.productID = productID;
        this.productPackage = productPackage;
        this.price = price;
        this.description = description;
    }
    
    private int productID;
    private String productType;
    private String productPackage;
    private String price;
    private String description;
    private int adminID;
}
