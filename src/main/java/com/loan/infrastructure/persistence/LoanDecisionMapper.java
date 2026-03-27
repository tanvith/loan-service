package com.loan.infrastructure.persistence;

import com.loan.application.LoanDecision;

public class LoanDecisionMapper {

    public static LoanDecisionEntity toEntity(LoanDecision decision) {

        LoanDecisionEntity entity = new LoanDecisionEntity();

        entity.setId(decision.getApplicationId());
        entity.setStatus(decision.getStatus());
        entity.setRiskBand(decision.getRiskBand());

        if (decision.getOffer() != null) {
            entity.setInterestRate(decision.getOffer().getInterestRate());
            entity.setEmi(decision.getOffer().getEmi());
            entity.setTotalPayable(decision.getOffer().getTotalPayable());
        }

        if (decision.getRejectionReasons() != null) {
            entity.setRejectionReasons(String.join(",", decision.getRejectionReasons()));
        }

        return entity;
    }
}