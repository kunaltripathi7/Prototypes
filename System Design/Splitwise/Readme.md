Are we modelling exact debts between users or group-level shared expenses (split equally / by share / custom)? — determines data model for Expense vs. Balance graph.

Should the system compute minimal settlement transactions (minimize transfers) or just show per-person balances? — affects algorithmic complexity (graph reduction / NP-ish heuristics).

What currency & rounding rules apply and do we need multi-currency support? — impacts Money value object and precision rules.

How do concurrent edits to the same expense or settlement get resolved (optimistic locking / last-write-wins / versioning)? — determines conflict resolution approach.

Do we need batch settlement (periodic clearing) or immediate payments (link to payments)? — affects transactional model and external integration.