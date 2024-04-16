package Dashboard.Designer.Model;

import java.sql.Timestamp;

public class ModelDesigner {

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(double successRate) {
        this.successRate = successRate;
    }

    public ModelDesigner() {
    }

    public ModelDesigner(String typeContent, String instagram, String status, double avgRating, double successRate) {
        this.typeContent = typeContent;
        this.instagram = instagram;
        this.status = status;
        this.avgRating = avgRating;
        this.successRate = successRate;
    }

    private String typeContent;
    private String instagram;
    private String status;
    private double avgRating;
    private double successRate;
}
