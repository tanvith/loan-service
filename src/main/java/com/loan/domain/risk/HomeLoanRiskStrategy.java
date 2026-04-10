package com.loan.domain.risk;

import com.loan.domain.model.LoanApplication;
import com.loan.domain.model.LoanPurpose;

public class HomeLoanRiskStrategy implements LoanRiskStrategy {

    @Override
    public boolean supports(LoanPurpose purpose) {
        return purpose == LoanPurpose.HOME;
    }

    @Override
    public RiskBand classify(int creditScore, LoanApplication application) {
        if(application.getApplicant().getAge()<25){
            return RiskBand.LOW;
        }
        if (creditScore >= 750) return RiskBand.LOW;
        else if (creditScore >= 650) return RiskBand.MEDIUM;
        else return RiskBand.HIGH;
    }
}
