create table APP."Chat"
(
    ID      INTEGER not null
        constraint "Chat_pk"
            primary key,
    SUBJECT VARCHAR(100)
);

