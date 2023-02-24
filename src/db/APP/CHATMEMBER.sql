create table CHATMEMBER
(
    ID        INTEGER GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1)
        primary key,
    CHAT_ID   INTEGER not null
        constraint "CHATMEMBER_CHAT_ID_fk"
            references CHAT,
    PERSON_ID INTEGER not null
        constraint "CHATMEMBER_PERSON_ID_fk"
            references PERSON
);

