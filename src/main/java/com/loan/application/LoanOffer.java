package com.loan.application;

import java.math.BigDecimal;

public class LoanOffer {

    private final BigDecimal interestRate;
    private final int tenureMonths;
    private final BigDecimal emi;
    private final BigDecimal totalPayable;

    public LoanOffer(BigDecimal interestRate,
                     int tenureMonths,
                     BigDecimal emi,
                     BigDecimal totalPayable) {
        this.interestRate = interestRate;
        this.tenureMonths = tenureMonths;
        this.emi = emi;
        this.totalPayable = totalPayable;
    }

    public BigDecimal getInterestRate() { return interestRate; }
    public int getTenureMonths() { return tenureMonths; }
    public BigDecimal getEmi() { return emi; }
    public BigDecimal getTotalPayable() { return totalPayable; }
}