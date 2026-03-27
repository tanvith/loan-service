package com.loan.domain.rules;

import java.math.BigDecimal;
import java.util.Optional;

public interface OfferRule {
    Optional<String> evaluate(BigDecimal emi, BigDecimal income);
}
