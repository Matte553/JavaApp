create table PERSON
(
    ID              INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    FIRSTNAME       VARCHAR(255),
    LASTNAME        VARCHAR(255),
    MAIL            VARCHAR(255),
    PHONE           VARCHAR(30),
    CUSTOMER_NUMBER VARCHAR(6)
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

INSERT INTO PERSON (FIRSTNAME, LASTNAME, MAIL, PHONE, CUSTOMER_NUMBER) VALUES('Anders', 'Andersson', 'AndersAndersson@gmail.com', '060-0000000', '000000');
INSERT INTO PERSON (FIRSTNAME, LASTNAME, MAIL, PHONE, CUSTOMER_NUMBER) VALUES('Anton', 'Persson', 'AntonPerson@gmail.com', '070-11111111', '111111');
INSERT INTO PERSON (FIRSTNAME, LASTNAME, MAIL, PHONE, CUSTOMER_NUMBER) VALUES('Lisa', 'Karlsson', 'LisaKarlsson@gmail.com', '070-2222222', '222222');

INSERT INTO CHAT (SUBJECT) VALUES ('Reservation');
INSERT INTO CHAT (SUBJECT) VALUES ('Reperation');

INSERT INTO CHATMEMBER (CHAT_ID, PERSON_ID) VALUES (1, 1);
INSERT INTO CHATMEMBER (CHAT_ID, PERSON_ID) VALUES (1, 2);

INSERT INTO CHATMEMBER (CHAT_ID, PERSON_ID) VALUES (2, 1);
INSERT INTO CHATMEMBER (CHAT_ID, PERSON_ID) VALUES (2, 3);

INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (0, 1, 'Hejsan kund Anton. Du har reserverat gitarr "Cort".', null, null);
INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (0, 2, 'Tjena Anders. Tackar. ', null, null);
INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (0, 1, 'Hej kund, nu är gitarren redo att hämtas.', null, null);
INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (0, 2, 'Toppen Anders. Jag kommer imorgon. ', null, null);

INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (1, 1, 'Hejsan kund Lisa. Du har reserverat gitarr "Fender".', null, null);
INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (1, 3, 'Hej Anders. Vad bra att det funkade. ', null, null);
INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (1, 1, 'Hej Lisa, det tar någon dag till innan gitarren är klar', null, null);
INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (1, 3, 'Ingen fara Anders!', null, null);