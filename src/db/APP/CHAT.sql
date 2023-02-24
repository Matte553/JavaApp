create table CHAT
(
    ID      INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
    SUBJECT VARCHAR(255)
);

