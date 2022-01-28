package com.n11.graduationproject.enum_;

public enum CreditLimitFactor {

    FACTOR(4);

    private final int value;

    CreditLimitFactor(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
