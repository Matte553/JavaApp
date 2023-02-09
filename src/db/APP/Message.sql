create table APP."Message"
(
    ID         INTEGER not null
        constraint "Message_pk"
            primary key,
    "personID" INTEGER
        constraint "Message_Person_ID_fk"
            references APP."Person",
    "chatID"   INTEGER
        constraint "Message_Chat_ID_fk"
            references APP."Chat",
    TEXT       VARCHAR(100),
    IMAGE      VARCHAR(10000),
    "timeSent" TIME,
    "dateSent" DATE
);

