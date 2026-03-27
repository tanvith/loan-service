package com.loan.application;

import com.loan.domain.calculator.EmiCalculator;
import com.loan.domain.interest.InterestRateCalculator;
import com.loan.domain.model.LoanApplication;
import com.loan.domain.rules.EligibilityEngine;
import com.loan.domain.risk.RiskBand;
import com.loan.domain.risk.RiskBandClassifier;
import com.loan.domain.rules.EmiThresholdOfferRule;
import com.loan.domain.rules.OfferRule;
import com.loan.domain.valueobject.InterestRate;
import com.loan.domain.valueobject.Money;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class LoanEvaluatorService {

    private final EligibilityEngine eligibilityEngine = new EligibilityEngine();
    private final InterestRateCalculator interestCalculator = new InterestRateCalculator();
    private final EmiCalculator emiCalculator = new EmiCalculator();
    private final RiskBandClassifier riskClassifier = new RiskBandClassifier();
    private final com.loan.application.validation.LoanApplicationValidator validator = new com.loan.application.validation.LoanApplicationValidator();
    private final OfferRule offerRule = new EmiThresholdOfferRule();

    public LoanDecision evaluate(LoanApplication application) {

        List<String> validationErrors = validator.validate(application);

        if (!validationErrors.isEmpty()) {
            return new LoanDecision(
                    application.getId(),
                    "REJECTED",
                    null,
                    null,
                    validationErrors
            );
        }

        List<String> failures = eligibilityEngine.evaluate(application);

        if (!failures.isEmpty()) {
            return new LoanDecision(
                    application.getId(),
                    "REJECTED",
                    null,
                    null,
                    failures
            );
        }

        RiskBand riskBand = riskClassifier.classify(
                application.getApplicant().getCreditScore()
        );

        BigDecimal rate = interestCalculator.calculate(application);

        Money principal = new Money(application.getLoan().getAmount());

        BigDecimal emi = emiCalculator.calculate(
                principal,
                new InterestRate(rate),
                application.getLoan().getTenureMonths()
        );

        BigDecimal income = application.getApplicant().getMonthlyIncome();

        Optional<String> offerFailure = offerRule.evaluate(emi, income);

        if (offerFailure.isPresent()) {
            return new LoanDecision(
                    application.getId(),
                    "REJECTED",
                    null,
                    null,
                    List.of(offerFailure.get())
            );
        }

        BigDecimal totalPayable = emi.multiply(
                BigDecimal.valueOf(application.getLoan().getTenureMonths())
        );

        LoanOffer offer = new LoanOffer(
                rate,
                application.getLoan().getTenureMonths(),
                emi,
                totalPayable
        );

        return new LoanDecision(
                application.getId(),
                "APPROVED",
                riskBand.name(),
                offer,
                null
        );
    }
}