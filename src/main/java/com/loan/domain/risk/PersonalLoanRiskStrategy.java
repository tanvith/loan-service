package com.loan.domain.risk;

import com.loan.domain.model.LoanApplication;
import com.loan.domain.model.LoanPurpose;

public class PersonalLoanRiskStrategy implements LoanRiskStrategy {

    @Override
    public boolean supports(LoanPurpose purpose) {
        return purpose == LoanPurpose.PERSONAL;
    }

    @Override
    public RiskBand classify(int creditScore, LoanApplication application) {
        if (creditScore >= 750) return RiskBand.LOW;
        else if (creditScore >= 650) return RiskBand.MEDIUM;
        else return RiskBand.HIGH;
    }
}
