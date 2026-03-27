package com.loan.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InterestRate {

    private static final BigDecimal MONTHS_IN_YEAR = BigDecimal.valueOf(12);
    private static final BigDecimal PERCENT_DIVISOR = BigDecimal.valueOf(100);

    private final BigDecimal annualRate;

    public InterestRate(BigDecimal annualRate) {
        if (annualRate == null || annualRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Interest rate must be non-negative");
        }
        this.annualRate = annualRate;
    }

    public BigDecimal getAnnualRate() {
        return annualRate;
    }

    public BigDecimal monthlyRate() {
        return annualRate
                .divide(MONTHS_IN_YEAR, 10, RoundingMode.HALF_UP)
                .divide(PERCENT_DIVISOR, 10, RoundingMode.HALF_UP);
    }
}