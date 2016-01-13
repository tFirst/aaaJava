insert into auth
(id, login, hash, salt)
values
(1, 'jdoe', 'a3af848c1a2a86d09bb39453c08c928f', '5gth987hc6');

insert into autorise
(id, login, resource, role)
values
(1, 'jdoe', 'a', 'READ');

insert into roles
(id, role)
values
(1, 'READ');

insert into roles
(id, role)
values
(2, 'WRITE');

insert into roles
(id, role)
values
(3, 'EXEC');