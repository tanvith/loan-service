# DEVELOPMENT NOTES

## Overall Approach

I approached this problem by first trying to clearly separate **what the system does** from **how it is exposed or stored**.

Instead of jumping straight into Spring or database modeling, I started by defining the **core domain objects** (Applicant, Loan, LoanApplication) and building the business logic around them. This helped keep the logic independent of frameworks and easier to reason about.

From there, I incrementally added layers:

* Domain (core logic)
* Application (orchestration)
* Infrastructure (persistence)
* Interface (REST API)

This made it easier to evolve the system step by step without tightly coupling everything.

---

## Key Design Decisions

### 1. Separation of Concerns

One of the main decisions was to keep different responsibilities clearly separated:

* **Validation** → checks if input is even valid
* **Eligibility rules** → decides if a valid application should be rejected
* **Offer rules** → applied after calculations (e.g., EMI thresholds)
* **Service layer** → orchestrates the full flow
* **Persistence layer** → handles storage for audit

This avoided mixing things like validation errors with business rejections.

---

### 2. Domain-First Design (No @Entity in Domain)

I intentionally avoided using JPA annotations in domain classes.

The domain layer represents **business concepts**, not database structure.
Persistence is handled separately using entity + mapper.

This keeps the domain clean and makes it easier to change storage later without impacting logic.

---

### 3. Value Objects (Money, InterestRate)

Instead of passing raw `BigDecimal` everywhere, I introduced value objects like:

* `Money`
* `InterestRate`

This helped:

* Enforce constraints (e.g., no negative money)
* Avoid repeated logic (e.g., monthly rate conversion)
* Make the code more expressive

---

### 4. Rule Engine for Eligibility

Eligibility checks are implemented using a simple rule interface.

Each rule:

* Is independent
* Returns a failure reason if violated

The engine just runs all rules and collects failures.

This makes it easy to:

* Add new rules
* Test rules in isolation
* Avoid large `if-else` blocks

---

### 5. Interest Calculation Using Composition

Interest is built from multiple components:

* Risk premium
* Employment premium
* Loan size premium

Each component is implemented separately and combined in a calculator.

This avoids hardcoding everything in one place and allows easy extension.

---

### 6. Offer Rule Separation

The EMI ≤ 50% check is implemented as an **offer-level rule**, not part of the eligibility engine.

Reason:

* It depends on computed values (EMI)
* Keeping it separate avoids coupling rule engine with calculation logic

---

### 7. Exception-Based Validation

Validation failures throw a custom exception, which is handled globally and returned as HTTP 400.

This ensures:

* Clear distinction between invalid input and business rejection
* Proper API semantics

---

### 8. Persistence for Audit Only

The system stores decisions mainly for audit purposes.

I kept persistence minimal:

* One entity (`LoanDecisionEntity`)
* Simple repository
* Mapper from domain → entity

No unnecessary database modeling was added.

---

## Trade-offs Considered

### Simplicity vs Over-Engineering

I considered implementing a full pipeline-based architecture (with multiple processing stages), but decided against it for now.

Instead, I kept:

* Rule engine for eligibility
* Simple offer rule abstraction

This keeps the system clean without becoming overly complex.

---

### Lombok vs Manual Code

Initially considered Lombok for entities, but manual getters/setters are safer for portability and reduce dependency issues during evaluation.

---

### Where to Handle Persistence

I chose to handle persistence inside the **service layer**, not the controller.

This keeps:

* Controller thin (only HTTP handling)
* Service responsible for orchestration + side effects

---

## Assumptions

* Interest is compounded monthly using a standard formula
* Base interest rate is fixed (can be externalized later)
* No concurrency or distributed concerns (single instance system)
* Persistence is only for audit, not querying or analytics

---

## Improvements with More Time

If I had more time, I would:

1. Introduce a **decision pipeline abstraction**

    * Separate stages like validation, eligibility, pricing, and offer evaluation

2. Externalize business rules/config:

    * Interest rates
    * Thresholds (50%, 60%)

3. Add better test coverage:

    * Unit tests for each rule
    * Integration tests for API

4. Improve API layer:

    * Request validation using annotations
    * Better error response structure

5. Add logging and observability:

    * Track decision flow
    * Debug rule failures easily

---

## Final Thoughts

The main goal was to keep the system:

* **Readable**
* **Extensible**
* **Correct in terms of business logic**

Rather than over-optimizing early, I focused on building something that is easy to evolve and reason about.

---
