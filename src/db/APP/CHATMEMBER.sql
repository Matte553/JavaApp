create table CHATMEMBER
(
    CHAT_ID   INTEGER not null
        constraint "CHATMEMBER_CHAT_ID_fk"
            references CHAT,
    PERSON_ID INTEGER not null
        constraint "CHATMEMBER_PERSON_ID_fk"
            references PERSON,
    constraint "CHATMEMBER_pk"
        primary key (PERSON_ID, CHAT_ID)
);

