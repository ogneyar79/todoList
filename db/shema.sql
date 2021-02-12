create database todo;
create table tasks;

CREATE table IF NOT EXISTS tasks
(
    id          SERIAL PRIMARY KEY,
    description TEXT,
    created     TIMESTAMP,
    done        BOOLEAN
);

CREATE table IF NOT EXISTS users
(
    id          SERIAL PRIMARY KEY,
    name VARCHAR(60) not null,
    email     VARCHAR not null unique,
    password     VARCHAR  (30) not null
);
AlTER table tasks(
    add column user_id INTEGER  REFERENCES users (id) ON DELETE CASCADE
);

