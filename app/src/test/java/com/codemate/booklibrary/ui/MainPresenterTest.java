package com.codemate.booklibrary.ui;

import com.codemate.booklibrary.data.Book;
import com.codemate.booklibrary.data.Library;
import com.codemate.booklibrary.data.RandomBookGenerator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ironman on 02/09/16.
 */
public class MainPresenterTest {

    @Mock
    private MainView mainView;

    @Mock
    private Library library;

    private MainPresenter presenter;
    private List<Book> randomBooks;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(mainView, library);
        randomBooks = RandomBookGenerator.randomBooks(5);
    }

    @Test
    public void searchForBooks_ShowsThemInUI() {
        when(library.search(anyString()))
                .thenReturn(randomBooks);
        presenter.searchForBooks("test_search");

        verify(mainView).showBooks(randomBooks);
    }

    @Test
    public void loadAllBooks_ShowsThemInUI() {
        when(library.getAllBooks())
                .thenReturn(randomBooks);
        presenter.loadAllBooks();

        verify(mainView).showBooks(randomBooks);
    }
}
