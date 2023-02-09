create table APP."Instrument"
(
    ID            INTEGER not null
        constraint "Instrument_pk"
            primary key,
    "Name"        VARCHAR(200),
    "Type"        VARCHAR(200),
    "Price"       VARCHAR(15),
    "Description" VARCHAR(7000),
    "ReservedBy"  INTEGER
        constraint "Instrument_Person_ID_fk"
            references APP."Person"
);

