package com.woowahan.techcourse.utils;

import java.lang.reflect.Field;
import java.util.Scanner;

/**
 * @author 김주원 <kjw11077naver@gmail.com>
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
     * This method returns the rest of the current line, excluding any line
     * separator at the end. The position is set to the beginning of the next
     * line.
     *
     * @return the <tt>String</tt>
     * @throws IllegalArgumentException scanner.nextLine() 에서 발생하는 예외
     */
    public static String nextLine() {
        makeNewScannerIfScannerIsClosed();
        return scanner.nextLine();
    }

    /**
     * 숫자형태의 인풋을 int타입으로 반환한다.
     *
     * @return the <tt>int</tt> 들어온 입력을 int 타입으로 변환한 값
     * @throws IllegalStateException scanner.nextInt()중 발생하는 예외
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
