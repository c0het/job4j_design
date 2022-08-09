create table equipments(
    id serial primary key,
    name varchar(255)
    status bool,
    Release date int,
    Department text    
);
insert into equipment(name, status, ReleaseDate, Department) values ('Монитор', true, 2020, 'Бугалтерия');
update equipment set status = false;
delete from equipment name;
select * from equipment;
