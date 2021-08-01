package com.woowahan.techcourse.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Randoms {

    private static final Random RANDOM = new Random();

    private Randoms() {
    }

    /**
     * 지정한 범위 안의 랜덤 숫자를 반환하는 메소드. <p>
     * 예를 들어 Randoms.pick(1, 5) 메소드를 호출할 경우, 1 이상 5 이하의 랜덤한 숫자가 반환된다.
     *
     * @param startInclusive 랜덤 끝 지점, 범위 안에 포함된다.
     * @param endInclusive 랜덤 시작 지점, 범위 안에 포함된다.
     * @return 지정한 범위 안의 랜덤 숫자
     * @throws IllegalArgumentException <b>startInclusive</b>가 <b>endInclusive</b>보다 클 경우 발생하는 예외
     */
    public static int pick(final int startInclusive, final int endInclusive) {
        validateRange(startInclusive, endInclusive);

        return startInclusive + RANDOM.nextInt(endInclusive - startInclusive + 1);
    }

    private static void validateRange(final int startInclusive, final int endInclusive) {
        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException("startInclusive가 endInclusive보다 클 수 없습니다.");
        }
    }

    /**
     * 지정한 범위 내의 숫자들을 주어진 숫자(count)만큼 랜덤하게 뽑은 결과를 반환한다.
     * 뽑은 숫자들은 서로 중복되지 않는다. <p>
     * 예를 들어 Randoms.notDuplicatedPicks(1, 5, 3) 메소드를 호출할 경우, 1 이상 5 이하의 숫자들 중 랜덤하게 3개를 가진 List 가 반환된다.
     *
     * @param startInclusive 랜덤 끝 지점, 범위 안에 포함된다.
     * @param endInclusive 랜덤 시작 지점, 범위 안에 포함된다.
     * @return 랜덤하게 뽑은 숫자 List
     * @throws IllegalArgumentException
     *     <ul>
     *         <li><b>count</b>가 0보다 작을 경우 발생하는 예외</li>
     *         <li><b>count</b>가 범위 내의 원소들의 개수(endInclusive - startInclusive + 1)보다 클 경우 발생하는 예외</li>
     *     </ul>
     */
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

    /**
     * 주어진 <b>List</b>를 {@link Collections#shuffle(List list)} 이용하여 셔플한 <b>List</b> 를 반환한다.
     *
     * @param list 셔플할 <b>List</b>
     * @return 셔플이 된 List
     */
    public static <T> List<T> shuffle(final List<T> list) {
        List<T> result = new ArrayList<>(list);
        Collections.shuffle(result);
        return result;
    }
}
