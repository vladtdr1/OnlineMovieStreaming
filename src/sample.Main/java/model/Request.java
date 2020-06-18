package model;

import java.util.Objects;

public class Request {
    private String viewer;
    private String title;
    private String launchyear;

    public Request() {
    }

    public Request(String viewer, String title, String launchyear) {
        this.viewer = viewer;
        this.title = title;
        this.launchyear = launchyear;
    }

    public String getViewer() {
        return viewer;
    }

    public void setViewer(String viewer) { this.viewer = viewer; }

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


    @Override
    public String toString() {
        return "Request{" +
                "viewer='" + viewer + '\'' +
                ", title='" + title + '\'' +
                ", launchyear='" + launchyear + '\'' +
                "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(title, request.title);
    }
}
