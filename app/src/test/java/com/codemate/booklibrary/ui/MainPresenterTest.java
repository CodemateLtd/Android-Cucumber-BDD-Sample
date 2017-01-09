package com.codemate.booklibrary.ui;

import com.codemate.booklibrary.data.Book;
import com.codemate.booklibrary.data.Library;
import com.codemate.booklibrary.data.RandomBookGenerator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ironman on 02/09/16.
 */
public class MainPresenterTest {
    private static final List<Book> DUMMY_BOOKS = Arrays.asList(
            new Book("Sample book one", "John Doe", Date.valueOf("2000-10-25")),
            new Book("Sample book two", "Jane Doe", Date.valueOf("2016-01-01")),
            new Book("Sample book three", "Some One", Date.valueOf("2005-12-12"))
    );

    @Mock
    private MainView mainView;

    @Mock
    private RandomBookGenerator bookGenerator;

    private MainPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(bookGenerator.generate(anyInt())).thenReturn(DUMMY_BOOKS);
        presenter = new MainPresenter(mainView, new Library(), bookGenerator);
    }

    @Test
    public void searchForBooks_WhenSearchingByAuthorJohn_ShowsFirstBookInUI() {
        presenter.fetchBooks();
        presenter.searchForBooks("John");

        verify(mainView).showBooks(singletonList(DUMMY_BOOKS.get(0)));
    }

    @Test
    public void fetchBooks_LoadsAll_AndShowsThemInUI() {
        presenter.fetchBooks();

        verify(mainView).showBooks(DUMMY_BOOKS);
    }
}
