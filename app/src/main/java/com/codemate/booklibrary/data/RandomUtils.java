package com.codemate.booklibrary.data;

import java.util.Random;

public class RandomUtils {
    private RandomUtils() {}

    private static final Random random;

    static {
        random = new Random();
    }

    public static <T> T randomFromArray(T[] array) {
        return array[randBetween(0, array.length - 1)];
    }

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(random.nextDouble() * (end - start));
    }
}
