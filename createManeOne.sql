CREATE TABLE clients(
    id serial primary key,
    name varchar(255)    
);

CREATE TABLE books(
    id serial primary key,
    name varchar(255),
    clients_id int references clients(id)
);