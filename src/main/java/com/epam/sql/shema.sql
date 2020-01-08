
CREATE SCHEMA IF NOT EXISTS service;

CREATE TABLE persons
(
    id SERIAL NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    surname varchar(255) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    type varchar(30) NOT NULL
);