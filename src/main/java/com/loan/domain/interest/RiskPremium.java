package com.loan.domain.interest;

import com.loan.domain.model.LoanApplication;
import com.loan.domain.risk.RiskBand;
import com.loan.domain.risk.RiskBandClassifier;

import java.math.BigDecimal;

public class RiskPremium implements InterestComponent {

    private final RiskBandClassifier classifier = new RiskBandClassifier();

    @Override
    public BigDecimal apply(LoanApplication application) {

        RiskBand band = classifier.classify(
                application.getApplicant().getCreditScore()
        );

        return switch (band) {
            case LOW -> BigDecimal.ZERO;
            case MEDIUM -> BigDecimal.valueOf(1.5);
            case HIGH -> BigDecimal.valueOf(3);
        };
    }
}