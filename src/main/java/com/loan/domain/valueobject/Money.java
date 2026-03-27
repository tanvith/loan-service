package com.loan.domain.valueobject;

import java.math.BigDecimal;

public class Money {

    private final BigDecimal value;

    public Money(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money must be non-negative");
        }
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
