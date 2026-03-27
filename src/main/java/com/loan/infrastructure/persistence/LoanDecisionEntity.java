package com.loan.infrastructure.persistence;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class LoanDecisionEntity {

    @Id
    private UUID id;

    private String status;

    private String riskBand;

    private BigDecimal interestRate;

    private BigDecimal emi;

    private BigDecimal totalPayable;

    private String rejectionReasons;

    public LoanDecisionEntity() {}


    public UUID getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getRiskBand() {
        return riskBand;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public BigDecimal getEmi() {
        return emi;
    }

    public BigDecimal getTotalPayable() {
        return totalPayable;
    }

    public String getRejectionReasons() {
        return rejectionReasons;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRiskBand(String riskBand) {
        this.riskBand = riskBand;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public void setEmi(BigDecimal emi) {
        this.emi = emi;
    }

    public void setTotalPayable(BigDecimal totalPayable) {
        this.totalPayable = totalPayable;
    }

    public void setRejectionReasons(String rejectionReasons) {
        this.rejectionReasons = rejectionReasons;
    }
}