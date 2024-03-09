
package User.PlaceOrder.model;

import java.util.List;

public class ModelItem {

    private int productID;
    private String productName;
    private List<Model_Data> pricingData;  // Menambahkan List<Model_Data> untuk menyimpan data pricing

    public ModelItem(int productID, String productName) {
        this.productID = productID;
        this.productName = productName;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Model_Data> getPricingData() {
        return pricingData;
    }

    public void setPricingData(List<Model_Data> pricingData) {
        this.pricingData = pricingData;
    }
    
}
