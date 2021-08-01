package com.woowahan.techcourse.utils;

import java.util.*;

public class Randoms {

    private static final Random RANDOM = new Random();

    private Randoms() {
    }

    public static int pick(final int start, final int end) {
        validateRange(start, end);

        return start + RANDOM.nextInt(end - start + 1);
    }

    private static void validateRange(final int start, final int end) {
        if (start > end) {
            throw new IllegalArgumentException("start가 end보다 클 수 없습니다.");
        }
    }

    public static Set<Integer> picks(final int start, final int end, final int count) {
        validateIntsRange(start, end, count);
        List<Integer> randomInts = new ArrayList<>();

        for (int i = start; i <= end; ++i) {
            randomInts.add(i);
        }

        Collections.shuffle(randomInts);
        return new HashSet<>(randomInts.subList(0, count));
    }

    private static void validateIntsRange(final int start, final int end, final int count) {
        if (start > end) {
            throw new IllegalArgumentException("start가 end보다 클 수 없습니다.");
        }

        if (count < 0) {
            throw new IllegalArgumentException("count는 0보다 작을 수 없습니다.");
        }

        if (end - start + 1 < count) {
            throw new IllegalArgumentException("start에서 end의 범위보다 count가 클 수 없습니다.");
        }
    }

    public static <T> List<T> shuffle(final List<T> list) {
        List<T> result = new ArrayList<>(list);
        Collections.shuffle(result);
        return result;
    }
}