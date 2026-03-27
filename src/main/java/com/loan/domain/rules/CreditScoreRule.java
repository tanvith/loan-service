package com.loan.domain.rules;

import com.loan.domain.model.LoanApplication;

import java.util.Optional;

public class CreditScoreRule implements EligibilityRule {

    @Override
    public Optional<String> evaluate(LoanApplication application) {

        if (application.getApplicant().getCreditScore() < 600) {
            return Optional.of("CREDIT_SCORE_TOO_LOW");
        }

        return Optional.empty();
    }
}