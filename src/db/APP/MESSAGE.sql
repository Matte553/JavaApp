create table MESSAGE
(
    ID                INTEGER GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1)
        primary key,
    CHAT_ID           INTEGER not null
        constraint "MESSAGE_CHAT_ID_fk"
            references CHAT,
    PERSON_ID         INTEGER not null
        constraint "MESSAGE_PERSON_ID_fk"
            references PERSON,
    TEXT              VARCHAR(5000),
    IMAGE             VARCHAR(1000),
    MESSAGE_TIMESTAMP TIMESTAMP
);

