package com.n11.graduationproject.util;

import java.util.concurrent.ThreadLocalRandom;

public final class NumberUtil {

    public static long generateRandomNumberBetween(long min, long max) {

        return ThreadLocalRandom.current().nextLong(min, max + 1);
    }
}