package Dashboard.Admin.AddMessage.Model;

import java.util.Date;

public class ModelMessage {

    private int messageID;
    private int receiverID;
    private String title;
    private String description;
    private Date sentAt;
    private Date deleteAt;
    private String messageStatus;

    public ModelMessage(int receiverID, String title, String description, Date sentAt, Date deleteAt, String messageStatus) {
        this.receiverID = receiverID;
        this.title = title;
        this.description = description;
        this.sentAt = sentAt;
        this.deleteAt = deleteAt;
        this.messageStatus = messageStatus;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public ModelMessage() {
    }

    public ModelMessage(int messageID, int receiverID, String title, String description, Date sentAt, Date deleteAt, String messageStatus) {
        this.messageID = messageID;
        this.receiverID = receiverID;
        this.title = title;
        this.description = description;
        this.sentAt = sentAt;
        this.deleteAt = deleteAt;
        this.messageStatus = messageStatus;
    }

    @Override
    public String toString() {
        return "ModelMessage{"
                + "messageID=" + messageID
                + ", receiverID=" + receiverID
                + ", title='" + title + '\''
                + ", description='" + description + '\''
                + ", sentAt=" + sentAt
                + ", deleteAt=" + deleteAt
                + ", messageStatus='" + messageStatus + '\''
                + '}';
    }
}
