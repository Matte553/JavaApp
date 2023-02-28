create table PERSON
(
    ID              INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    FIRSTNAME       VARCHAR(255),
    LASTNAME        VARCHAR(255),
    MAIL            VARCHAR(255),
    PHONE           VARCHAR(30),
    CUSTOMER_NUMBER INTEGER
);

create table CHAT
(
    ID     INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    SUBJECT VARCHAR(255)
);

create table INSTRUMENT
(
    ID          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    TYPE        VARCHAR(255),
    NAME        VARCHAR(255),
    PRICE       DOUBLE,
    DESCRIPTION VARCHAR(3600)
);

create table INSTRUMENT_PICTURES
(
    IMAGE_URL     VARCHAR(1600) not null,
    INSTRUMENT_ID INTEGER       not null
        constraint "INSTRUMENT_PICTURES_INSTRUMENT_ID_fk"
            references INSTRUMENT(ID),
    constraint "INSTRUMENT_PICTURES_pk"
        primary key (IMAGE_URL, INSTRUMENT_ID)
);

create table CHATMEMBER
(
    CHAT_ID   INTEGER not null
        constraint "CHATMEMBER_CHAT_ID_fk"
            references CHAT(ID),
    PERSON_ID INTEGER not null
        constraint "CHATMEMBER_PERSON_ID_fk"
            references PERSON(ID),
    constraint "CHATMEMBER_pk"
        primary key (PERSON_ID, CHAT_ID)
);

create table MESSAGE
(
    ID                INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    CHAT_ID           INTEGER not null
        constraint "MESSAGE_CHAT_ID_fk"
            references CHAT(ID),
    PERSON_ID         INTEGER not null
        constraint "MESSAGE_PERSON_ID_fk"
            references PERSON(ID),
    TEXT              VARCHAR(5000),
    IMAGE             VARCHAR(1000),
    MESSAGE_TIMESTAMP TIMESTAMP
);

create table REPARATIONS
(
    ERRAND_NUMBER INTEGER not null,
    PERSON_ID     INTEGER not null
        constraint "REPARATIONS_PERSON_ID_fk"
            references PERSON(ID),
    DESCRIPTION   VARCHAR(3600),
    TYPE          VARCHAR(255),
    primary key (ERRAND_NUMBER, PERSON_ID)
);

create table RESERVATION
(
    RESERVATION_NUMBER INTEGER not null,
    INSTRUMENT_ID      INTEGER not null
        constraint "RESERVATION_INSTRUMENT_ID_fk"
            references INSTRUMENT(ID),
    PERSON_ID          INTEGER not null
        constraint "RESERVATION_PERSON_ID_fk"
            references PERSON(ID),
    constraint "RESERVATION_pk"
        primary key (RESERVATION_NUMBER, INSTRUMENT_ID, PERSON_ID)
);
