CREATE TABLE clients(
    id serial primary key,
    name varchar(255)    
);

CREATE TABLE books(
    id serial primary key,
    name varchar(255),
    clients_id int references clients(id)
);

CREATE TABLE who_has_what_book(
    id serial primary key,
    bools_id int references books(id),
    clients_id int references clients(id)
);