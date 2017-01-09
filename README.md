# Behavior-Driven Development with Cucumber

![Sample Video](https://raw.githubusercontent.com/CodemateLtd/Android-Cucumber-BDD-Sample/master/art/sample_video.gif)

This is a sample Android app that has BDD-style tests made using the Cucumber framework. The app acts like a library book search, allowing you to search for books by title, author or year.

[Cucumber](https://cucumber.io/) is a BDD testing framework that allows people without programming background write specifications **that can be translated to unit test requirements almost automatically**.

## An example

The Springfield City Library needs a simple mobile app for searching their book collection while away from their computer. For the first version of the search feature, there's only one thing to implement:

* the employees must be able to search for books by authors.

This translates to the following **Gherkin** syntax, which could be even written by the customer or project lead.

**booksearch.feature:**

```gherkin
Feature: Book Search
    Scenario: Search books by author
      Given there's a book called "Tips for job interviews" written by "John Smith"
        And there's a book called "Bananas and their many colors" written by "James Doe"
        And there's a book called "Mama look I'm a rock star" written by "John Smith"
      When an employee searches by author "John Smith"
      Then 2 books should be found
        And Book 1 has the title "Tips for job interviews"
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

# License

```
This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to <http://unlicense.org>
```
