package com.codemate.booklibrary.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LibraryTest {
    @Test
    public void addMultipleBooks_PersistsThemInLibrary() {
        List<Book> books = new RandomBookGenerator().generate(3);
        Library library = new Library();
        library.addBooks(books);

        Assert.assertEquals(books, library.getAllBooks());
    }
}
