package com.loan.domain.calculator;

import com.loan.domain.valueobject.InterestRate;
import com.loan.domain.valueobject.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EmiCalculator {

    public BigDecimal calculate(Money principal,
                                InterestRate interestRate,
                                int tenureMonths) {

        BigDecimal P = principal.getValue();
        BigDecimal r = interestRate.monthlyRate();
        int n = tenureMonths;

        BigDecimal onePlusR = BigDecimal.ONE.add(r);
        BigDecimal onePlusRPowerN = onePlusR.pow(n);

        BigDecimal numerator = P.multiply(r).multiply(onePlusRPowerN);
        BigDecimal denominator = onePlusRPowerN.subtract(BigDecimal.ONE);

        return numerator.divide(denominator, 2, RoundingMode.HALF_UP);
    }
}