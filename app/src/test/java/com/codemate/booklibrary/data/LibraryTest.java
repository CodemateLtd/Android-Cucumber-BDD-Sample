package com.codemate.booklibrary.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by ironman on 02/09/16.
 */
public class LibraryTest {
    @Test
    public void addMultipleBooks_PersistsThemInLibrary() {
        List<Book> books = RandomBookGenerator.randomBooks(3);
        Library library = new Library();
        library.addBooks(books);

        Assert.assertEquals(books, library.getAllBooks());
    }
}
