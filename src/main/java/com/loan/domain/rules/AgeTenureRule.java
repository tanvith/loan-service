package com.loan.domain.rules;

import com.loan.domain.model.LoanApplication;

import java.util.Optional;

public class AgeTenureRule implements EligibilityRule {

    @Override
    public Optional<String> evaluate(LoanApplication application) {

        int age = application.getApplicant().getAge();
        int tenureYears = application.getLoan().getTenureMonths() / 12;

        if (age + tenureYears > 65) {
            return Optional.of("AGE_TENURE_LIMIT_EXCEEDED");
        }

        return Optional.empty();
    }
}