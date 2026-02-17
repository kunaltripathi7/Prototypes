#!/bin/bash
set -e

if [ ! -s "$PGDATA/PG_VERSION" ]; then
    echo "Starting streaming replica..."
    until PGPASSWORD=replication_password pg_basebackup -h postgres-primary -D "$PGDATA" -U replication_user -Fp -Xs -R

    donedo
        echo "Waiting for primary to be ready..."
        sleep 2
    echo "Replica initialized successfully"
fi

exec docker-entrypoint.sh postgres
