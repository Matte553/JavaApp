create table MESSAGE
(
    ID                INTEGER default AUTOINCREMENT: start 1 increment 1 generated always as identity,
    CHAT_ID           INTEGER not null,
    PERSON_ID         INTEGER not null,
    TEXT              VARCHAR(5000),
    IMAGE             VARCHAR(1000),
    MESSAGE_TIMESTAMP TIMESTAMP,
    constraint "MESSAGE_pk"
        primary key (ID),
    constraint "MESSAGE_CHAT_ID_fk"
        foreign key (CHAT_ID) references CHAT,
    constraint "MESSAGE_PERSON_ID_fk"
        foreign key (PERSON_ID) references PERSON
);

