package com.codemate.booklibrary.ui;

import android.support.annotation.VisibleForTesting;

import com.codemate.booklibrary.data.Book;
import com.codemate.booklibrary.data.Library;
import com.codemate.booklibrary.data.RandomBookGenerator;

import java.util.List;

/**
 * Created by ironman on 02/09/16.
 */
public class MainPresenter {
    private final MainView mainView;
    private final Library library;

    @VisibleForTesting
    RandomBookGenerator bookGenerator;

    public MainPresenter(MainView mainView, Library library, RandomBookGenerator bookGenerator) {
        this.mainView = mainView;
        this.library = library;
        this.bookGenerator = bookGenerator;
    }

    public void searchForBooks(String searchQuery) {
        List<Book> searchResults = library.search(searchQuery);
        mainView.showBooks(searchResults);
    }

    public void fetchBooks() {
        // Populate the library with fake dummy data. In a real app
        // we would have an interactor that would fetch the books from
        // a real API.
        List<Book> books = bookGenerator.generate(45);
        library.addBooks(books);

        mainView.showBooks(library.getAllBooks());
    }
}
