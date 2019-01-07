package com.sharma.ravi.top10downloader;

/**
 * Created by Ravi Sharma Local on 12/25/2016.
 */

public class FeedEntry {

    private String name;
    private String artist;
    private String summary;
    private String imageURL;
    private String releaseDate;


    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return ", name=" + name + '\n' +
                ", artist=" + artist + '\n' +
                ", releaseDate='" + releaseDate + '\n' +
                ", imageURL='" + imageURL + '\n' ;
    }
}
