package com.codemate.booklibrary.ui;

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

    public MainPresenter(MainView mainView, Library library) {
        this.mainView = mainView;
        this.library = library;
    }

    public void searchForBooks(String searchQuery) {
        List<Book> searchResults = library.search(searchQuery);
        loadBooks(searchResults);
    }

    public void loadAllBooks() {
        // Populate the library with fake dummy data. In a real app
        // we would have an interactor that would fetch the books from
        // a real API.
        List<Book> books = RandomBookGenerator.generate(45);
        library.addBooks(books);

        loadBooks(library.getAllBooks());
    }

    public void loadBooks(List<Book> books) {
        mainView.showBooks(books);
    }
}
