package com.loan.domain.rules;

import com.loan.domain.calculator.EmiCalculator;
import com.loan.domain.model.LoanApplication;
import com.loan.domain.valueobject.InterestRate;
import com.loan.domain.valueobject.Money;

import java.math.BigDecimal;
import java.util.Optional;

public class EmiToIncomeRule implements EligibilityRule {

    private final EmiCalculator emiCalculator = new EmiCalculator();

    private static final BigDecimal MAX_RATIO = BigDecimal.valueOf(0.6);

    @Override
    public Optional<String> evaluate(LoanApplication application) {

        BigDecimal income = application.getApplicant().getMonthlyIncome();

        Money principal = new Money(application.getLoan().getAmount());
        InterestRate rate = new InterestRate(BigDecimal.valueOf(12)); // base rate for now

        BigDecimal emi = emiCalculator.calculate(
                principal,
                rate,
                application.getLoan().getTenureMonths()
        );

        BigDecimal ratio = emi.divide(income, 4, BigDecimal.ROUND_HALF_UP);

        if (ratio.compareTo(MAX_RATIO) > 0) {
            return Optional.of("EMI_EXCEEDS_60_PERCENT");
        }

        return Optional.empty();
    }
}