# Behavior-Driven Development with Cucumber

This is a sample Android app that has tests mostly written using the Cucumber framework. The app acts like a library book search, allowing you to search for books by title, author or year. **The test coverage is 97%**.

[Cucumber](https://cucumber.io/) is a BDD testing framework that allows people without programming background write specifications **that can be translated to unit tests almost automatically**.

Writing tests turns more fun and it's no more a pain in the ass to write them first. It's also easier to specify the requirements.

## An example

The Oulu City Library has an application for book management.

The application is working fine, but they don't have a search feature. For the first version of the search feature, there's only one thing to implement:

* the employees must be able to search for books by authors.

This translates to the following **Gherkin** syntax, which could be even written by the customer or project lead.

**booksearch.feature:**

```gherkin
Feature: Book Search

    Scenario: Search books by author
      Given there's a book called "Tips for horrible hangovers" written by "John Smith"
        And there's a book called "Bananas and their many colors" written by "James Brown"
        And there's a book called "Mama look I'm a rock star" written by "John Smith"
      When an employee searches by author "John Smith"
      Then 2 books should be found
        And Book 1 has the title "Tips for horrible hangovers"
        And Book 2 has the title "Mama look I'm a rock star"
```

Simple, isn't it? This can easily be read and understood by non-programmers!

From that file, Cucumber pretty much autogenerates the Regular Expressions and Annotations needed to match the specific criterias. Then we only need to add the test logic and if we want, modify the method names.


**BookSearchSteps.java (annotations and methods auto-generated):**

```java
public class BookSearchSteps {
    private Library library = new Library();
    private List<Book> searchResults = new ArrayList<>();

    @Given("^there's a book called \"([^\"]*)\" written by \"([^\"]*)\"$")
    public void addBook(String title, String author) throws Throwable {
        Book book = new Book(title, author);
        library.addBook(book);
    }

    @When("^an employee searches by author \"([^\"]*)\"$")
    public void searchForAuthor(String authorQuery) throws Throwable {
        searchResults = library.findBooksByAuthor(authorQuery);
    }

    @And("^Book (\\d+) has the title \"([^\"]*)\"$")
    public void verifyBookFound(int position, String title) throws Throwable {
        int realPosition = position - 1;
        assertEquals(title, searchResults.get(realPosition).getTitle());
    }
}
```

Now that we have the tests in place, we just implement the functionality to make the tests pass!