Is this single-server authoritative (server holds game state) or peer-to-peer? — affects cheating prevention and concurrency model.

How many simultaneous players per game and how many concurrent games do we expect? — impacts state holding, memory, and sharding.

What are the valid actions/events (roll dice, move, use power-ups) and are any actions reversible? — defines domain model and state transitions.

Do we need persistence / crash-recovery of in-progress games or ephemeral sessions only? — decides persistence and snapshotting.

How strict must turn-order be enforced and how to handle disconnected players (timeout, bot takeover)? — drives locking, timeout logic, and reconciliation.