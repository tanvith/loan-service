package com.loan.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoanDecisionRepository extends JpaRepository<LoanDecisionEntity, UUID> {
}