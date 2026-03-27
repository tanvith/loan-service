package com.loan.domain.risk;

public class RiskBandClassifier {

    public RiskBand classify(int creditScore) {

        if (creditScore >= 750) {
            return RiskBand.LOW;
        } else if (creditScore >= 650) {
            return RiskBand.MEDIUM;
        } else {
            return RiskBand.HIGH;
        }
    }
}