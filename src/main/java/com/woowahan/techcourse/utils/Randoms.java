package com.woowahan.techcourse.utils;

import java.util.*;

public class Randoms {

    private static final Random RANDOM = new Random();

    private Randoms() {
    }

    /**
     * start ~ end 범위 내의 수 하나를 랜덤으로 반환합니다.
     *
     * @return start ~ end 범위 중 하나의 수
     * @throws IllegalArgumentException start가 end보다 큰 경우
     */
    public static int pick(final int start, final int end) {
        validateRange(start, end);

        return start + RANDOM.nextInt(end - start + 1);
    }

    private static void validateRange(final int start, final int end) {
        if (start > end) {
            throw new IllegalArgumentException("start가 end보다 클 수 없습니다.");
        }
    }

    /**
     * 임의의 {@code Set<Integer>}를 반환합니다.
     * 반환되는 결과는 start ~ end 범위 내의 수로 이루어져 있고, count만큼의 길이를 가집니다. 중복은 존재하지 않고, 순서는 무작위입니다.
     *
     * @return start ~ end 범위 중 하나의 수
     * @throws IllegalArgumentException start가 end보다 큰 경우, count가 0보다 작은 경우, start에서 end의 범위보다 count가 큰 경우.
     */
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

    /**
     * {@code List<T>}를 반환합니다.
     * 파라미터 list와 길이가 같지만 원소들의 순서를 무작위로 섞은 리스트를 반환합니다.
     *
     * @return list의 순서를 섞은 리스트
     */
    public static <T> List<T> shuffle(final List<T> list) {
        List<T> result = new ArrayList<>(list);
        Collections.shuffle(result);
        return result;
    }
}