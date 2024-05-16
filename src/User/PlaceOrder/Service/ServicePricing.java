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
import notif.Mail.MailNotification;

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
        String transactionType = generateTransactionType();
        String status = "Waiting";
        Timestamp createdAt = getCurrentTimestamp();

        String query = "INSERT INTO transaction (transaction_number, amount, status, created_at, type) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, transactionNumber);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, status);
            preparedStatement.setTimestamp(4, createdAt);
            preparedStatement.setString(5, transactionType);

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

    // Generate a transaction number (e.g., TSR-1234)
    private String generateTransactionType() {
        String prefix = "CRT-";
        int randomNumber = (int) (Math.random() * 10000); // Menghasilkan nomor acak antara 0 dan 9999

        // Menambahkan angka nol di depan jika nomor acaknya memiliki tiga digit atau kurang
        String transactionType = String.format("%04d", randomNumber);

        return prefix + transactionType;
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
        String transactionType = generateTransactionType();
        String status = "Waiting"; // Ensure status is set
        Timestamp createdAt = getCurrentTimestamp();

        String query = "INSERT INTO transaction (transaction_number, product_name, designer, level, amount, status, created_at, username, type) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, transactionNumber);
            preparedStatement.setString(2, productName);
            preparedStatement.setString(3, designer);
            preparedStatement.setString(4, level);
            preparedStatement.setDouble(5, price);
            preparedStatement.setString(6, status); // Ensure status is correctly included
            preparedStatement.setTimestamp(7, createdAt);
            preparedStatement.setString(8, user.getUserName());
            preparedStatement.setString(9, transactionType);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Update status designer
            if (rowsAffected > 0) {
                updateDesignerStatus(designer, "Unavailable");

                // Insert revision based on product name and level
                int revisionCount = getRevisionCount(productName, level);
                String revisionText = "No Revision";
                if (revisionCount > 0) {
                    insertNewRevision(transactionNumber, designer, productName, level, revisionText, revisionCount);
                } else {
                    System.out.println("Ran out of Revision");
                }

                // Retrieve designer's email
                String designerEmail = getDesignerEmail(designer);

                // Send email notification
                if (designerEmail != null) {
                    MailNotification mailNotif = new MailNotification();
                    String notificationSubject = "Notification";
                    String notificationMessage = user.getUserName() + " has placed an order with transaction number: " + transactionNumber;
                    mailNotif.sendNotification(designerEmail, notificationSubject, notificationMessage);
                }
            }

            // Check if the insertion was successful
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertCart(String productName, String designer, String level, double price, ModelUser user) {
        String transactionNumber = generateTransactionNumber();
        Timestamp createdAt = getCurrentTimestamp();

        String queryTransaction = "INSERT INTO transaction (transaction_number, product_name, designer, level, amount, status, created_at, username, type) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String queryCart = "INSERT INTO cart (transaction_number, username, product_name, designer, level, amount, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Memulai transaksi
            con.setAutoCommit(false);

            // Insert ke dalam tabel transactions
            try (PreparedStatement pTransaction = con.prepareStatement(queryTransaction)) {
                pTransaction.setString(1, transactionNumber);
                pTransaction.setString(2, productName);
                pTransaction.setString(3, designer);
                pTransaction.setString(4, level);
                pTransaction.setDouble(5, price);
                pTransaction.setString(6, "Carted");
                pTransaction.setTimestamp(7, createdAt);
                pTransaction.setString(8, user.getUserName());  // Perbaikan parameter
                pTransaction.setString(9, "Carted");  // Perbaikan parameter
                pTransaction.executeUpdate();
            }

            // Insert ke dalam tabel cart
            try (PreparedStatement pCart = con.prepareStatement(queryCart)) {
                pCart.setString(1, transactionNumber);
                pCart.setString(2, user.getUserName());
                pCart.setString(3, productName);
                pCart.setString(4, designer);
                pCart.setString(5, level);
                pCart.setDouble(6, price);
                pCart.setBoolean(7, false);
                pCart.executeUpdate();
            }

            // Commit transaksi
            con.commit();

            return true;
        } catch (Exception e) {
            // Rollback transaksi jika terjadi kesalahan
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            // Set auto-commit ke nilai default setelah transaksi selesai
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String getDesignerEmail(String designerUsername) {
        // Query to get the designer's email based on the designer's username
        String query = "SELECT email FROM designer WHERE username = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, designerUsername);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no email is found or an error occurs
    }

    private void updateDesignerStatus(String designer, String status) throws SQLException {
        String updateQuery = "UPDATE designer SET Status = ? WHERE username = ?";
        try (PreparedStatement updateStatement = con.prepareStatement(updateQuery)) {
            updateStatement.setString(1, status);
            updateStatement.setString(2, designer);
            updateStatement.executeUpdate();
        }
    }

//    public boolean insertRevision(String transactionNumber, String designer, String productName, String level, String revision, int revisionCount) {
//        // Check if the transaction number already exists in the revision table
//        if (isTransactionNumberExists(transactionNumber)) {
//            // If transaction number exists, update the existing revision
//            return updateExistingRevision(transactionNumber, revision, revisionCount);
//        } else {
//            // If transaction number doesn't exist, insert a new revision
//            return insertNewRevision(transactionNumber, designer, productName, level, revision, revisionCount);
//        }
//    }
    private boolean isTransactionNumberExists(String transactionNumber) {
        String query = "SELECT COUNT(*) AS count FROM revision WHERE transaction_number = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, transactionNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    private boolean updateExistingRevision(String transactionNumber, String revision, int revisionCount) {
//        String query = "UPDATE revision SET revision = ?, revision_count = ? WHERE transaction_number = ?";
//        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
//            preparedStatement.setString(1, revision);
//            preparedStatement.setInt(2, revisionCount); // Tidak mengurangi revisionCount di sini
//            preparedStatement.setString(3, transactionNumber);
//
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            // Update remaining revision count
//            if (rowsAffected > 0) {
//                // Perbarui remaining revision count setelah basis data berhasil diperbarui
//                updateRemainingRevisionCount(transactionNumber, --revisionCount);
//            }
//
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    public boolean insertNewRevision(String transactionNumber, String designer, String productName, String level, String revision, int revisionCount) {
        String query = "INSERT INTO revision (transaction_number, designer_name, product_name, level, revision, revision_count, revision_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, transactionNumber);
            preparedStatement.setString(2, designer);
            preparedStatement.setString(3, productName);
            preparedStatement.setString(4, level);
            preparedStatement.setString(5, revision);
            preparedStatement.setInt(6, revisionCount);
            preparedStatement.setTimestamp(7, getCurrentTimeRevision()); // Set revision_date

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Update remaining revision count
            if (rowsAffected > 0) {
                updateRemainingRevisionCount(transactionNumber, revisionCount);
            }

            // Check if the insertion was successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

// Method to get current timestamp
    private Timestamp getCurrentTimeRevision() {
        return new Timestamp(System.currentTimeMillis());
    }

    private void updateRemainingRevisionCount(String transactionNumber, int remainingRevisions) {
        String updateQuery = "UPDATE revision SET revision_count = ? WHERE transaction_number = ?";
        try (PreparedStatement updateStatement = con.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, remainingRevisions);
            updateStatement.setString(2, transactionNumber);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getRevisionCount(String productName, String level) {
        int revisionCount = 0;
        // Set revision count based on product name and level
        switch (productName) {
            case "Video Editing":
                switch (level) {
                    case "Basic":
                        revisionCount = 2;
                        break;
                    case "Standard":
                        revisionCount = 4;
                        break;
                    case "Pro":
                        revisionCount = Integer.MAX_VALUE; // Unlimited revisions
                        break;
                }
                break;
            case "Design Graphic":
                switch (level) {
                    case "Basic":
                        revisionCount = 2;
                        break;
                    case "Standard":
                        revisionCount = 5;
                        break;
                    case "Pro":
                        revisionCount = Integer.MAX_VALUE; // Unlimited revisions
                        break;
                }
                break;
            case "3D Modelling":
                switch (level) {
                    case "Basic":
                        revisionCount = 5;
                        break;
                    case "Standard":
                        revisionCount = 10;
                        break;
                    case "Pro":
                        revisionCount = Integer.MAX_VALUE; // Unlimited revisions
                        break;
                }
                break;
        }
        return revisionCount;
    }

    public int getRemainingRevisionCount(String transactionNumber) {
        int remainingRevisions = 0;
        String query = "SELECT revision_count FROM revision WHERE transaction_number = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, transactionNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                remainingRevisions = resultSet.getInt("revision_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return remainingRevisions;
    }

    private int getUsedRevisionCount(String transactionNumber) {
        int usedRevisions = 0;
        String query = "SELECT COUNT(*) AS used_revisions FROM revision WHERE transaction_number = ? AND revision != 'No Revision'";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, transactionNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                usedRevisions = resultSet.getInt("used_revisions");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usedRevisions;
    }

}
