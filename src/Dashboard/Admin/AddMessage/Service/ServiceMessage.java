package Dashboard.Admin.AddMessage.Service;

import Dashboard.Admin.AddMessage.Model.ModelMessage;
import Dashboard.Admin.AddMessage.Model.ModelName;
import LoginRegister.Model.ModelUser;
import connection.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceMessage {

    public void insertMessage(ModelMessage message) throws SQLException {
        String sql = "INSERT INTO messages(receiver_id, title, description, sent_at, delete_at, message_status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            p.setInt(1, message.getReceiverID());
            p.setString(2, message.getTitle());
            p.setString(3, message.getDescription());
            p.setTimestamp(4, new java.sql.Timestamp(message.getSentAt().getTime()));
            p.setDate(5, new java.sql.Date(message.getDeleteAt().getTime()));
            p.setString(6, message.getMessageStatus());
            p.executeUpdate();

            ResultSet r = p.getGeneratedKeys();
            if (r.first()) {
                int messageID = r.getInt(1);
                message.setMessageID(messageID);
            }
            r.close();
        }
    }

    public List<ModelName> loadDesigner() throws SQLException {
        List<ModelName> designers = new ArrayList<>();
        String sql = "SELECT username FROM designer";
        try (PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    String username = r.getString("username");
                    designers.add(new ModelName(username));
                }
            }
        }
        return designers;
    }

    public int getDesignerIDByUsername(String username) throws SQLException {
        String sql = "SELECT designer_id FROM designer WHERE username=?";
        try (PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            p.setString(1, username);
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    return r.getInt("designer_id");
                }
            }
        }
        return 0; // Return 0 jika tidak ditemukan
    }

    public List<ModelMessage> getAllMessages() throws SQLException {
        List<ModelMessage> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages";
        try (PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    int messageId = r.getInt("message_id");
                    int receiverId = r.getInt("receiver_id");
                    String title = r.getString("title");
                    String description = r.getString("description");
                    Date sentAt = r.getTimestamp("sent_at");
                    Date deleteAt = r.getDate("delete_at");
                    String messageStatus = r.getString("message_status");
                    messages.add(new ModelMessage(messageId, receiverId, title, description, sentAt, deleteAt, messageStatus));
                }
            }
        }
        return messages;
    }

    public void deleteMessage(int messageId) throws SQLException {
        String sql = "DELETE FROM messages WHERE message_id=?";
        try (PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            p.setInt(1, messageId);
            p.executeUpdate();
        }
    }

    public ModelMessage getMessageById(int messageId) throws SQLException {
        String sql = "SELECT * FROM messages WHERE message_id=?";
        try (PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            p.setInt(1, messageId);
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    int receiverId = r.getInt("receiver_id");
                    String title = r.getString("title");
                    String description = r.getString("description");
                    Date sentAt = r.getTimestamp("sent_at");
                    Date deleteAt = r.getDate("delete_at");
                    String messageStatus = r.getString("message_status");
                    return new ModelMessage(messageId, receiverId, title, description, sentAt, deleteAt, messageStatus);
                }
            }
        }
        return null; // Return null jika tidak ditemukan
    }

    public ModelMessage getMessageByUser(ModelUser user) throws SQLException {
        String sql = "SELECT * FROM messages WHERE receiver_id = (SELECT designer_id FROM designer WHERE username = ?)";
        try (PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            p.setString(1, user.getUserName());
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    int messageId = r.getInt("message_id");
                    int receiverId = r.getInt("receiver_id");
                    String title = r.getString("title");
                    String description = r.getString("description");
                    Date sentAt = r.getTimestamp("sent_at");
                    Date deleteAt = r.getDate("delete_at");
                    String messageStatus = r.getString("message_status");
                    return new ModelMessage(messageId, receiverId, title, description, sentAt, deleteAt, messageStatus);
                }
            }
        }
        return null; // Return null jika tidak ditemukan
    }

    // Tambahkan metode baru untuk mendapatkan semua desainer dengan ID mereka
    public List<ModelName> getAllDesignersWithId() throws SQLException {
        List<ModelName> designers = new ArrayList<>();
        String sql = "SELECT designer_id, username FROM designer";
        try (PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    int id = r.getInt("designer_id");
                    String username = r.getString("username");
                    designers.add(new ModelName(id, username));
                }
            }
        }
        return designers;
    }

    public List<ModelMessage> getAllMessagesByUser(ModelUser user) throws SQLException {
        List<ModelMessage> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE receiver_id = (SELECT designer_id FROM designer WHERE username = ?)";
        try (PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            p.setString(1, user.getUserName());
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    int messageId = r.getInt("message_id");
                    int receiverId = r.getInt("receiver_id");
                    String title = r.getString("title");
                    String description = r.getString("description");
                    Date sentAt = r.getTimestamp("sent_at");
                    Date deleteAt = r.getDate("delete_at");
                    String messageStatus = r.getString("message_status");
                    messages.add(new ModelMessage(messageId, receiverId, title, description, sentAt, deleteAt, messageStatus));
                }
            }
        }
        return messages;
    }

}
