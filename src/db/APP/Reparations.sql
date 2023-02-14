create table APP."Reparations"
(
    ID               INTEGER not null
        constraint "Reparations_pk"
            primary key,
    "CustomerID"     INTEGER not null
        constraint "Reparations_Person_ID_fk"
            references APP."Person",
    "Description"    VARCHAR(7000),
    "InstrumentType" VARCHAR(50)
);

