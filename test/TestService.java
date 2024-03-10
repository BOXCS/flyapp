
import User.PlaceOrder.Service.ServicePricing;
import User.PlaceOrder.model.Model_Data;
import java.sql.SQLException;
import java.util.List;

public class TestService {

    public static void main(String[] args) throws SQLException {
        // Membuat instansi ServicePricing
        ServicePricing servicePricing = new ServicePricing();

        // Mengganti dengan nama produk yang sesuai
        String productName = "Video Editing";

        // Mengganti dengan nama level yang sesuai
        String levelName = "Basic";

        // Memanggil metode getPackageItems untuk mendapatkan daftar item paket
        List<Model_Data> packageItems = servicePricing.getPackageItems(productName, levelName);

        // Menampilkan hasil pengambilan item paket
        for (Model_Data item : packageItems) {
            System.out.println("Item Name: " + item.getItemName() + ", Is Enabled: " + item.isStatus());
        }
    }
}
