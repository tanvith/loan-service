package com.loan.domain.rules;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class EmiThresholdOfferRule implements OfferRule {

    private static final BigDecimal MAX_RATIO = BigDecimal.valueOf(0.5);

    @Override
    public Optional<String> evaluate(BigDecimal emi, BigDecimal income) {

        BigDecimal ratio = emi.divide(income, 4, RoundingMode.HALF_UP);

        if (ratio.compareTo(MAX_RATIO) > 0) {
            return Optional.of("EMI_EXCEEDS_50_PERCENT");
        }

        return Optional.empty();
    }
}
