package com.example.android.popularmovies.details.model;

/**
 * Created by dan.ariton on 08-Mar-17.
 */

public class MovieVideoDescriptor {

    private String id;
    private String title;
    private String site;
    private String key;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "MovieVideoDescriptor{" +
                "title=[" + title + ']' +
                ", site=[" + site + ']' +
                ", key=[" + key + ']' +
                '}';
    }
}
