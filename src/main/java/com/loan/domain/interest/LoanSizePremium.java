package com.loan.domain.interest;

import com.loan.domain.model.LoanApplication;

import java.math.BigDecimal;

public class LoanSizePremium implements InterestComponent {

    @Override
    public BigDecimal apply(LoanApplication application) {

        if (application.getLoan().getAmount().compareTo(BigDecimal.valueOf(1_000_000)) > 0) {
            return BigDecimal.valueOf(0.5);
        }

        return BigDecimal.ZERO;
    }
}