package com.loan.interfaces.rest;

import java.math.BigDecimal;

public class LoanApplicationRequest {

    public Applicant applicant;
    public Loan loan;

    public static class Applicant {
        public String name;
        public int age;
        public BigDecimal monthlyIncome;
        public String employmentType;
        public int creditScore;
    }

    public static class Loan {
        public BigDecimal amount;
        public int tenureMonths;
        public String purpose;
    }
}