create table CHAT
(
    ID      INTEGER default AUTOINCREMENT: start 1 increment 1 generated always as identity,
    SUBJECT VARCHAR(255),
    constraint "CHAT_pk"
        primary key (ID)
);

