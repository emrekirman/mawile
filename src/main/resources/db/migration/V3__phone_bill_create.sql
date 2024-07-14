create table t_phone_bill
(
    id             serial not null,
    transaction_id integer,
    created_date   timestamp(6),
    operator       varchar(255),
    phone_number   varchar(255),
    primary key (id)
);

alter table if exists t_phone_bill
    add constraint FK8rv8jvbrwdv6fheq969k14rqd foreign key (transaction_id) references t_transaction;