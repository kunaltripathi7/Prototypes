Is the vending machine single-instance (local state) or networked with central inventory/telemetry? — affects offline behavior and reconciliation.

What payment methods are supported (coins/bills/card/wallet) and should we model change dispensing? — determines money and dispenser logic.

What are valid failure modes and compensation (item jam, out-of-stock after payment, payment failure)? — drives transactional flow and refund/rollback.

Do we track per-slot inventory and expiry, and do we support restock/maintenance workflows? — impacts state model and administrative APIs.

How strict must concurrency be (multiple simultaneous button presses or remote commands)? — defines locking on dispense per-slot and idempotency of dispense actions.