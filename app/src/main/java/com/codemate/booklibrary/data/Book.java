package com.codemate.booklibrary.data;

import java.util.Date;

/**
 * Created by ironman on 01/09/16.
 */
public class Book {
    private final String title;
    private final String author;
    private final Date publishDate;

    public Book(String title, String author, Date publishDate) {
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublishDate() {
        return publishDate;
    }
}
