package com.eribeiro.volleysimples.models;

import java.util.Date;

/**
 * Created by cassiano on 26/03/16.
 */
public class FeedItemModel {
    private String post_title, post_description, post_date;
    private int resource_photo_id;
    private Date dateData;

    public FeedItemModel(String post_title, String post_description, String date, int resource_photo_id) {
        this.post_title = post_title;
        this.post_description = post_description;
        this.post_date = date;
        this.resource_photo_id = resource_photo_id;
    }

    public String getPostTitle() {
        return post_title;
    }

    public void setPostTitle(String post_title) {
        this.post_title = post_title;
    }

    public String getPostDescription() {
        return post_description;
    }

    public void setPostDescription(String post_description) {
        this.post_description = post_description;
    }

    public String getPostDate() {
        return post_date;
    }

    public void setPostDate(String post_date) {
        this.post_date = post_date;
    }

    public Date getDateData() {
        return dateData;
    }

    public void setDateData(Date dateData) {
        this.dateData = dateData;
    }

    public int getResource_photo_id() {
        return resource_photo_id;
    }

    public void setResource_photo_id(int resource_photo_id) {
        this.resource_photo_id = resource_photo_id;
    }
}
