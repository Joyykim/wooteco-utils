package com.woowahan.techcourse.utils;

import java.util.*;

public class RandomUtils {

    private static final Random RANDOM = new Random();

    private RandomUtils() {
    }

    public static int nextInt(final int startInclusive, final int endInclusive) {
        validateRange(startInclusive, endInclusive);

        if (startInclusive == endInclusive) {
            return startInclusive;
        }

        return startInclusive + RANDOM.nextInt(endInclusive - startInclusive + 1);
    }

    public static Set<Integer> intsSet(final int startInclusive, final int endInclusive, final int count) {
        validateRangeSet(startInclusive, endInclusive, count);
        List<Integer> randomInts = new ArrayList<>();

        for (int i = startInclusive; i <= endInclusive; ++i) {
            randomInts.add(i);
        }

        Collections.shuffle(randomInts);
        return new TreeSet<>(randomInts.subList(0, count));
    }

    public static <T> List<T> shuffle(final List<T> list) {
        List<T> result = new ArrayList<>(list);
        Collections.shuffle(result);
        return result;
    }

    private static void validateRangeSet(final int startInclusive, final int endInclusive, final int count) {
        if (count < 0) {
            throw new IllegalArgumentException();
        }

        if (endInclusive - startInclusive < count - 1) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateRange(final int startInclusive, final int endInclusive) {
        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException();
        }

        if (startInclusive < 0) {
            throw new IllegalArgumentException();
        }
    }
}