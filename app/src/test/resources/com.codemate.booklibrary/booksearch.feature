Feature: Book Search
  A customer must be able to search books by year, author or title

  Background:
    Given the library has a book with title "How to be awesome", written by "Iiro Krankka", published in 16 May 2016
    And a book with title "My life as an awesome guy", written by "Iiro Krankka", published in 27 July 2016
    And a book with title "I think my teacher is cool", written by "John Doe", published in 01 January 2010

  Scenario: List all books
    When the customer wants to know all books in the library
    Then 3 books should be found
      And Book 1 should have the title "How to be awesome"
      And Book 2 should have the title "My life as an awesome guy"
      And Book 3 should have the title "I think my teacher is cool"

  Scenario: Search books by year
    When the customer searches for books published in year 2016
    Then 2 books should be found
      And Book 1 should have the title "How to be awesome"
      And Book 2 should have the title "My life as an awesome guy"

  Scenario Outline: Search books by author
    When the customer searches for books by author <Author Search>
    Then 2 books should be found
      And Book 1 should have the title "How to be awesome"
      And Book 2 should have the title "My life as an awesome guy"

    Examples:
      | Author Search  |
      | "Iiro"         |
      | "Krankka"      |
      | "Iiro Krankka" |

  Scenario Outline: Search books by title
    When the customer searches for books with title <Title Search>
    Then <Number of Results> books should be found
    And Books should be <Found Books>

    Examples:
      | Title Search   | Number of Results    | Found Books                                         |
      | "Awesome"      | 2                    | "How to be awesome" and "My life as an awesome guy" |
      | "cool"         | 1                    | "I think my teacher is cool"                        |
      | "How to be"    | 1                    | "How to be awesome"                                 |