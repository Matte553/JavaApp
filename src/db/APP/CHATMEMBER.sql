create table CHATMEMBER
(
    ID        INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    CHAT_ID   INTEGER not null,
    PERSON_ID INTEGER not null,
    constraint "CHATMEMBER_CHAT_ID_fk"
        foreign key (CHAT_ID) references CHAT(ID),
    constraint "CHATMEMBER_PERSON_ID_fk"
        foreign key (PERSON_ID) references PERSON(ID)
);

