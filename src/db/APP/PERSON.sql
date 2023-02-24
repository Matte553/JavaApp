create table PERSON
(
    ID              INTEGER GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1)
        primary key,
    FIRSTNAME       VARCHAR(255),
    LASTNAME        VARCHAR(255),
    MAIL            VARCHAR(255),
    PHONE           VARCHAR(30),
    CUSTOMER_NUMBER VARCHAR(6)
);

