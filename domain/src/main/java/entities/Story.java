package entities;

import java.util.Date;
import java.util.List;

/**
 * Created by evgeniy.yanev on 1/7/16.
 */
public class Story {
    long registrationUser;
    long createdByUser;
    Date createdDate;
    String text;
    List<String> attachmentUrls;

    public long getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(long createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getAttachmentUrls() {
        return attachmentUrls;
    }

    public void setAttachmentUrls(List<String> attachmentUrls) {
        this.attachmentUrls = attachmentUrls;
    }

    public long getRegistrationUser() {
        return registrationUser;
    }

    public void setRegistrationUser(long registrationUser) {
        this.registrationUser = registrationUser;
    }
}
