package com.woowahan.techcourse.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class RandomsTest {

    @DisplayName("pick 기능 테스트")
    @Nested
    class PickTest {

        @DisplayName("n,m 범위의 숫자를 뽑으면, n,m 사이의 숫자가 반환된다..")
        @ParameterizedTest
        @CsvSource({"1,1", "1,2", "0,10", "-1,0"})
        void pickTest(final int start, final int end) {

            for (int i = 0; i < 1000; ++i) {
                // when
                int randomInt = Randoms.pick(start, end);

                // then
                assertThat(randomInt).isBetween(start, end);
            }
        }

        @DisplayName("start가 end보다 같으면, 같은 숫자가 반환된다.")
        @ParameterizedTest
        @CsvSource({"1,1", "-1,-1", "2,2"})
        void pickSameTest(final int start, final int end) {
            // when
            int randomInt = Randoms.pick(start, end);

            // then
            assertThat(randomInt).isEqualTo(start);
            assertThat(randomInt).isEqualTo(end);
        }

        @DisplayName("start가 end보다 크면, 예외가 발생한다.")
        @ParameterizedTest
        @CsvSource({"1,0", "1,-1", "9,-2", "-1,-3"})
        void pickExceptionTest(final int start, final int end) {
            assertThatThrownBy(
                () -> Randoms.pick(start, end)
            ).isExactlyInstanceOf(IllegalArgumentException.class).hasMessage("startInclusive가 endInclusive보다 클 수 없습니다.");
        }

        @DisplayName("overflow가 발생할 수 있는 범위의 숫자를 넣으면 예외가 발생한다.")
        @ParameterizedTest
        @CsvSource({"1, 0x7fffffff", "0, 0x7fffffff"})
        void overFlowExceptionTest(final int start, final int end) {
            assertThatThrownBy(
                () -> Randoms.pick(start, end)
            ).isExactlyInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("중복되지 않는 랜덤한 숫자들을 뽑아주는 기능 테스트")
    @Nested
    class notDuplicatedPicksTest {

        @DisplayName("start가 end보다 크면, 예외가 발생한다.")
        @ParameterizedTest
        @CsvSource({"-1,-2,1", "1,-1,1", "1,0,1", "2,1,1", "3,1,1", "3,1,2", "3,1,3"})
        void notDuplicatedExceptionTest3(final int start, final int end) {
            assertThatThrownBy(
                () -> Randoms.pick(start, end)
            ).isExactlyInstanceOf(IllegalArgumentException.class).hasMessage("startInclusive가 endInclusive보다 클 수 없습니다.");
        }

        @DisplayName("start부터 end까지의 중복되지 않는 숫자 count 개가 반환된다.")
        @ParameterizedTest
        @CsvSource({"-2,-1,1", "-1,1,1", "1,1,1", "1,2,1", "1,3,1", "1,3,2", "1,3,3"})
        void doubleInputTest(final int start, final int end, final int count) {
            List<Integer> nonDuplicatedInts = Randoms.notDuplicatedPicks(start, end, count);

            assertThat(nonDuplicatedInts).hasSize(count);
            for (final int num : nonDuplicatedInts) {
                assertThat(num).isBetween(start, end);
                assertThat(nonDuplicatedInts).containsOnlyOnce(num);
            }
        }

        @DisplayName("start부터 end까지의 수를 음수의 개수로 뽑으려고 하면 예외가 발생한다.")
        @ParameterizedTest
        @CsvSource({"1,2,-1", "1,3,-2", "1,3,-3", "1,3,-4"})
        void notDuplicatedExceptionTest(final int start, final int end, final int wrongCount) {
            assertThatThrownBy(() -> {
                Randoms.notDuplicatedPicks(start, end, wrongCount);
            }).isExactlyInstanceOf(IllegalArgumentException.class).hasMessage("count는 0보다 작을 수 없습니다.");
        }

        @DisplayName("start부터 end까지의 수를 중복될수 밖에 없는 개수로 뽑으려고 하면 예외가 발생한다.")
        @ParameterizedTest
        @CsvSource({"1,1,2", "1,2,3", "1,3,4", "-1,3,6", "-1,4,7"})
        void notDuplicatedExceptionTest2(final int start, final int end, final int wrongCount) {
            assertThatThrownBy(() -> {
                Randoms.notDuplicatedPicks(start, end, wrongCount);
            }).isExactlyInstanceOf(IllegalArgumentException.class).hasMessage("start에서 end의 범위보다 count가 클 수 없습니다.");
        }
    }

    @DisplayName("리스트를 인자로 넣으면, 원소가 무작위로 섞인 새로운 리스트를 반환한다.")
    @ParameterizedTest
    @MethodSource
    <T> void shuffleSourceTest(final T o1, final T o2, final T o3) {
        List<T> list = Arrays.asList(o1, o2, o3);
        List<T> shuffledList = Randoms.shuffle(list);
        assertThat(list).containsExactly(o1, o2, o3);
        assertThat(shuffledList).hasSize(3);
    }

    private static Stream<Arguments> shuffleSourceTest() {
        return Stream.of(
            Arguments.of(1, 2, 3),
            Arguments.of("a", "b", "c")
        );
    }
}