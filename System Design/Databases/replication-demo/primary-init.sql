CREATE USER replication_user WITH REPLICATION ENCRYPTED PASSWORD 'replication_password';
SELECT pg_create_physical_replication_slot('replication_slot_1');
SELECT pg_create_physical_replication_slot('replication_slot_2');
