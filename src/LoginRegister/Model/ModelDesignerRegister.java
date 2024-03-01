package LoginRegister.Model;

import Dashboard.Admin.Model.ModelName;

public class ModelDesignerRegister {

    public int getDesignerID() {
        return designerID;
    }

    public void setDesignerID(int designerID) {
        this.designerID = designerID;
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

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ModelDesignerRegister(int designerID, String userName, String email, String password, String instagram, String typeContent, String status, String role) {
        this.designerID = designerID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.instagram = instagram;
        this.typeContent = typeContent;
        this.status = status;
        this.role = role;
    }
    
    public ModelDesignerRegister(int designerID, String userName, String email, String password, String instagram, String typeContent, String status) {
        this.designerID = designerID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.instagram = instagram;
        this.typeContent = typeContent;
        this.status = status;
    }

    private int designerID;
    private String userName;
    private String email;
    private String password;
    private String instagram;
    private String typeContent;
    private String status;
    private String role;
}
