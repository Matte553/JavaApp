create table APP."Person"
(
    FIRSTNAME VARCHAR(100),
    LASTNAME  VARCHAR(100),
    PHONE     VARCHAR(100),
    MAIL      VARCHAR(100),
    ID        INTEGER not null
        constraint "Person_pk"
            primary key
);

