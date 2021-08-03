package com.woowahan.techcourse.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendingMachineUtils {

    private static final List<Integer> COIN_VALUES = Arrays.asList(500, 100, 50, 10);

    public static List<Integer> changeToCoins(int amount) {
        List<Integer> coins = new ArrayList<>();

        for (int coin : COIN_VALUES) {
            int div = amount / coin;
            int coinCount = Randoms.pick(0, div);

            if (coin == 10) {
                for (int i = 0; i < amount / 10; ++i) {
                    coins.add(10);
                }
                return coins;
            }

            for (int i = 0; i < coinCount; ++i) {
                coins.add(coin);
            }
            amount -= coin * coinCount;
        }
        return Randoms.shuffle(coins);
    }
}
