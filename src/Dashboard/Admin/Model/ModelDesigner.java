package Dashboard.Admin.Model;

import com.raven.table.model.TableRowData;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModelDesigner extends TableRowData{

    public int getDesignerID() {
        return designerID;
    }

    public void setDesignerID(int designerID) {
        this.designerID = designerID;
    }

    public ModelName getName() {
        return name;
    }

    public void setName(ModelName name) {
        this.name = name;
    }

    public String getInstagram() {
        return Instagram;
    }

    public void setInstagram(String Instagram) {
        this.Instagram = Instagram;
    }

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ModelDesigner() {
    }

    public ModelDesigner(int designerID, ModelName name, String Instagram, String typeContent, String Status, Date date) {
        this.designerID = designerID;
        this.name = name;
        this.Instagram = Instagram;
        this.typeContent = typeContent;
        this.Status = Status;
        this.date = date;
    }

    public String getFormattedDate() {
        // Format the date as a string
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
    
    private int designerID;
    private ModelName name;
    private String Instagram;
    private String typeContent;
    private String Status;
    private Date date;

    @Override
    public Object[] toTableRow() {
        return new Object[]{name, Instagram, typeContent, Status, date};
    }
}
