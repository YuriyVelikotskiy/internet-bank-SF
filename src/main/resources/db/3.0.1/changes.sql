alter table ibank.operations
    drop constraint operations_payee_id_fkey,
    drop constraint operations_user_id_fkey;

alter table ibank.operations
    add foreign key (payee_id) references ibank.balance (user_id) on delete cascade,
    add foreign key (user_id) references ibank.balance (user_id) on delete cascade;
