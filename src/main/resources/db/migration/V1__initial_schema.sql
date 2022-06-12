create table account
(
    id varchar(36) not null,
    name varchar(256) null,
    address varchar(256) null,
    constraint account_pk
        primary key (id)
);

create table login_history
(
    id bigint auto_increment not null ,
    account_id varchar(36) not null,
    login_at datetime not null,
    constraint login_history_pk
        primary key (id)
);