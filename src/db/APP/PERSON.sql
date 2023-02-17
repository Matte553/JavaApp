create table PERSON
(
    ID              INTEGER default AUTOINCREMENT: start 1 increment 1 generated always as identity
        constraint "PERSON_pk"
        primary key,
    FIRSTNAME       VARCHAR(255),
    LASTNAME        VARCHAR(255),
    MAIL            VARCHAR(255),
    PHONE           VARCHAR(30),
    CUSTOMER_NUMBER VARCHAR(6)
);

