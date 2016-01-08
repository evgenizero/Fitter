package com.fitter.model;

import java.util.Date;
import java.util.List;

import entities.User;

/**
 * Created by evgeniy.yanev on 1/8/16.
 */
public class StoryModel {
    User registrationUser;
    User createdByUser;
    Date createdDate;
    String text;
    List<String> attachmentUrls;

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
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

    public User getRegistrationUser() {
        return registrationUser;
    }

    public void setRegistrationUser(User registrationUser) {
        this.registrationUser = registrationUser;
    }
}
