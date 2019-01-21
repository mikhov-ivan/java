package com.mt452.sponsored;

/**
 * Created by mt452 on 03.01.2019.
 */
public class Slot3 implements Slot {
    String title;
    String description;
    String url;

    public Slot3(String title, String url) {
        this.title = title;
        this.url = url;
        description = "";
    }

    public Slot3(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        String result = "[" + title + "]";
        if (description.length() > 0) result += " " + description;
        result += " -> " + url;
        return result;
    }
}
