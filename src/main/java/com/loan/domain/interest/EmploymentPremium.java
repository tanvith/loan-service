package com.loan.domain.interest;

import com.loan.domain.model.EmploymentType;
import com.loan.domain.model.LoanApplication;

import java.math.BigDecimal;

public class EmploymentPremium implements InterestComponent {

    @Override
    public BigDecimal apply(LoanApplication application) {

        if (application.getApplicant().getEmploymentType() == EmploymentType.SELF_EMPLOYED) {
            return BigDecimal.valueOf(1);
        }

        return BigDecimal.ZERO;
    }
}