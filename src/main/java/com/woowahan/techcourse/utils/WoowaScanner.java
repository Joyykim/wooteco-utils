package com.woowahan.techcourse.utils;

import java.lang.reflect.Field;
import java.util.Scanner;

public class WoowaScanner {

    private static Scanner scanner = getScanner();

    private WoowaScanner() {
    }

    public static String nextLine() {
        makeNewScannerIfScannerIsClosed();
        return scanner.nextLine();
    }

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
