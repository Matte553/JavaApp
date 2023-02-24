create table CHAT
(
    ID      INTEGER GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1)
        primary key,
    SUBJECT VARCHAR(255)
);

