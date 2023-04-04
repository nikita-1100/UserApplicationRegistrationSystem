alter table if exists request drop constraint if exists FKpclp2gw6xd4rr96f19kkhja2j;
alter table if exists user_role drop constraint if exists FKg7fr1r7o0fkk41nfhnjdyqn7b;
drop table if exists app_user cascade;
drop table if exists request cascade;
drop table if exists user_role cascade;
create table app_user (id bigint not null, password varchar(255), username varchar(255), primary key (id));
create table request (id bigserial not null, created timestamp(6), message varchar(255), status varchar(255), author_id bigint, primary key (id));
create table user_role (user_id bigint not null, role varchar(255));
alter table if exists user_role add constraint user_roles_unique unique (user_id, role);
alter table if exists request add constraint FKpclp2gw6xd4rr96f19kkhja2j foreign key (author_id) references app_user;
alter table if exists user_role add constraint FKg7fr1r7o0fkk41nfhnjdyqn7b foreign key (user_id) references app_user;