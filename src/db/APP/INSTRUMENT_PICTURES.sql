create table INSTRUMENT_PICTURES
(
    IMAGE_URL     VARCHAR(1600) not null,
    INSTRUMENT_ID INTEGER       not null
        constraint "INSTRUMENT_PICTURES_INSTRUMENT_ID_fk"
            references INSTRUMENT,
    constraint "INSTRUMENT_PICTURES_pk"
        primary key (IMAGE_URL, INSTRUMENT_ID)
);

