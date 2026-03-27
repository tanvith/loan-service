package com.loan.domain.calculator;

import com.loan.domain.valueobject.InterestRate;
import com.loan.domain.valueobject.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EmiCalculatorTest {

    private final EmiCalculator calculator = new EmiCalculator();

    @Test
    void shouldCalculateEmiCorrectly() {

        Money principal = new Money(BigDecimal.valueOf(100000));
        InterestRate rate = new InterestRate(BigDecimal.valueOf(12));

        BigDecimal emi = calculator.calculate(principal, rate, 12);

        assertTrue(emi.compareTo(BigDecimal.ZERO) > 0);
    }
}