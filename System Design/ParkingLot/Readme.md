Clarifying:
Do we support reservations in advance or only real-time on-arrival allocation? — changes model: reservation lifecycle vs ephemeral allocation.

How are slots categorized (size types, EV charging, handicapped) and do vehicles have size/type rules? — necessary for allocation policy and validation.

Should a reserved/allocated slot be held for a fixed timeout before being released (no-show)? If yes, what timeframe? — drives timers and cleanup.

How should competing requests for the same slot be resolved (first-commit, optimistic CAS, queuing)? — concurrency hotspot handling.

Do we need per-slot occupancy sensors / real-time updates, or is system single-source-of-truth (entry gate issues tracked by server)? — affects event model and reconciliation logic.