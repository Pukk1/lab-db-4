#!/bin/sh
set -e

psql --username postgres --dbname postgres <<-EOSQL
  CREATE DATABASE lab;
  CREATE ROLE lab WITH ENCRYPTED PASSWORD 'lab' LOGIN;
  GRANT ALL PRIVILEGES ON DATABASE lab TO lab;
EOSQL

psql --username postgres --dbname lab <<-EOSQL
  GRANT ALL ON SCHEMA public TO lab;
EOSQL
