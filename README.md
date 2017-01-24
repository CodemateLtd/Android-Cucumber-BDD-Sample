# Behavior-Driven Development with Cucumber

[![Build Status](https://travis-ci.org/CodemateLtd/Android-Cucumber-BDD-Sample.svg?branch=master)](https://travis-ci.org/CodemateLtd/Android-Cucumber-BDD-Sample) [![Coverage Status](https://coveralls.io/repos/github/CodemateLtd/Android-Cucumber-BDD-Sample/badge.svg?branch=master)](https://coveralls.io/github/CodemateLtd/Android-Cucumber-BDD-Sample?branch=master)

![Sample Video](https://raw.githubusercontent.com/CodemateLtd/Android-Cucumber-BDD-Sample/master/art/sample_video.gif)

This is a sample Android app that has BDD-style tests made using the Cucumber framework. The app acts like a library book search, allowing you to search for books by title, author or year.

[Cucumber](https://cucumber.io/) is a BDD testing framework that allows people without programming background write specifications **that can be translated to unit test requirements almost automatically**.

The feature file that has all functionality requirements that the application must fulfill is located [here](https://github.com/CodemateLtd/Android-Cucumber-BDD-Sample/blob/master/app/src/test/resources/com.codemate.booklibrary/booksearch.feature).

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
Copyright 2016 Codemate Ltd

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
