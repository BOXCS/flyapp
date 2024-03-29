package User.PlaceOrder.Service;

import LoginRegister.Model.ModelUser;
import PaymentGateaway.Model.Model_PG;
import User.PlaceOrder.model.ModelItem;
import User.PlaceOrder.model.Model_Data;
import connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServicePricing {

    private final Connection con;

    public ServicePricing() throws SQLException {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.connectToDatabase();
        con = databaseConnection.getConnection();
    }

    public double getPrice(String productName, String levelName) {
        double price = 0.0;
        String query = "SELECT price_value FROM price "
                + "JOIN Product ON price.product_id = Product.product_id "
                + "JOIN packagelevel ON price.level_id = packagelevel.level_id "
                + "WHERE Product.product_name = ? AND packagelevel.level_name = ?";

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
        String query = "SELECT item_name, is_enabled FROM packageitem "
                + "JOIN product ON packageitem.product_id = product.product_id "
                + "JOIN packagelevel ON packageitem.level_id = packagelevel.level_id "
                + "WHERE product.product_name = ? AND packagelevel.level_name = ?";

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

        // Debugging statements
//    System.out.println("Debugging - Query: " + query);
//    System.out.println("Debugging - Product Name: " + productName);
//    System.out.println("Debugging - Level Name: " + levelName);
//    System.out.println("Debugging - Number of items retrieved: " + packageItems.size());
        return packageItems;
    }

    public Model_PG getProductInfo(String productName, String levelName) {
        double price = getPrice(productName, levelName);
        List<Model_Data> packageItems = getPackageItems(productName, levelName);

        // Debugging statement
        System.out.println("Debugging - Product Name: " + productName);
        System.out.println("Debugging - Level Name: " + levelName);
        System.out.println("Debugging - Price: " + price);
        System.out.println("Debugging - Number of package items: " + packageItems.size());

        return new Model_PG(productName, levelName, price, packageItems);
    }

    // Metode untuk melakukan transaksi dan menyimpannya ke dalam tabel transaction
    public boolean placeOrder(String productName, String levelName, double price) {
        String transactionNumber = generateTransactionNumber();
        String status = "Pending";
        Timestamp createdAt = getCurrentTimestamp();

        String query = "INSERT INTO transaction (transaction_number, amount, status, created_at) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, transactionNumber);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, status);
            preparedStatement.setTimestamp(4, createdAt);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the insertion was successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Generate a transaction number (e.g., TSR-1234)
    private String generateTransactionNumber() {
        String prefix = "TRS-";
        int randomNumber = (int) (Math.random() * 10000); // Menghasilkan nomor acak antara 0 dan 9999

        // Menambahkan angka nol di depan jika nomor acaknya memiliki tiga digit atau kurang
        String transactionNumber = String.format("%04d", randomNumber);

        return prefix + transactionNumber;
    }

    private String[] getColumnList(String columnName, String tableName, String condition) {
        List<String> columnList = new ArrayList<>();
        String query = "SELECT " + columnName + " FROM " + tableName + " WHERE " + condition;

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                columnList.add(resultSet.getString(columnName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return columnList.toArray(new String[0]);
    }

    // Get the current timestamp
    private Timestamp getCurrentTimestamp() {
        Date currentDate = new Date();
        return new Timestamp(currentDate.getTime());
    }

    public List<String> getDesigner(String category, String typeContent) {
        List<String> designerList = new ArrayList<>();
        String query = "SELECT username FROM designer WHERE typeContent = ? AND Status = 'Available'";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                designerList.add(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return designerList;
    }

    public List<String> getDesignersByProduct(String productName) throws SQLException {
        List<String> designers = new ArrayList<>();

        // Dapatkan kategori atau jenis konten dari produk
        String category = getCategoryByProduct(productName);

        // Pastikan kategori tidak kosong
        if (category != null && !category.isEmpty()) {
            // Dapatkan daftar desainer berdasarkan kategori dan status yang tersedia
            designers = getDesigner(category, "Available");
        } else {
            System.out.println("Error: Category not found for the product: " + productName);
        }

        return designers;
    }

    private String getCategoryByProduct(String productName) {
        String category = null;
        String query = "SELECT category FROM product WHERE product_name = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = resultSet.getString("category");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    public boolean insertOrder(String productName, String designer, String level, double price, ModelUser user) {
        String transactionNumber = generateTransactionNumber();
        String status = "Pending";
        Timestamp createdAt = getCurrentTimestamp();

        String query = "INSERT INTO transaction (transaction_number, product_name, designer, level, amount, status, created_at, username) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, transactionNumber);
            preparedStatement.setString(2, productName);
            preparedStatement.setString(3, designer);
            preparedStatement.setString(4, level);
            preparedStatement.setDouble(5, price);
            preparedStatement.setString(6, status);
            preparedStatement.setTimestamp(7, createdAt);
            preparedStatement.setString(8, user.getUserName()); // Mengambil username dari objek ModelUser

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the insertion was successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
