create table RESERVATION
(
    RESERVATION_NUMBER INTEGER not null,
    INSTRUMENT_ID      INTEGER not null
        constraint "RESERVATION_INSTRUMENT_ID_fk"
            references INSTRUMENT,
    PERSON_ID          INTEGER not null
        constraint "RESERVATION_PERSON_ID_fk"
            references PERSON,
    constraint "RESERVATION_pk"
        primary key (RESERVATION_NUMBER, INSTRUMENT_ID, PERSON_ID)
);

