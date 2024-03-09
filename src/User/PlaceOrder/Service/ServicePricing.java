package User.PlaceOrder.Service;

import User.PlaceOrder.model.ModelItem;
import User.PlaceOrder.model.Model_Data;
import connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicePricing {
    private final Connection con;

    public ServicePricing() {
        con = (Connection) DatabaseConnection.getInstance().getConnection();
    }
    
    public double getPrice(String productName, String levelName) {
        double price = 0.0;
        String query = "SELECT price_value FROM price " +
                       "JOIN Product ON price.product_id = Product.product_id " +
                       "JOIN packagelevel ON price.level_id = packagelevel.level_id " +
                       "WHERE Product.product_name = ? AND packagelevel.level_name = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, levelName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                price = resultSet.getDouble("price_value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }

    // Metode untuk mendapatkan daftar produk
    public String[] getProductList() {
        List<String> productList = new ArrayList<>();
        String query = "SELECT product_name FROM product";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productList.add(resultSet.getString("product_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList.toArray(new String[0]);
    }

    // Metode untuk mendapatkan daftar level paket
    public String[] getLevelList(String product) {
        List<String> levelList = new ArrayList<>();
        String query = "SELECT level_name FROM packagelevel";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                levelList.add(resultSet.getString("level_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return levelList.toArray(new String[0]);
    }
    
    public List<Model_Data> getPackageItems(String productName, String levelName) {
        List<Model_Data> packageItems = new ArrayList<>();
        String query = "SELECT item_name, is_enabled FROM packageitem " +
                       "JOIN product ON packageitem.product_id = product.product_id " +
                       "JOIN packagelevel ON packageitem.level_id = packagelevel.level_id " +
                       "WHERE product.product_name = ? AND packagelevel.level_name = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, levelName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                boolean isEnabled = resultSet.getBoolean("is_enabled");
                String itemName = resultSet.getString("item_name");
                packageItems.add(new Model_Data(isEnabled, itemName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return packageItems;
    }
}
