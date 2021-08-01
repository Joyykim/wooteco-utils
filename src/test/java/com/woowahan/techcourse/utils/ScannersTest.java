package com.woowahan.techcourse.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Duration;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScannersTest {

    private static final Duration SIMPLE_TEST_TIMEOUT = Duration.ofSeconds(1L);
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

    @DisplayName("SCANNER 기능 테스트")
    @Nested
    class ScannerInputTest {

        @DisplayName("두개의 입력을 받으면, 두개의 입력을 출력한다.")
        @ParameterizedTest
        @CsvSource({"fortune,29", "seed,30", "joy,31"})
        void doubleInputTest(final String name, final String age) {
            assertSimpleTest(() -> subject(name, age));
            assertThat(getOutput()).contains(name, age);
        }
    }

    private String getOutput() {
        return captor.toString().trim();
    }
}