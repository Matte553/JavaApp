create table APP."ChatMember"
(
    ID         INTEGER not null
        constraint "ChatMember_pk"
            primary key,
    "chatID"   INTEGER
        constraint "ChatMember_Chat_ID_fk"
            references APP."Chat",
    "personID" INTEGER
        constraint "ChatMember_Person_ID_fk"
            references APP."Person"
);

