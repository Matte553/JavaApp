create table CHAT
(
    ID      INTEGER default AUTOINCREMENT: start 1 increment 1 generated always as identity
        constraint "CHAT_pk"
        primary key,
    SUBJECT VARCHAR(255)
);

