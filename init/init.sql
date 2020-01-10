
create table persons(
id serial not null primary key,
name varchar(255) not null,
surname varchar(255) not null,
email varchar(255) not null unique,
type varchar(30) not null
);

create table ads(
person_id integer references persons (id),
id varchar(255) not null primary key,
title varchar(255) not null,
body text not null,
category varchar(30) not null,
phone varchar(30) not null,
dateOfCreation date not null
);