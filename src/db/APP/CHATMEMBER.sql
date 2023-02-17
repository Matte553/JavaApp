create table CHATMEMBER
(
    ID        INTEGER default AUTOINCREMENT: start 1 increment 1 generated always as identity,
    CHAT_ID   INTEGER not null,
    PERSON_ID INTEGER not null,
    constraint "CHATMEMBER_pk"
        primary key (ID),
    constraint "CHATMEMBER_CHAT_ID_fk"
        foreign key (CHAT_ID) references CHAT,
    constraint "CHATMEMBER_PERSON_ID_fk"
        foreign key (PERSON_ID) references PERSON
);

