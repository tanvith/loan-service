package com.loan.domain.model;

import java.math.BigDecimal;

public class Applicant {

    private final String name;
    private final int age;
    private final BigDecimal monthlyIncome;
    private final EmploymentType employmentType;
    private final int creditScore;

    public Applicant(String name,
                     int age,
                     BigDecimal monthlyIncome,
                     EmploymentType employmentType,
                     int creditScore) {

        this.name = name;
        this.age = age;
        this.monthlyIncome = monthlyIncome;
        this.employmentType = employmentType;
        this.creditScore = creditScore;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public BigDecimal getMonthlyIncome() { return monthlyIncome; }
    public EmploymentType getEmploymentType() { return employmentType; }
    public int getCreditScore() { return creditScore; }
}