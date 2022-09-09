drop database if exists superheroDBtest;
create database superheroDBtest;

use superheroDBtest;

create table superhero(
id int primary key auto_increment,
name varchar(50) not null,
description varchar(250),
power varchar(50) not null
);

create table organization(
id int primary key auto_increment,
name varchar(50) not null,
address varchar(200) not null,
description varchar(250)
);

create table super_organization(
superId int not null,
orgId int not null,
primary key(superId, orgId),
foreign key(superId) references superhero(id),
foreign key(orgId) references organization(id)
);

create table location(
id int primary key auto_increment,
name varchar(50) not null,
address varchar(200) not null,
latitude int not null,
longitude int not null,
description varchar(250)
);

create table sighting(
id int primary key auto_increment,
superId int not null,
locationId int not null,
date date not null,
foreign key(superId) references superhero(id),
foreign key(locationId) references location(id)
);