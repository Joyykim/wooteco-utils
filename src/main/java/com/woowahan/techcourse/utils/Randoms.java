package com.woowahan.techcourse.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Jung YoonSung <ybook2012@gmail.com>, Kim HyeonSik <hsik0225@gmail.com>, Kim JuWon <kjw11077naver@gmail.com>
 * @version 1.0
 * @since 1.0
 */
public class Randoms {

    /**
     * 랜덤값을 만들어주는 java api
     */
    private static final Random RANDOM = new Random();

    private Randoms() {
    }

    /**
     * 범위내의 랜덤한 숫자를 한개를 반환하는 기능을 수행한다. 임의의 int 를 반환한다.
     * <p>
     * 시작값에서 끝값 사이의 숫자 한개를 랜덤하게 생성하여 반환한다. 예를 들어 Randoms.pick(1, 5) 메소드를 호출할 경우, 1 이상 5 이하의 랜덤한 숫자가 반환된다.
     * </p>
     *
     * @param startInclusive 범위의 시작값, 리턴 범위내에 포함된다.
     * @param endInclusive   범위의 끝값, 리턴 범위내에 포함된다.
     * @return 지정한 범위 안의 랜덤 숫자
     * @throws IllegalArgumentException 스택오버플로우가 터질 수 있는 경우, 발생한다. 잘못된 범위가 입력되는 경우, 발생한다.
     */
    public static int pickNumberInRange(final int startInclusive, final int endInclusive) {
        validateRange(startInclusive, endInclusive);
        return startInclusive + RANDOM.nextInt(endInclusive - startInclusive + 1);
    }

    /**
     * {@code List<Integer>}내의 랜덤한 숫자를 한개를 반환하는 기능을 수행한다. 임의의 int 를 반환한다.
     * <p>
     * 숫자 한개를 랜덤하게 반환한다. 예를 들어 Randoms.pick(Arrays.asList(1,2,3)) 메소드를 호출할 경우, 1,2,3 중 랜덤한 숫자가 한개 반환된다.
     * </p>
     *
     * @param numbers 범위의 시작값, 리턴 범위내에 포함된다.
     * @return List 안의 숫자중 랜덤하게 한개의 숫자
     * @throws IllegalArgumentException 비어있는 List를 인풋으로 받을시 발생한다.
     */
    public static int pickNumberInList(final List<Integer> numbers) {
        validateNumbers(numbers);
        return numbers.get(pickNumberInRange(0, numbers.size() - 1));
    }

    private static void validateNumbers(final List<Integer> numbers) {
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("비어있는 numbers 로부터는 숫자를 뽑아낼 수 없습니다.");
        }
    }

    private static void validateRange(final int startInclusive, final int endInclusive) {

        if (endInclusive == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("끝값이 너무 큽니다. (스택 오버플로우 발생이 가능합니다)");
        }

        if (endInclusive - startInclusive >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException("입력값이 너무 큽니다.");
        }

        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException("startInclusive가 endInclusive보다 클 수 없습니다.");
        }
    }

    /**
     * 범위내의 중복되지 않는 숫자 목록들을 반환한다. 임의의 {@code List<Integer>}를 반환한다.
     * <p>
     * 반환되는 결과는 startInclusive ~ endInclusive 범위 내의 수로 이루어져 있고, count만큼의 길이를 가진다. 중복은 존재하지 않고, 순서는 무작위이다. 예를 들어 Randoms.notDuplicatedPicks(1, 5, 3) 메소드를 호출할 경우,
     * 1 이상 5 이하의 숫자들 중 랜덤하게 3개를 가진 List 가 반환된다.
     * </p>
     *
     * @param startInclusive 범위의 시작값, 반환되는 숫자 리스트내에 포함된다.
     * @param endInclusive   범위의 끝값, 반환되는 숫자 리스트내에 포함된다.
     * @param count          가져오려는 숫자의 개수.
     * @return 서로 중복되지 않은, 랜덤한 숫자들의 집합
     * @throws IllegalArgumentException <ul>
     *                                  <li>스택오버플로우가 터질 수 있는 경우, 발생한다.</li>
     *                                  <li>잘못된 범위가 입력되는 경우, 발생한다.</li>
     *                                  <li>중복된 숫자가 나올수 밖에 없는 경우, 발생한다.</li>
     *                                  <li>가져오려는 숫자의 개수가 올바르지 않은경우, 발생한다.</li>
     *                                  </ul>
     */
    public static List<Integer> pickUniqueNumbersInRange(final int startInclusive, final int endInclusive, final int count) {
        validateRange(startInclusive, endInclusive);
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
            throw new IllegalArgumentException("start에서 end의 범위보다 count가 클 수 없습니다.");
        }
    }

    /**
     * {@code List<T>}를 반환한다. 주어진 <b>List</b>를 {@link Collections#shuffle(List list)} 이용하여 셔플한 <b>List</b> 를 반환한다.
     * <p>
     * Collections.shuffle 기능을 수행하고, 수행한 결과를 반환한다.
     * </p>
     *
     * @param list shuffle이 수행될 list
     * @return 셔플이 완료된 새로운 List
     */
    public static <T> List<T> shuffle(final List<T> list) {
        List<T> result = new ArrayList<>(list);
        Collections.shuffle(result);
        return result;
    }
}