package com.loan.domain.model;

import java.math.BigDecimal;

public class Loan {

    private final BigDecimal amount;
    private final int tenureMonths;
    private final LoanPurpose purpose;

    public Loan(BigDecimal amount, int tenureMonths, LoanPurpose purpose) {
        this.amount = amount;
        this.tenureMonths = tenureMonths;
        this.purpose = purpose;
    }

    public BigDecimal getAmount() { return amount; }
    public int getTenureMonths() { return tenureMonths; }
    public LoanPurpose getPurpose() { return purpose; }
}