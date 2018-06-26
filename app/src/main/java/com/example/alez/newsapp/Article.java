package com.example.alez.newsapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private String mTitle;
    private String mSection;
    private String mAuthor;
    private String mUrl;
    private Date mPublishDate;

    Article(String title, String category, String url, String dateString, String author) {
        mTitle = title;
        mSection = category;
        mUrl = url;
        mAuthor = author;

        try {
            mPublishDate = formatter.parse(dateString);
        } catch (ParseException e) {
            mPublishDate = new Date();
            e.printStackTrace();
        }
    }

    String getTitle() {
        return mTitle;
    }

    String getSection() {
        return mSection;
    }

    String getUrl() {
        return mUrl;
    }

    Date getPublishDate() {
        return mPublishDate;
    }

    String getAuthor() {
        return mAuthor;
    }
}
