-- ---------------------------------------------------- --
-- ............... SCRIP BD help_desk ................. --
-- ............ Author: CE.  Version 1.1 .............. --
-- ---------------------------------------------------- --
/*drop database if exists help_desk; 
create database help_desk;*/
use help_desk;
create table employee(
id_employee int not null primary key auto_increment,
first_name varchar(40) not null,
last_name varchar(40),
email varchar(50),
phone_number varchar(15),
department varchar(40)
);
create table users(
id_user int not null primary key auto_increment,
user_name varchar(40) not null,
password varchar(40) not null,
id_employee int not null,
foreign key (id_employee) references employee(id_employee)
);
create table  ticket(
id_ticket int not null primary key auto_increment,
id_employee int not null,
device varchar(40) not null, -- tipo de dispositivo en el que ocurrio el incidente (Desktop, Laptop, Tablet, SmartPhone, Dispositivo de salida)
type varchar(40) not null,  -- tipo de incidente -Dejó de funcionar, -Aparece un error, -No enciende, -No responde, -Sufrio un siniestro.
date_of date not null, -- fecha del hecho
time_of varchar(20), -- hora de lo ocurrido
description_of longtext, 
estatus int not null,
foreign key(id_employee) references employee(id_employee)
);
insert into employee values(0,"admin","admin","admin@admin.com",null,"administracion");
insert into users values(0,"admin","1234",1);

insert into employee values(0,"Christian","Muñoz","Naruxtian@hotmail.com",null,"administracion");
insert into users values(0,"Chris","Pass",2);

update ticket set estatus=1 where id_ticket=5;

select * from ticket;
select * from employee;