create table REPERATIONS
(
    ERRAND_NUMBER INTEGER not null
        unique,
    PERSON_ID     INTEGER not null
        unique
        constraint "REPERATIONS_PERSON_ID_fk"
            references PERSON,
    DESCRIPTION   VARCHAR(3600),
    TYPE          VARCHAR(255)
);

