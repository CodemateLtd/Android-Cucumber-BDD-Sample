package com.codemate.booklibrary.steps;

import com.codemate.booklibrary.data.Book;
import com.codemate.booklibrary.data.Library;
import com.codemate.booklibrary.ui.MainPresenter;
import com.codemate.booklibrary.ui.MainView;

import org.junit.Assert;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cucumber.api.Format;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by ironman on 01/09/16.
 */
public class BookSearchSteps {
    private Library library = new Library();
    private List<Book> results = new ArrayList<>();

    /**
     * Test data populations
     */
    @Given(".*a book with title \"([^\"]*)\", written by \"([^\"]*)\", published in (.+)$")
    public void addNewBook(String title, String author, @Format("dd MMMM yyyy") Date publishDate) throws Throwable {
        Book book = new Book(title, author, publishDate);
        library.addBook(book);
    }

    /**
     * Use cases
     */
    @When("^the customer wants to know all books in the library$")
    public void getAllBooks() throws Throwable {
        results = library.getAllBooks();
    }

    @When("^the customer searches for books by author \"([^\"]*)\"$")
    public void searchByAuthor(String authorQuery) throws Throwable {
        results = library.search(authorQuery);
    }

    @When("^the customer searches for books with title \"([^\"]*)\"$")
    public void searchByTitle(String title) throws Throwable {
        results = library.search(title);
    }

    @When("^the customer searches for books published in year (.*)$")
    public void searchByYear(String year) throws Throwable {
        results = library.search(year);
    }

    /**
     * Assertions
     */
    @Then("^(\\d+) books should be found$")
    public void verifyAmountOfBooksFound(int booksFound) throws Throwable {
        assertEquals(booksFound, results.size());
    }

    @And("^Book (\\d+) should have the title \"([^\"]*)\"$")
    public void verifyBookAtPosition(int position, String title) throws Throwable {
        int realPosition = position - 1;
        Assert.assertEquals(title, results.get(realPosition).getTitle());
    }

    @And("^Books should be (.+)$")
    public void verifyBookTitlesEqualTo(String titles) throws Throwable {
        for (Book result : results) {
            assertThat(titles, containsString(result.getTitle()));
        }
    }
}