package com.ryuqq.support.utils;

import org.jeasy.random.api.Randomizer;

class IntegerRangeRandomizer implements Randomizer<Integer> {
    private final int min;
    private final int max;

    public IntegerRangeRandomizer(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Integer getRandomValue() {
        return min + (int) (Math.random() * (max - min));
    }
}

