package Dashboard.Admin.Model;

import javax.swing.Icon;

public class ModelName {

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public Icon getProfile() {
        return profile;
    }

    public void setProfile(Icon profile) {
        this.profile = profile;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ModelName() {
    }

    public ModelName(String UserName, Icon profile, String path) {
        this.UserName = UserName;
        this.profile = profile;
        this.path = path;
    }

    private String UserName;
    private Icon profile;
    private String path;
    
    @Override
    public String toString() {
        return UserName;
    }
}
