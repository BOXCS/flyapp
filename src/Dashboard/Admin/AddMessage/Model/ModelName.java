package Dashboard.Admin.AddMessage.Model;

public class ModelName {

    private String userName;

    public ModelName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ModelName{"
                + "userName='" + userName + '\''
                + '}';
    }
}
