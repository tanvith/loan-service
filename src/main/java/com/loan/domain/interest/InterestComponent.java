package com.loan.domain.interest;

import com.loan.domain.model.LoanApplication;

import java.math.BigDecimal;

public interface InterestComponent {
    BigDecimal apply(LoanApplication application);
}