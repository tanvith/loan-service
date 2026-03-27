package com.loan.application.validation;

import com.loan.domain.model.LoanApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LoanApplicationValidator {

    public List<String> validate(LoanApplication application) {

        List<String> errors = new ArrayList<>();

        int age = application.getApplicant().getAge();
        if (age < 21 || age > 60) {
            errors.add("INVALID_AGE");
        }

        int score = application.getApplicant().getCreditScore();
        if (score < 300 || score > 900) {
            errors.add("INVALID_CREDIT_SCORE");
        }

        BigDecimal income = application.getApplicant().getMonthlyIncome();
        if (income.compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("INVALID_INCOME");
        }

        BigDecimal amount = application.getLoan().getAmount();
        if (amount.compareTo(BigDecimal.valueOf(10_000)) < 0 ||
                amount.compareTo(BigDecimal.valueOf(5_000_000)) > 0) {
            errors.add("INVALID_LOAN_AMOUNT");
        }

        int tenure = application.getLoan().getTenureMonths();
        if (tenure < 6 || tenure > 360) {
            errors.add("INVALID_TENURE");
        }

        return errors;
    }
}