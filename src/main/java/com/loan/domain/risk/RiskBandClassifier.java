package com.loan.domain.risk;

import com.loan.domain.model.LoanApplication;
import com.loan.domain.model.LoanPurpose;
import com.loan.domain.rules.CreditScoreRule;
import com.loan.domain.rules.EligibilityEngine;
import com.loan.domain.rules.EligibilityRule;

import java.util.List;

public class RiskBandClassifier {

    private final List<LoanRiskStrategy> strategies;

    public RiskBandClassifier(List<LoanRiskStrategy> strategies) {
        this.strategies = strategies;
    }

    public RiskBand classify(LoanApplication application) {

        LoanPurpose purpose = application.getLoan().getPurpose();


        return strategies.stream()
                .filter(s -> s.supports(purpose))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No strategy found for " + purpose))
                .classify(application.getApplicant().getCreditScore(), application);
    }
}