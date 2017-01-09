package com.codemate.booklibrary.data;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class RandomBookGeneratorTest {
    @Test
    public void generatingRandomBooks_ReturnsNonEmptyBookList() {
        List<Book> books = new RandomBookGenerator().generate(15);

        assertEquals(15, books.size());

        for (Book book : books) {
            assertNotNull(book.getTitle());
            assertNotNull(book.getAuthor());
            assertNotEquals(0, book.getPublishDate().getTime());
        }
    }
}
