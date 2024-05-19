package LoginRegister.Model;

public class ModelUser {

    private int userID;
    private String userName;
    private String email;
    private String password;
    private String verifyCode;
    private String role;

    public ModelUser(int userID, String userName, String email, String password, String verifyCode, String role) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.verifyCode = verifyCode;
        this.role = role;
    }

    public ModelUser(int userID, String userName, String email, String role) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.role = role;
    }

    public ModelUser(String password, String userName, String email, int userID) {
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.userID = userID;
    }

    public ModelUser(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    public ModelUser(String userName) {
        this.userName = userName;
    }

    public ModelUser() {
    }

    // getters and setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
