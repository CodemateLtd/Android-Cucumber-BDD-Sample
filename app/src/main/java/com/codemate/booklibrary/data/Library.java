package com.codemate.booklibrary.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ironman on 01/09/16.
 */
public class Library {
    private List<Book> inventory = new ArrayList<>();

    public void addBook(Book book) {
        inventory.add(book);
    }

    public void addBooks(List<Book> books) {
        inventory.addAll(books);
    }

    public List<Book> getAllBooks() {
        return inventory;
    }

    /**
     * This is a stupid search. In a real scenario, we would have something that
     * would be much better.
     */
    public List<Book> search(String searchQuery) {
        List<Book> results = new ArrayList<>();

        if (searchQuery.matches("^[1-9][0-9]{0,3}$")) {
            results.addAll(findBooksByYear(searchQuery));
        }

        results.addAll(findBooksByAuthor(searchQuery));
        results.addAll(findBooksByTitle(searchQuery));

        return results;
    }

    private List<Book> findBooksByAuthor(String authorQuery) {
        List<Book> results = new ArrayList<>();

        for (Book candidate : inventory) {
            String normalizedAuthor = candidate.getAuthor().toLowerCase();
            String normalizedSearchQuery = authorQuery.toLowerCase();

            if (normalizedAuthor.contains(normalizedSearchQuery)) {
                results.add(candidate);
            }
        }

        return results;
    }

    private List<Book> findBooksByTitle(String titleQuery) {
        List<Book> results = new ArrayList<>();

        for (Book candidate : inventory) {
            String normalizedTitle = candidate.getTitle().toLowerCase();
            String normalizedSearchQuery = titleQuery.toLowerCase();

            if (normalizedTitle.contains(normalizedSearchQuery)) {
                results.add(candidate);
            }
        }

        return results;
    }

    private List<Book> findBooksByYear(String searchedYear) {
        List<Book> results = new ArrayList<>();

        for (Book candidate : inventory) {
            Calendar candidateCalendar = Calendar.getInstance();
            candidateCalendar.setTime(candidate.getPublishDate());

            int candidateYear = candidateCalendar.get(Calendar.YEAR);

            if (candidateYear == Integer.valueOf(searchedYear)
                    || yearsStartSimilarly(candidateYear, searchedYear)) {
                results.add(candidate);
            }
        }

        return results;
    }

    private boolean yearsStartSimilarly(int candidateYear, String searchedYear) {
        String candidate = String.valueOf(candidateYear);

        return candidate.startsWith(searchedYear);
    }
}
