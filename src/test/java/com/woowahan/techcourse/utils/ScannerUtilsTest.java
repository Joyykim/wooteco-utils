package com.woowahan.techcourse.utils;

import org.assertj.core.util.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class ScannerUtilsTest {

    private static final Duration SIMPLE_TEST_TIMEOUT = Duration.ofSeconds(1L);

    private void subject(final String... args) {
        command(args);
        TestApp.main(new String[]{});
    }

    private void command(final String... args) {
        final byte[] buf = Strings.join(args).with("\n").getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }

    private void assertSimpleTest(final Executable executable) {
        assertTimeoutPreemptively(SIMPLE_TEST_TIMEOUT, executable);
    }

    @DisplayName("기능 테스트")
    @Nested
    class FunctionTest {

        private PrintStream standardOut;
        private OutputStream captor;

        @BeforeEach
        void setUp() {
            standardOut = System.out;
            captor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(captor));
        }

        @AfterEach
        void tearDown() {
            System.setOut(standardOut);
            System.out.println(captor.toString());
        }

        private List<String> getOutputLines() {
            return Arrays.asList(captor.toString().trim().split("\n"));
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "2", "3", "4"})
        void nextLineTest(String input) {
            assertSimpleTest(() -> subject(input, input, input));
            assertThat(getOutputLines()).containsExactly(input, input, input);
        }
    }
}
