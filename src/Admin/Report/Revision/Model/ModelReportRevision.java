package Admin.Report.Revision.Model;

import java.sql.Timestamp;

public class ModelReportRevision {

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String Level) {
        this.Level = Level;
    }

    public String getRevision() {
        return Revision;
    }

    public void setRevision(String Revision) {
        this.Revision = Revision;
    }

    public String getRevisionLeft() {
        return RevisionLeft;
    }

    public void setRevisionLeft(String RevisionLeft) {
        this.RevisionLeft = RevisionLeft;
    }

    public Timestamp getLastRevision() {
        return LastRevision;
    }

    public void setLastRevision(Timestamp LastRevision) {
        this.LastRevision = LastRevision;
    }

    public ModelReportRevision() {
    }

    public ModelReportRevision(String transactionNumber, String designerName, String productName, String Level, String Revision, String RevisionLeft, Timestamp LastRevision) {
        this.transactionNumber = transactionNumber;
        this.designerName = designerName;
        this.productName = productName;
        this.Level = Level;
        this.Revision = Revision;
        this.RevisionLeft = RevisionLeft;
        this.LastRevision = LastRevision;
    }

    private String transactionNumber;
    private String designerName;
    private String productName;
    private String Level;
    private String Revision;
    private String RevisionLeft;
    private Timestamp LastRevision;
}
