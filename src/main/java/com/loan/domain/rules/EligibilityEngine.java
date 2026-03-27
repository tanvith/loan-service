package com.loan.domain.rules;

import com.loan.domain.model.LoanApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EligibilityEngine {

    private final List<EligibilityRule> rules = new ArrayList<>();

    public EligibilityEngine() {
        rules.add(new CreditScoreRule());
        rules.add(new AgeTenureRule());
        rules.add(new EmiToIncomeRule());
    }

    public List<String> evaluate(LoanApplication application) {

        List<String> failures = new ArrayList<>();

        for (EligibilityRule rule : rules) {
            Optional<String> result = rule.evaluate(application);
            result.ifPresent(failures::add);
        }

        return failures;
    }
}