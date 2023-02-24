create table REPARATIONS
(
    ERRAND_NUMBER INTEGER not null,
    PERSON_ID     INTEGER not null
        constraint "REPARATIONS_PERSON_ID_fk"
            references PERSON,
    DESCRIPTION   VARCHAR(3600),
    TYPE          VARCHAR(255),
    primary key (ERRAND_NUMBER, PERSON_ID)
);

