create table ibank.operations (
    operation_id bigint primary key generated always as identity,
    user_id integer,
    FOREIGN KEY (user_id) REFERENCES ibank.balance (id),
    sum numeric,
    type_of_operation numeric
);
