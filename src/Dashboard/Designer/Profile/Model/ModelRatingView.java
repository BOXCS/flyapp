package Dashboard.Designer.Profile.Model;

public class ModelRatingView {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public ModelRatingView() {
    }

    public ModelRatingView(String username, String productName, int starCount, String feedback) {
        this.username = username;
        this.productName = productName;
        this.starCount = starCount;
        this.feedback = feedback;
    }

    private String username;
    private String productName;
    private int starCount;
    private String feedback;
}
