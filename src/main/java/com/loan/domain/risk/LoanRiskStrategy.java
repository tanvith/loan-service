package com.loan.domain.risk;

import com.loan.domain.model.LoanApplication;
import com.loan.domain.model.LoanPurpose;

public interface LoanRiskStrategy {
    boolean supports(LoanPurpose purpose);
    RiskBand classify(int creditScore, LoanApplication application);
}
