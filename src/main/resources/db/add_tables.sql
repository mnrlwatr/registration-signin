
create table users
(
    id        uuid        not null
        primary key,
    firstname varchar(20),
    lastname  varchar(20),
    email     varchar(25) not null
        constraint email
            unique,
    password  text        not null
);


