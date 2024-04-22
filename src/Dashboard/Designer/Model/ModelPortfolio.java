package Dashboard.Designer.Model;

public class ModelPortfolio {

    public int getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(int portfolioId) {
        this.portfolioId = portfolioId;
    }

    public int getDesignerId() {
        return designerId;
    }

    public void setDesignerId(int designerId) {
        this.designerId = designerId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(byte[] mediaContent) {
        this.mediaContent = mediaContent;
    }

    public ModelPortfolio() {
    }

    public ModelPortfolio(int portfolioId, int designerId, String mediaType, byte[] mediaContent) {
        this.portfolioId = portfolioId;
        this.designerId = designerId;
        this.mediaType = mediaType;
        this.mediaContent = mediaContent;
    }

    private int portfolioId;
    private int designerId;
    private String mediaType;
    private byte[] mediaContent;
}
