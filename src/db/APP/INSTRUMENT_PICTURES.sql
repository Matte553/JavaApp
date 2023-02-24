create table INSTRUMENT_PICTURES
(
    IMAGE_URL     VARCHAR(1600) not null
        unique,
    INSTRUMENT_ID INTEGER       not null
        unique
        constraint "INSTRUMENT_PICTURES_INSTRUMENT_ID_fk"
            references INSTRUMENT
);

