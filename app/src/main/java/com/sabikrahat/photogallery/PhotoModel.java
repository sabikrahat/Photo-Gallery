package com.sabikrahat.photogallery;

public class PhotoModel {
    String url = "";
    String description = "";

    public PhotoModel() {
    }

    public PhotoModel(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
