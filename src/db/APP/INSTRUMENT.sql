create table INSTRUMENT
(
    ID          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1)
        constraint "INSTRUMENT_pk"
            primary key,
    TYPE        VARCHAR(255),
    NAME        VARCHAR(255),
    PRICE       DOUBLE,
    DESCRIPTION VARCHAR(3600)
);

