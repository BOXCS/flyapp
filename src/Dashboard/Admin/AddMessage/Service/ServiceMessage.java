package Dashboard.Admin.AddMessage.Service;

import Dashboard.Admin.AddMessage.Model.ModelMessage;
import Dashboard.Admin.AddMessage.Model.ModelName;
import connection.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

}
