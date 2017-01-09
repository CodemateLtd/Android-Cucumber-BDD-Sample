package com.codemate.booklibrary.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RandomBookGenerator {
    private static final String[] SUBJECTS = {
            "Chicken", "Pig", "Hippo", "Dinosaur", "Giraffe", "Orangutan",
            "Bat", "Lion", "Daddy", "Grandpa", "Donald Trump", "Coffee",
            "Dog", "Rat", "Pokemon", "Cat", "Senile", "Pensioner",
            "Project manager", "Salesperson"
    };

    private static final String[] SECOND_WORDS = {
            "strip club", "that knew too little", "Strategy", "parking space",
            "Office", "Banana", "Toilet", "Poop", "Fart", "Rain", "Job",
            "Thing", "Radio", "Zoo", "Office", "House", "Village",
            "swimming pool", "Computer"
    };

    private static final String[] FIRST_NAMES = {
            "Bob", "Anna", "Tim", "Bethany", "Donald", "Mickey", "John",
            "George", "Dick", "James", "Timothy", "Joanne", "Angus"
    };

    private static final String[] LAST_NAMES = {
            "Doe", "Trump", "Bond", "Jackson", "Jordan", "Young", "Page",
            "Johnson", "Springsteen", "Kauffman", "Schmidt", "Jokinen"
    };

    /**
     * Generates multiple random books.
     * @param howMany how many random books should the generator generate.
     * @return a list that has as many books as the parameter howMany specifies.
     */
    public List<Book> generate(int howMany) {
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < howMany; i++) {
            books.add(randomBook());
        }

        return books;
    }

    /**
     * Generates a random book.
     */
    public Book randomBook() {
        String title = randomTitle();
        String author = randomAuthor();
        Date date = randomDate();

        return new Book(title, author, date);
    }

    private static String randomTitle() {
        String subject = RandomUtils.randomFromArray(SUBJECTS);
        String secondWord = RandomUtils.randomFromArray(SECOND_WORDS);

        return String.format("%s %s", subject, secondWord);
    }

    private static String randomAuthor() {
        String firstName = RandomUtils.randomFromArray(FIRST_NAMES);
        String lastName = RandomUtils.randomFromArray(LAST_NAMES);

        return String.format("%s %s", firstName, lastName);
    }

    private static Date randomDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, RandomUtils.randBetween(1995, 2016));
        calendar.set(Calendar.MONTH, RandomUtils.randBetween(0, 11));
        calendar.set(Calendar.DAY_OF_MONTH, RandomUtils.randBetween(1, 25));

        return calendar.getTime();
    }
}
