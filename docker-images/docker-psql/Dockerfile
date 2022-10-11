FROM postgres:alpine

COPY postgresql.conf /etc/postgresql/postgresql.conf

COPY init-scripts/create-multiple-postgresql-databases.sh /docker-entrypoint-initdb.d/create-multiple-postgresql-databases.sh

EXPOSE 5432

