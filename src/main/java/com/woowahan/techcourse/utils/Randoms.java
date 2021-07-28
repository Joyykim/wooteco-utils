package com.woowahan.techcourse.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Randoms {

    private static final Random RANDOM = new Random();

    private Randoms() {
    }

    public static int pick(final int startInclusive, final int endInclusive) {
        validateRange(startInclusive, endInclusive);

        return startInclusive + RANDOM.nextInt(endInclusive - startInclusive + 1);
    }

    private static void validateRange(final int startInclusive, final int endInclusive) {
        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException("startInclusive가 endInclusive보다 클 수 없습니다.");
        }
    }

    public static List<Integer> notDuplicatedPicks(final int startInclusive, final int endInclusive, final int count) {
        validateIntsRange(startInclusive, endInclusive, count);
        List<Integer> randomInts = new ArrayList<>();

        for (int i = startInclusive; i <= endInclusive; ++i) {
            randomInts.add(i);
        }

        Collections.shuffle(randomInts);
        return randomInts.subList(0, count);
    }

    private static void validateIntsRange(final int startInclusive, final int endInclusive, final int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count는 0보다 작을 수 없습니다.");
        }

        if (endInclusive - startInclusive + 1 < count) {
            throw new IllegalArgumentException("count가 (endInclusive - startInclusive + 1) 보다 클 수 없습니다.");
        }
    }

    public static <T> List<T> shuffle(final List<T> list) {
        List<T> result = new ArrayList<>(list);
        Collections.shuffle(result);
        return result;
    }
}