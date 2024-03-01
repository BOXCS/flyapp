package Admin.AddDesigner.Service;

public class ModelDesignerLogin {

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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public ModelDesignerLogin(int designerID, String userName, String email, String password, String instagram, String typeContent, String other, String Status) {
        this.designerID = designerID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.instagram = instagram;
        this.typeContent = typeContent;
        this.other = other;
        this.Status = Status;
    }

    public ModelDesignerLogin() {
    }
    
    private int designerID;
    private String userName;
    private String email;
    private String password;
    private String instagram;
    private String typeContent;
    private String other;
    private String Status;
}
