create schema if not exists ibank authorization test;

create table ibank.balance
(
    user_id         text primary key,
    current_balance numeric
);

create table ibank.operations
(
    id                bigint primary key generated always as identity,
    user_id           text                      not null,
    FOREIGN KEY (user_id) REFERENCES ibank.balance (user_id),
    payee_id             text,
    FOREIGN KEY (payee_id) REFERENCES ibank.balance (user_id),
    amount            numeric                   not null,
    type_of_operation int                       not null,
    operation_date    date default current_date not null
);

insert into ibank.balance (user_id, current_balance)
values ('user-1', '10000');

insert into ibank.operations (user_id, amount, type_of_operation)
values ('user-1', '10000', '1');