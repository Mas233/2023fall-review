DROP DATABASE IF EXISTS exampledb6;
CREATE DATABASE exampledb6 DEFAULT CHARACTER SET utf8;
USE exampledb6;

create table spitter (
  id int AUTO_INCREMENT  PRIMARY KEY not null,
  username varchar(25) not null,
  password varchar(25) not null,
  fullName varchar(100) not null,
  email varchar(50) not null,
  updateByEmail boolean not null
)ENGINE=InnoDB;

create table spittle (
  id int AUTO_INCREMENT primary key,
  spitter integer not null,
  message varchar(2000) not null,
  postedTime datetime not null,
  foreign key (spitter) references spitter(id)
)ENGINE=InnoDB;


insert into Spitter (username, password, fullname, email, updateByEmail) values ('habuma', 'password', 'Craig Walls', 'craig@habuma.com', false);
insert into Spitter (username, password, fullname, email, updateByEmail) values ('mwalls', 'password', 'Michael Walls', 'mwalls@habuma.com', true);
insert into Spitter (username, password, fullname, email, updateByEmail) values ('chuck', 'password', 'Chuck Wagon', 'chuck@habuma.com', false);
insert into Spitter (username, password, fullname, email, updateByEmail) values ('artnames', 'password', 'Art Names', 'art@habuma.com', true);

insert into Spittle (spitter, message, postedTime) values (1, 'This is a test spittle message', '2012-06-09 22:00:00');
insert into Spittle (spitter, message, postedTime) values (1, 'This is another test spittle message', '2012-06-09 22:10:00');
insert into Spittle (spitter, message, postedTime) values (1, 'This is a third test spittle message', '2012-07-04 23:30:00');
insert into Spittle (spitter, message, postedTime) values (2, 'Hello from Chuck!', '2012-03-25 12:15:00');
insert into Spittle (spitter, message, postedTime) values (4, 'Hello from Art!', '2012-03-25 12:15:00');
insert into Spittle (spitter, message, postedTime) values (4, 'Hello again from Art!', '2012-03-25 12:25:00');
insert into Spittle (spitter, message, postedTime) values (4, 'Hola from Arthur!', '2012-03-25 12:35:00');
insert into Spittle (spitter, message, postedTime) values (4, 'Buenos Dias from Art!', '2012-03-25 12:45:00');
insert into Spittle (spitter, message, postedTime) values (4, 'Ni Hao from Art!', '2012-03-25 12:55:00');
insert into Spittle (spitter, message, postedTime) values (4, 'Guten Tag from Art!', '2012-03-25 13:05:00');
insert into Spittle (spitter, message, postedTime) values (4, 'Konnichi wa from Art!', '2012-03-25 13:15:00');
insert into Spittle (spitter, message, postedTime) values (4, 'Buon giorno from Art!', '2012-03-25 13:25:00');
insert into Spittle (spitter, message, postedTime) values (4, 'Bonjour from Art!', '2012-03-25 13:35:00');
insert into Spittle (spitter, message, postedTime) values (4, 'Aloha from Art!', '2012-03-25 13:45:00');
insert into Spittle (spitter, message, postedTime) values (4, 'God dag from Art!', '2012-03-25 13:55:00');

commit;
