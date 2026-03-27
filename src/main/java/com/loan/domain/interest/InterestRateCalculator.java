package com.loan.domain.interest;

import com.loan.domain.model.LoanApplication;

import java.math.BigDecimal;
import java.util.List;

public class InterestRateCalculator {

    private static final BigDecimal BASE_RATE = BigDecimal.valueOf(12);

    private final List<InterestComponent> components = List.of(
            new RiskPremium(),
            new EmploymentPremium(),
            new LoanSizePremium()
    );

    public BigDecimal calculate(LoanApplication application) {

        BigDecimal total = BASE_RATE;

        for (InterestComponent component : components) {
            total = total.add(component.apply(application));
        }

        return total;
    }
}