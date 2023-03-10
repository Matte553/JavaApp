create table PERSON
(
    ID              INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    FIRSTNAME       VARCHAR(255),
    LASTNAME        VARCHAR(255),
    MAIL            VARCHAR(255),
    PHONE           VARCHAR(30),
    CUSTOMER_NUMBER VARCHAR(255)
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
    ID            INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    IMAGE_URL     VARCHAR(1600) not null,
    INSTRUMENT_ID INTEGER       not null
        constraint "INSTRUMENT_PICTURES_INSTRUMENT_ID_fk"
            references INSTRUMENT(ID)
);

create table CHATMEMBER
(
    ID        INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    CHAT_ID   INTEGER not null
        constraint "CHATMEMBER_CHAT_ID_fk"
            references CHAT(ID),
    PERSON_ID INTEGER not null
        constraint "CHATMEMBER_PERSON_ID_fk"
            references PERSON(ID)
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
    ID            INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    ERRAND_NUMBER INTEGER not null,
    PERSON_ID     INTEGER not null
        constraint "REPARATIONS_PERSON_ID_fk"
            references PERSON(ID),
    DESCRIPTION   VARCHAR(3600),
    TYPE          VARCHAR(255)
);

create table RESERVATION
(
    ID                 INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    RESERVATION_NUMBER INTEGER not null,
    INSTRUMENT_ID      INTEGER not null
        constraint "RESERVATION_INSTRUMENT_ID_fk"
            references INSTRUMENT(ID),
    PERSON_ID          INTEGER not null
        constraint "RESERVATION_PERSON_ID_fk"
            references PERSON(ID)
);

create table CALENDAR_EVENT
(
    ID               INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    START_TIME       time        not null,
    STOP_TIME        time        not null,
    START_DATE       date        not null,
    STOP_DATE        date        not null,
    SUBJECT          VARCHAR(50) not null,
    FREE_TEXT        VARCHAR(5000),
    REFERENCE_NUMBER INTEGER     not null,
    PERSON_ID        INTEGER     not null
        constraint "CALENDAR_PERSON_ID_fk"
            references PERSON(ID)
);

create table LOG
(
    ID                INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    TEXT              VARCHAR(5000),
    PERSON_ID         INTEGER
        constraint "LOG_PERSON_ID_fk"
            references PERSON(ID),
    LOG_TIMESTAMP     TIMESTAMP
);
