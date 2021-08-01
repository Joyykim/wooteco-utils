package com.woowahan.techcourse.utils;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class RandomsTest {

    @ParameterizedTest(name = "지정한 범위 안의 랜덤 숫자 반환 테스트")
    @CsvSource(value = {"1:5", "-5:-1", "-1:1", "1:1"}, delimiter = ':')
    void pickTest(int start, int end) {
        for (int i = 0; i < 1000; i++) {
            int pick = Randoms.pick(start, end);
            assertThat(pick).isBetween(start, end);
        }
    }

    @DisplayName("startInclusive가 endInclusive보다 클 경우 예외 발생")
    @Test
    void pickFalIfStartIsGreaterThanEnd() {
        assertThatIllegalArgumentException().isThrownBy(() -> Randoms.pick(1, 0))
                                            .isExactlyInstanceOf(IllegalArgumentException.class)
                                            .withMessage("startInclusive가 endInclusive보다 클 수 없습니다.");
    }

    @ParameterizedTest(name = "지정한 범위 내의 숫자들을 주어진 숫자만큼 랜덤하게 뽑는 테스트")
    @CsvSource(value = {"1:5:3", "-5:-1:3", "-1:1:3", "1:1:1"}, delimiter = ':')
    void notDuplicatedPicksTest(int start, int end, int count) {
        for (int i = 0; i < 1000; i++) {
            final List<Integer> notDuplicatedPicks = Randoms.notDuplicatedPicks(start, end, count);
            assertThat(notDuplicatedPicks).hasSize(count);
            for (int pick : notDuplicatedPicks) {
                assertThat(notDuplicatedPicks).containsOnlyOnce(pick);
                assertThat(pick).isBetween(start, end);
            }
        }
    }

    @DisplayName("count가 0보다 작을 경우 예외 발생")
    @Test
    void notDuplicatedPicksFailIfCountIsLessThanZero() {
        assertThatIllegalArgumentException().isThrownBy(() -> Randoms.notDuplicatedPicks(1, 3, -1))
                                            .isExactlyInstanceOf(IllegalArgumentException.class)
                                            .withMessage("count는 0보다 작을 수 없습니다.");
    }

    @DisplayName("count가 범위 내의 원소들의 개수보다 클 경우 예외 발생")
    @Test
    void notDuplicatedPicksFailIfCountIsGreaterThanBoundary() {
        assertThatIllegalArgumentException().isThrownBy(() -> Randoms.notDuplicatedPicks(1, 3, 4))
                                            .isExactlyInstanceOf(IllegalArgumentException.class)
                                            .withMessage("count가 (endInclusive - startInclusive + 1) 보다 클 수 없습니다.");
    }

    @DisplayName("주어진 List Shuffle 테스트")
    @Test
    void shuffleTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        final List<Integer> shuffledList = Randoms.shuffle(list);
        assertThat(list).usingRecursiveComparison().isNotEqualTo(shuffledList);
    }
}
