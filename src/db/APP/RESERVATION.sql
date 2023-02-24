create table RESERVATION
(
    RESERVATION_NUMBER INTEGER not null
        unique,
    INSTRUMENT_ID      INTEGER not null
        unique
        constraint "RESERVATION_INSTRUMENT_ID_fk"
            references INSTRUMENT,
    PERSON_ID          INTEGER not null
        unique
        constraint "RESERVATION_PERSON_ID_fk"
            references PERSON
);

