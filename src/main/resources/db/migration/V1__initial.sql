create table t_bank_account
(
    balance        float(53),
    id             serial not null,
    created_date   timestamp(6),
    account_number varchar(255),
    owner          varchar(255),
    primary key (id)
);
create table t_transaction
(
    amount          float(53),
    bank_account_id integer,
    id              serial not null,
    created_date    timestamp(6),
    approval_code   varchar(255),
    type            varchar(255),
    primary key (id)
);
alter table if exists t_transaction
    add constraint FK9f6t7r2l4ypuyp17r4gtmm5lu foreign key (bank_account_id) references t_bank_account;
