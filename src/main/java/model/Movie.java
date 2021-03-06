package model;

import java.util.Objects;

public class Movie {
    private String uploader;
    private String title;
    private String launchyear;
    private String genre;
    private String url;
    private String description;

    public Movie() {
    }

    public Movie(String uploader, String title, String launchyear, String genre, String url, String description) {
        this.uploader = uploader;
        this.title = title;
        this.launchyear = launchyear;
        this.genre = genre;
        this.url = url;
        this.description = description;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLaunchyear() {
        return launchyear;
    }

    public void setLaunchyear(String launchyear) {
        this.launchyear = launchyear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    @Override
    public String toString() {
        return "Movie{" +
                "uploader='" + uploader + '\'' +
                ", title='" + title + '\'' +
                ", launchyear='" + launchyear + '\'' +
                ", genre='" + genre + '\'' +
                ", url='" + url +
                ", description='" + description +
                "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(uploader, movie.uploader);
    }


}
