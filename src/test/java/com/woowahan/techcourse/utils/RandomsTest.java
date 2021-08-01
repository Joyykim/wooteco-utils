package com.woowahan.techcourse.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class RandomsTest {

    @DisplayName("start~end 범위 내에서 임의의 하나의 수를 반환한다.")
    @ParameterizedTest
    @CsvSource({"1,1", "1,2", "1,3", "-1,-1", "-1,0", "-1,1"})
    void pick_success(int start, int end) {
        assertThatCode(() -> Randoms.pick(start, end))
                .doesNotThrowAnyException();
    }

    @DisplayName("start가 end보다 크면 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource({"2,1", "-1,-2", "1,-1"})
    void pick_fail(int start, int end) {
        assertThatThrownBy(() -> Randoms.pick(start, end))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("start~end 범위 내에 있는 수들로 이루어지고, count만큼의 사이즈를 가지고, 수가 서로 중복되지 않는 Set<Integer>를 반환한다.")
    @ParameterizedTest
    @CsvSource({"1,1,1", "1,2,2", "1,3,3", "-1,-1,1", "-1,0,2", "-1,1,3"})
    void picks_success(int start, int end, int count) {
        assertThatCode(() -> {
            Set<Integer> picks = Randoms.picks(start, end, count);

            assertThat(picks).hasSize(count);
        }).doesNotThrowAnyException();
    }

    @DisplayName("start가 end보다 크면 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource({"2,1", "-1,-2", "1,-1"})
    void picks_fail1(int start, int end) {
        int count = 1;
        assertThatThrownBy(() -> Randoms.picks(start, end, count))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("start~end 범위보다 count가 크다면 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource({"1,1,2", "1,2,3", "1,3,4", "-1,-1,2", "-1,0,3", "-1,1,4"})
    void picks_fail2(int start, int end, int count) {
        assertThatThrownBy(() -> Randoms.picks(start, end, count))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("리스트를 인자로 넣으면, 원소가 무작위로 섞인 새로운 리스트를 반환한다.")
    @Test
    void shuffle() {
        List<String> strings = Arrays.asList("a", "b", "c");
        List<String> shuffledStrings = Randoms.shuffle(strings);

        assertThat(strings).containsExactly("a", "b", "c");
        assertThat(shuffledStrings).hasSize(3);
    }
}