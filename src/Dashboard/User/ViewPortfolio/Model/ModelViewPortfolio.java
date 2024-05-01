package Dashboard.User.ViewPortfolio.Model;

public class ModelViewPortfolio {

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(byte[] mediaContent) {
        this.mediaContent = mediaContent;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public ModelViewPortfolio(int portfolioId, int designerId, String username, byte[] mediaContent, String mediaType) {
        this.portfolioId = portfolioId;
        this.designerId = designerId;
        this.username = username;
        this.mediaContent = mediaContent;
        this.mediaType = mediaType;
    }

    public ModelViewPortfolio() {
    }

    private int portfolioId;
    private int designerId;
    private String username;
    private byte[] mediaContent;
    private String mediaType;
}
