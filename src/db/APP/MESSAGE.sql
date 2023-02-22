create table MESSAGE
(
    ID                INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    CHAT_ID           INTEGER not null,
    PERSON_ID         INTEGER not null,
    TEXT              VARCHAR(5000),
    IMAGE             VARCHAR(1000),
    MESSAGE_TIMESTAMP TIMESTAMP,
    constraint "MESSAGE_CHAT_ID_fk"
        foreign key (CHAT_ID) references CHAT(ID),
    constraint "MESSAGE_PERSON_ID_fk"
        foreign key (PERSON_ID) references PERSON(ID)
);

