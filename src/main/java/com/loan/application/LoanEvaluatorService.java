package com.loan.application;

import com.loan.application.validation.LoanApplicationValidator;
import com.loan.domain.calculator.EmiCalculator;
import com.loan.domain.interest.InterestRateCalculator;
import com.loan.domain.model.LoanApplication;
import com.loan.domain.rules.EmiThresholdOfferRule;
import com.loan.domain.rules.OfferRule;
import com.loan.domain.rules.EligibilityEngine;
import com.loan.domain.risk.RiskBand;
import com.loan.domain.risk.RiskBandClassifier;
import com.loan.domain.valueobject.InterestRate;
import com.loan.domain.valueobject.Money;
import com.loan.infrastructure.persistence.LoanDecisionEntity;
import com.loan.infrastructure.persistence.LoanDecisionMapper;
import com.loan.infrastructure.persistence.LoanDecisionRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class LoanEvaluatorService {

    private final LoanApplicationValidator validator = new LoanApplicationValidator();
    private final EligibilityEngine eligibilityEngine = new EligibilityEngine();
    private final InterestRateCalculator interestCalculator = new InterestRateCalculator();
    private final EmiCalculator emiCalculator = new EmiCalculator();
    private final RiskBandClassifier riskClassifier = new RiskBandClassifier();
    private final OfferRule offerRule = new EmiThresholdOfferRule();

    private final LoanDecisionRepository repository;

    public LoanEvaluatorService(LoanDecisionRepository repository) {
        this.repository = repository;
    }

    public LoanDecision evaluate(LoanApplication application) {

        List<String> validationErrors = validator.validate(application);

        if (!validationErrors.isEmpty()) {
            LoanDecision decision = new LoanDecision(
                    application.getId(),
                    "REJECTED",
                    null,
                    null,
                    validationErrors
            );

            save(decision);
            return decision;
        }

        List<String> failures = eligibilityEngine.evaluate(application);

        if (!failures.isEmpty()) {
            LoanDecision decision = new LoanDecision(
                    application.getId(),
                    "REJECTED",
                    null,
                    null,
                    failures
            );

            save(decision);
            return decision;
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
            LoanDecision decision = new LoanDecision(
                    application.getId(),
                    "REJECTED",
                    null,
                    null,
                    List.of(offerFailure.get())
            );

            save(decision);
            return decision;
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

        LoanDecision decision = new LoanDecision(
                application.getId(),
                "APPROVED",
                riskBand.name(),
                offer,
                null
        );

        save(decision);
        return decision;
    }

    private void save(LoanDecision decision) {
        LoanDecisionEntity entity = LoanDecisionMapper.toEntity(decision);
        repository.save(entity);
    }
}