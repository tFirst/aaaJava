create table accounting
(
id number,
login varchar2(30),
start_date date,
end_date date,
volume number,
resource varchar2(30));

create table auth
(
id number,
login varchar2(30),
hash varchar2(500),
salt varchar2(500));

create table autorise
(
id number,
login varchar2(30),
resource varchar2(50),
role varchar2(10));

create table roles
(
id number,
role varchar2(10));

create sequence acc_seq
increment by 1
start with 1;