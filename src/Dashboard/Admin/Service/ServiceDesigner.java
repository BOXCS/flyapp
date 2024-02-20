package Dashboard.Admin.Service;

import Dashboard.Admin.Model.ModelDesigner;
import Dashboard.Admin.Model.ModelName;
import connection.DatabaseConnection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ServiceDesigner {

    public void insertDesigner(ModelDesigner data) throws SQLException, IOException {
        String sql = "INSERT INTO designer(`username`, `instagram`, `TypeContent`, `Status`, `date`, `profile`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        p.setString(1, data.getName().getUserName());
        p.setString(2, data.getInstagram());
        p.setString(3, data.getTypeContent());
        p.setString(4, data.getStatus());
        p.setDate(5, new java.sql.Date(data.getDate().getTime()));
        if (data.getName().getPath().equals("")) {
            p.setObject(6, null);
        } else {
            p.setBlob(6, Files.newInputStream(new File(data.getName().getPath()).toPath()));
        }
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        r.first();
        int designerID = r.getInt(1);
        data.setDesignerID(designerID);
        r.close();
        p.close();
    }

    public void updateDesigner(ModelDesigner data) throws SQLException, IOException {
        if (data.getName().getPath().equals("Image")) {
            // user no update Image
            String sql = "UPDATE designer SET username=?, instagram=?, TypeContent=?, Status=?, date=? WHERE designerID=? LIMIT 1";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, data.getName().getUserName());
            p.setString(2, data.getInstagram());
            p.setString(3, data.getTypeContent());
            p.setString(4, data.getStatus());
            p.setDate(5, new java.sql.Date(data.getDate().getTime()));
            p.setInt(6, data.getDesignerID());
            p.execute();
            p.close();
        } else {
            // user update image
            String sql = "UPDATE designer SET UserName=?, Discord=?, typeContent=?, Details=?, date=?, Profile=? WHERE designerID=? LIMIT 1";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, data.getName().getUserName());
            p.setString(2, data.getInstagram());
            p.setString(3, data.getTypeContent());
            p.setString(4, data.getStatus());
            p.setDate(5, new java.sql.Date(data.getDate().getTime()));

            if (data.getName().getPath().equals("")) {
                p.setObject(6, null);
            } else {
                p.setBlob(6, Files.newInputStream(new File(data.getName().getPath()).toPath()));
            }
            p.setInt(7, data.getDesignerID());
            p.execute();
            p.close();
        }
    }

    public List<ModelDesigner> getDesigner() throws SQLException {
        List<ModelDesigner> list = new ArrayList<>();
        String sql = "SELECT * FROM designer";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int designerID = r.getInt("designerID");
            String userName = r.getString("username");
            Icon profile = null;
            if (r.getObject("profile") != null) {
                profile = new ImageIcon(r.getBytes("profile"));
            }
            String projectname = r.getString("instagram");
            String typeContent = r.getString("TypeContent");
            String aboutProject = r.getString("Status");
            Date date = r.getDate("date");
            list.add(new ModelDesigner(designerID, new ModelName(userName, profile, "Image"), projectname, typeContent, aboutProject, date));
        }
        r.close();
        p.close();
        return list;
    }

    public void deleteDesigner(int designerID) throws SQLException {
        String sql = "DELETE FROM designer WHERE designerID=? LIMIT 1";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setInt(1, designerID);
        p.execute();
        p.close();
    }

    public boolean isUserNameExists(String userName) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM designer WHERE `username`=?";
        try (PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {
            p.setString(1, userName);
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    int count = r.getInt("count");
                    return count > 0;
                }
            }
        }
        return false;
    }
}
