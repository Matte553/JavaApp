create table INSTRUMENT
(
    TYPE        VARCHAR(255),
    NAME        VARCHAR(255),
    PRICE       DOUBLE,
    DESCRIPTION VARCHAR(3600),
    ID          INTEGER not null
        constraint "INSTRUMENT_pk"
            primary key
);

