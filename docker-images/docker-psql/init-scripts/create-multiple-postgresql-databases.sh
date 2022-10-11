#!/bin/bash

# Credits to https://github.com/mrts/docker-postgresql-multiple-databases/blob/master/create-multiple-postgresql-databases.sh
# This is an improvement of that file
# To create an image, this file follows some rules
# It is inspired by the way Spring scans for script and data files
# The root folder of all script files must be docker-entrypoint-initdb.d/multiple/
# `multiple` is a choice.
# Originally, the docker entrypoint located in /user/local/bin looks for all .sql, .gz and .xz files to import on docker-entrypoint-initdb.d/.
# There is not clean way around this. Check the entrypoint details on https://github.com/docker-library/postgres/blob/master/docker-entrypoint.sh.
# This script will search for sql files in the root folder docker-entrypoint-initdb.d/multiple/ prepended like schema-<database>.sql and data-<database>.sql
# You can disable this by setting variable POSTGRES_SCAN_DISABLED to true
# Databases are declared in POSTGRES_MULTIPLE_DATABASES as a comma separated string of database tags
# You can specify a bundle of scripts to execute in a certain folder per database like so <database>:<folder> as one of these elements
# If you wish to use folders with the same name as the database and don't want to use this notation then you have to set POSTGRES_FOLDER_MAPPING to true
# To specify the database user use POSTGRES_USER
# To specify the database password use POSTGRES_PASSWORD
# The script creates a user with the given name who has the given password
# It also creates another user with the database name and the given password per database created
# If both match, then only one user is created with the database name and the given password
# This script is available to download in a small example I've created in https://github.com/jesperancinha/project-signer/tree/master/docker-templates/docker-psql

set -e
set -u

POSTGRES_SCAN_DISABLED="${POSTGRES_SCAN_DISABLED:-false}"
POSTGRES_FOLDER_MAPPING="${POSTGRES_FOLDER_MAPPING:-false}"

function create_user_and_database() {
  database=$(echo "$command" | awk -F':' '{print $1}')
	echo "  Creating user and database '$database'"
	psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" -tc "SELECT 1 FROM pg_user WHERE usename = '$database'" | \
	  grep -q 1 || \
	  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
	    CREATE USER $database with PASSWORD '$POSTGRES_PASSWORD';
	    DROP DATABASE IF EXISTS $database;
	    CREATE DATABASE $database;
	    GRANT ALL PRIVILEGES ON DATABASE $database TO $database;
EOSQL
}

function import_file_to_database() {
  echo "Importing file $2 to database $1..."
  psql -U "$POSTGRES_USER" -d "$1" -f "$2"
}

function import_files_from_folder() {
  directory=$1
  database=$2
  echo "Database bundle $directory for database $database requested"
  if [ -d "$directory" ]; then
    for script in "$directory"/*.sql; do
        echo "Request importing file $script to database $database"
        import_file_to_database "$database" "$script"
    done
  else
    echo -e "\e[38;5;208mWARNING\e[0m: No script bundle directory \e[38;5;82m$directory\e[0m found for database \e[38;5;82m$database\e[0m"
  fi
}

function create_and_import_files(){
  local rootDir="docker-entrypoint-initdb.d/multiple/"
  if [ -n "$POSTGRES_MULTIPLE_DATABASES" ]; then
    echo "Multiple database creation requested: $POSTGRES_MULTIPLE_DATABASES"
    for command in $(echo "$POSTGRES_MULTIPLE_DATABASES" | tr ',' ' '); do
      create_user_and_database "$command"
      if [[ "$command" == *":"* ]]; then
        database=$(echo "$command" | awk -F':' '{print $1}')
        directory=$rootDir$(echo "$command" | awk -F':' '{print $2}')
        import_files_from_folder "$directory" "$database"
      elif [ "$POSTGRES_FOLDER_MAPPING" == true ]; then
         if [[ "$command" != *":"* ]]; then
           import_files_from_folder "$rootDir$database" "$database"
         fi
      fi
      if [ "$POSTGRES_SCAN_DISABLED" != true ]; then
        database=$(echo "$command" | awk -F':' '{print $1}')
        echo "Auto-scanning files for database $database"
        echo "Auto-scanning schema files"
        schema_file=$rootDir"schema-$database.sql"
        if [ -f "$schema_file" ]; then
                import_file_to_database "$database" "$schema_file"
                echo "Auto-scanning data files"
                schema_data=$rootDir"data-$database.sql"
                if [ -f "$schema_data" ]; then
                        import_file_to_database "$database" "$schema_data"
                else
                  echo "\e[38;5;208mWARNING\e[0m: No data file \e[38;5;82m$schema_data\e[0m detected for database \e[38;5;82m$database\e[0m"
                fi
        else
            echo "\e[38;5;208mWARNING\e[0m: No schema file \e[38;5;82m$schema_file\e[0m detected for database \e[38;5;82m$database\e[0m"
        fi
      fi
    done
    echo "Multiple databases created"
  fi
}

create_and_import_files;
