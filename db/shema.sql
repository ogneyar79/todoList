create database todo;
create table tasks;

CREATE table IF NOT EXISTS tasks
(
    id          SERIAL PRIMARY KEY,
    description TEXT,
    created     TIMESTAMP,
    done        BOOLEAN
);

