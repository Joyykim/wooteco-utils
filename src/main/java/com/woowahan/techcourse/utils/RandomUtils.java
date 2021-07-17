package com.woowahan.techcourse.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomUtils {

    private static final Random RANDOM = new Random();

    private RandomUtils() {
    }

    public static int randomInt(final int startInclusive, final int endExclusive) {
        validateRange(startInclusive, endExclusive);

        return startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
    }

    private static void validateRange(final int startInclusive, final int endExclusive) {
        if (startInclusive >= endExclusive) {
            throw new IllegalArgumentException("startInclusive가 endExclusive보다 같거나 클 수 없습니다.");
        }
    }

    public static int randomPositive(final int startInclusive, final int endExclusive) {
        validatePositiveRange(startInclusive, endExclusive);

        return startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
    }

    private static void validatePositiveRange(final int startInclusive, final int endExclusive) {
        validateRange(startInclusive, endExclusive);

        if (startInclusive < 0 || endExclusive < 0) {
            throw new IllegalArgumentException("startInclusive, endExclusive는 음수일 수 없습니다.");
        }
    }

    public static List<Integer> notDuplicatedRandomInts(final int startInclusive, final int endExclusive, final int count) {
        validateIntsRange(startInclusive, endExclusive, count);
        List<Integer> randomInts = new ArrayList<>();

        for (int i = startInclusive; i < endExclusive; ++i) {
            randomInts.add(i);
        }

        Collections.shuffle(randomInts);
        return randomInts.subList(0, count);
    }

    private static void validateIntsRange(final int startInclusive, final int endExclusive, final int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count는 0보다 작을 수 없습니다.");
        }

        if (endExclusive - startInclusive < count) {
            throw new IllegalArgumentException("count가 (endExclusive - startInclusive) 보다 같거나 작아야합니다.");
        }
    }

    public static <T> List<T> shuffle(final List<T> list) {
        List<T> result = new ArrayList<>(list);
        Collections.shuffle(result);
        return result;
    }
}