package com.loan.interfaces.rest;

import com.loan.application.LoanDecision;
import com.loan.application.LoanEvaluatorService;
import com.loan.domain.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/applications")
public class LoanController {

    private final LoanEvaluatorService service;

    public LoanController(LoanEvaluatorService service) {
        this.service = service;
    }

    @PostMapping
    public LoanDecision create(@RequestBody LoanApplicationRequest request) {

        Applicant applicant = new Applicant(
                request.applicant.name,
                request.applicant.age,
                request.applicant.monthlyIncome,
                EmploymentType.valueOf(request.applicant.employmentType),
                request.applicant.creditScore
        );

        Loan loan = new Loan(
                request.loan.amount,
                request.loan.tenureMonths,
                LoanPurpose.valueOf(request.loan.purpose)
        );

        LoanApplication application = new LoanApplication(
                UUID.randomUUID(),
                applicant,
                loan
        );

        return service.evaluate(application);
    }
}