create table comments (
    id serial primary key,
    name text
);

create table attachs (
    id serial primary key,
    name text
);

create table item(
    id serial primary key,
    comments_id int references comments(id),
    attachs_id int references attachs(id)
);

create table users (
    id serial primary key,
    name text,
    item_id int references item(id)
);

create table rules (
    id serial primary key,
    name text
);

create table role (
    id serial primary key,
    name text,
    users_id int references users(id)
);

create table role_rules (
    id serial primary key,
    role_id int references role(id),
    rules_id int references rules(id)
);

create table category (
    id serial primary key,
    name text,
    item_id int references item(id)
);

create table state (
    id serial primary key,
    name text,
    item_id int references item(id)
);