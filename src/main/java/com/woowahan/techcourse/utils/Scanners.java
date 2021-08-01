package com.woowahan.techcourse.utils;

import java.lang.reflect.Field;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Scanners {

    private static Scanner scanner = getScanner();

    private Scanners() {
    }

    /**
     * 콘솔 입력을 받아 String형으로 반환합니다.
     *
     * @return 콘솔 입력 String
     * @throws NoSuchElementException 입력이 없을 경우
     */
    public static String nextLine() {
        makeNewScannerIfScannerIsClosed();
        return scanner.nextLine();
    }

    /**
     * 콘솔 입력을 받아 int형으로 반환합니다.
     *
     * @return 콘솔 입력을 int로 파싱한 결과
     * @throws InputMismatchException
     *         만약 입력이 <i>Integer</i> 형식에 맞지 않거나, out of range인 경우???
     * @throws NoSuchElementException 입력이 없을 경우
     */
    public static int nextInt() {
        makeNewScannerIfScannerIsClosed();
        return scanner.nextInt();
    }

    // 스캐너가 닫혀있다면 새로운 스캐너 생성
    private static void makeNewScannerIfScannerIsClosed() {
        if (scanner == null || scannerIsClosed()) {
            scanner = getScanner();
        }
    }

    // Scanner의 private 필드 sourceClosed를 리턴
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
