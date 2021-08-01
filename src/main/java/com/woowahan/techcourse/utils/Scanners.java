package com.woowahan.techcourse.utils;

import java.lang.reflect.Field;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Jung YoonSung <ybook2012@gmail.com>, Kim HyeonSik <hsik0225@gmail.com>, Kim JuWon <kjw11077naver@gmail.com>
 * @version 1.0
 * @since 1.0
 */

public class Scanners {

    /**
     * 입력을 받아주는 java api
     */
    private static Scanner scanner = getScanner();

    private Scanners() {
    }

    /**
     * {@link Scanner#nextLine()} 결과를 반환하는 메소드.
     * <p>
     * <b>\n</b> 전까지의 사용자가 입력한 문자열을 반환한다.
     *
     * @return {@link Scanner#nextLine()}
     * @throws NoSuchElementException scanner.nexLine() 에서 발생하는 예외
     * @throws IllegalStateException  scanner.nexLine() 에서 발생하는 예외
     */
    public static String nextLine() {
        makeNewScannerIfScannerIsClosed();
        return scanner.nextLine();
    }

    /**
     * {@link Scanner#nextInt()} 결과를 반환하는 메소드.
     * <p>
     * 사용자가 입력한 숫자를 반환한다.
     *
     * @return {@link Scanner#nextInt()}
     * @throws InputMismatchException scanner.nextInt()중 발생하는 예외
     * @throws NoSuchElementException scanner.nextInt()중 발생하는 예외
     * @throws IllegalStateException  scanner.nextInt()중 발생하는 예외
     */
    public static int nextInt() {
        makeNewScannerIfScannerIsClosed();
        return scanner.nextInt();
    }

    private static void makeNewScannerIfScannerIsClosed() {
        if (scanner == null || scannerIsClosed()) {
            scanner = getScanner();
        }
    }

    private static boolean scannerIsClosed() {
        try {
            Field sourceClosedField = Scanner.class.getDeclaredField("sourceClosed");
            sourceClosedField.setAccessible(true);
            return sourceClosedField.getBoolean(scanner);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("리플렉션 중 에러 발생");
        }
        return true;
    }

    private static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
