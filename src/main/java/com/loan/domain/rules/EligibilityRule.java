package com.loan.domain.rules;

import com.loan.domain.model.LoanApplication;

import java.util.Optional;

public interface EligibilityRule {

    Optional<String> evaluate(LoanApplication application);
}