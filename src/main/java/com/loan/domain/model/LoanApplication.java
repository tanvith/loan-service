package com.loan.domain.model;

import java.util.UUID;

public class LoanApplication {

    private final UUID id;
    private final Applicant applicant;
    private final Loan loan;

    public LoanApplication(UUID id, Applicant applicant, Loan loan) {
        this.id = id;
        this.applicant = applicant;
        this.loan = loan;
    }

    public UUID getId() { return id; }
    public Applicant getApplicant() { return applicant; }
    public Loan getLoan() { return loan; }
}