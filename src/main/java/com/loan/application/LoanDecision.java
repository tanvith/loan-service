package com.loan.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class LoanDecision {

    private final UUID applicationId;
    private final String status;
    private final String riskBand;
    private final LoanOffer offer;
    private final List<String> rejectionReasons;

    public LoanDecision(UUID applicationId,
                        String status,
                        String riskBand,
                        LoanOffer offer,
                        List<String> rejectionReasons) {
        this.applicationId = applicationId;
        this.status = status;
        this.riskBand = riskBand;
        this.offer = offer;
        this.rejectionReasons = rejectionReasons;
    }

    public UUID getApplicationId() { return applicationId; }
    public String getStatus() { return status; }
    public String getRiskBand() { return riskBand; }
    public LoanOffer getOffer() { return offer; }
    public List<String> getRejectionReasons() { return rejectionReasons; }
}