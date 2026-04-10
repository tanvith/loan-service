package com.loan.domain.rules;

import com.loan.domain.model.LoanApplication;
import com.loan.domain.model.LoanPurpose;

import java.util.Optional;

public class CreditScoreRule implements EligibilityRule {


    @Override
    public Optional<String> evaluate(LoanApplication application) {

        boolean homeCriteria = application.getLoan().getPurpose().equals(LoanPurpose.HOME);
        boolean ageCriteria = application.getApplicant().getAge() < 25;

        if(homeCriteria && ageCriteria){
            Optional.empty();
        }
        if (application.getApplicant().getCreditScore() < 600) {
            return Optional.of("CREDIT_SCORE_TOO_LOW");
        }

        return Optional.empty();
    }
}