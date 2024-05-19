package Dashboard.Admin.AddMessage.Model;

public class ModelName {

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    private String userName;
    private int Id;

    public ModelName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ModelName(int Id, String userName) {
        this.Id = Id;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ModelName{"
                + "userName='" + userName + '\''
                + '}';
    }
}
