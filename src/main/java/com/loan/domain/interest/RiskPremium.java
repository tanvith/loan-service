package com.loan.domain.interest;

import com.loan.domain.model.LoanApplication;
import com.loan.domain.risk.*;

import java.math.BigDecimal;
import java.util.List;

public class RiskPremium implements InterestComponent {


    @Override
    public BigDecimal apply(LoanApplication application) {

        List<LoanRiskStrategy> strategies = List.of(
                new HomeLoanRiskStrategy(),
                new CarLoanRiskStrategy(),
                new PersonalLoanRiskStrategy()
        );

        RiskBandClassifier classifier = new RiskBandClassifier(strategies);
        RiskBand riskBand = classifier.classify(application);

        return switch (riskBand) {
            case LOW -> BigDecimal.ZERO;
            case MEDIUM -> BigDecimal.valueOf(1.5);
            case HIGH -> BigDecimal.valueOf(3);
        };
    }
}