create table users
(
    UUIDAsChar varchar(36) not null
        primary key,
    firstname  varchar(20) null,
    lastname   varchar(20) null,
    email      varchar(25) not null,
    password   text        not null,
    constraint email
        unique (email)
);