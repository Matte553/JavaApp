create table CHATMEMBER
(
    ID        INTEGER default AUTOINCREMENT: start 1 increment 1 generated always as identity
        constraint "CHATMEMBER_pk"
        primary key,
    CHAT_ID   INTEGER not null
        constraint "CHATMEMBER_CHAT_ID_fk"
            references CHAT,
    PERSON_ID INTEGER not null
        constraint "CHATMEMBER_PERSON_ID_fk"
            references PERSON
);

