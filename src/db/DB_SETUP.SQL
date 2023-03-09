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

INSERT INTO PERSON (FIRSTNAME, LASTNAME, MAIL, PHONE, CUSTOMER_NUMBER) VALUES('Anders', 'Andersson', 'AndersAndersson@gmail.com', '060-0000000', '000000');
INSERT INTO PERSON (FIRSTNAME, LASTNAME, MAIL, PHONE, CUSTOMER_NUMBER) VALUES('Anton', 'Persson', 'AntonPerson@gmail.com', '070-11111111', '111111');
INSERT INTO PERSON (FIRSTNAME, LASTNAME, MAIL, PHONE, CUSTOMER_NUMBER) VALUES('Lisa', 'Karlsson', 'LisaKarlsson@gmail.com', '070-2222222', '222222');

INSERT INTO CHAT (SUBJECT) VALUES ('Reservation');
INSERT INTO CHAT (SUBJECT) VALUES ('Reparation');

INSERT INTO CHATMEMBER (CHAT_ID, PERSON_ID) VALUES (1, 1);
INSERT INTO CHATMEMBER (CHAT_ID, PERSON_ID) VALUES (1, 2);

INSERT INTO CHATMEMBER (CHAT_ID, PERSON_ID) VALUES (2, 1);
INSERT INTO CHATMEMBER (CHAT_ID, PERSON_ID) VALUES (2, 3);

INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (1, 1, 'Hejsan kund Anton. Du har reserverat gitarr "Cort".', null, null);
INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (1, 2, 'Tjena Anders. Tackar. ', null, null);
INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (1, 1, 'Hej kund, nu är gitarren redo att hämtas.', null, null);
INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (1, 2, 'Toppen Anders. Jag kommer imorgon. ', null, null);

INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (2, 1, 'Hejsan kund Lisa. Du har reserverat gitarr "Fender".', null, null);
INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (2, 3, 'Hej Anders. Vad bra att det funkade. ', null, null);
INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (2, 1, 'Hej Lisa, det tar någon dag till innan gitarren är klar', null, null);
INSERT INTO MESSAGE (CHAT_ID, PERSON_ID, TEXT, IMAGE, MESSAGE_TIMESTAMP) VALUES (2, 3, 'Ingen fara Anders!', null, null);

INSERT INTO INSTRUMENT (TYPE, NAME, PRICE, DESCRIPTION) VALUES ('Gitarr', 'Yamaha C40', 1000, 'En fin begagnad gitarr med nya strängar.');
INSERT INTO INSTRUMENT (TYPE, NAME, PRICE, DESCRIPTION) VALUES ('Ukulele', 'Cascha Premium Mahagoni Tenor', 600, 'Bästa modellen på marknaden. Som ny!');
INSERT INTO INSTRUMENT (TYPE, NAME, PRICE, DESCRIPTION) VALUES ('Elgitarr', 'Mooer GTRS Guitars Wing 900 Int APU', 13500, 'Låtsas vara Jimi Hendrix med den här värstingen!');
INSERT INTO INSTRUMENT (TYPE, NAME, PRICE, DESCRIPTION) VALUES ('Keyboard', 'Yamaha', 3500, 'Världens bästa keyboard, köp den här och ge mig pengar.');
INSERT INTO INSTRUMENT (TYPE, NAME, PRICE, DESCRIPTION) VALUES ('Gitarr', 'Cort', 1700, 'En fantastiskt ful gitarr.');
INSERT INTO INSTRUMENT (TYPE, NAME, PRICE, DESCRIPTION) VALUES ('Elgitarr', 'Fender', 2900, 'Bästa jag någonsin har varit med om.');

INSERT INTO INSTRUMENT_PICTURES (IMAGE_URL, INSTRUMENT_ID) VALUES ('classic_guitar_yamaha_c40.jpg', 1);
INSERT INTO INSTRUMENT_PICTURES (IMAGE_URL, INSTRUMENT_ID) VALUES ('classic_guitar_yamaha_c40_2.jpg', 1);
INSERT INTO INSTRUMENT_PICTURES (IMAGE_URL, INSTRUMENT_ID) VALUES ('cascha_premium_1.jpg', 2);
INSERT INTO INSTRUMENT_PICTURES (IMAGE_URL, INSTRUMENT_ID) VALUES ('cascha_premium_2.jpg', 2);
INSERT INTO INSTRUMENT_PICTURES (IMAGE_URL, INSTRUMENT_ID) VALUES ('mooer_1.jpg', 3);
INSERT INTO INSTRUMENT_PICTURES (IMAGE_URL, INSTRUMENT_ID) VALUES ('mooer_2.jpg', 3);
INSERT INTO INSTRUMENT_PICTURES (IMAGE_URL, INSTRUMENT_ID) VALUES ('keyboard_yamaha_1.jpg', 4);
INSERT INTO INSTRUMENT_PICTURES (IMAGE_URL, INSTRUMENT_ID) VALUES ('keyboard_yamaha_2.jpg', 4);
INSERT INTO INSTRUMENT_PICTURES (IMAGE_URL, INSTRUMENT_ID) VALUES ('classic_guitar_cort.jpg', 5);
INSERT INTO INSTRUMENT_PICTURES (IMAGE_URL, INSTRUMENT_ID) VALUES ('fender.jpg', 6);

INSERT INTO RESERVATION (RESERVATION_NUMBER, INSTRUMENT_ID, PERSON_ID) VALUES (6123, 5, 2);
INSERT INTO RESERVATION (RESERVATION_NUMBER, INSTRUMENT_ID, PERSON_ID) VALUES (7123, 6, 3);

INSERT INTO REPARATIONS (ERRAND_NUMBER, PERSON_ID, DESCRIPTION, TYPE) VALUES (1516, 2, 'Min ukulele saknar flera strängar och har spruckit lite.', 'Ukulele');
INSERT INTO REPARATIONS (ERRAND_NUMBER, PERSON_ID, DESCRIPTION, TYPE) VALUES (2671, 3, 'En tangent på mitt keyboard funkar inte alls.', 'Keyboard');

INSERT INTO LOG (TEXT, PERSON_ID, LOG_TIMESTAMP) VALUES ('Detta är min log för kund Lisa. ', 3, null);
INSERT INTO LOG (TEXT, PERSON_ID, LOG_TIMESTAMP) VALUES ('Nu är jag klar med lisas arbete', 3, null);

INSERT INTO CALENDAR_EVENT(START_TIME, STOP_TIME, START_DATE, STOP_DATE, SUBJECT, FREE_TEXT, REFERENCE_NUMBER, PERSON_ID) VALUES ('14:00:00', '16:00:00', '2023-04-02', '2023-04-02', 'Reparation', null, 1516, 2);
INSERT INTO CALENDAR_EVENT(START_TIME, STOP_TIME, START_DATE, STOP_DATE, SUBJECT, FREE_TEXT, REFERENCE_NUMBER, PERSON_ID) VALUES ('16:00:00', '16:20:00', '2023-04-04', '2023-04-04', 'Reservation', null, 7123, 3);