\c db_query_promotion;

CREATE TABLE tb_promotion (
    id                  uuid default gen_random_uuid() not null primary key,
    employee_id         uuid,
    employee_firstname  varchar(100),
    employee_lastname   varchar(100),
    proposed_level      varchar(10),
    status              varchar(20),
    request_date        date,
    decision_date       date,
    leader_id           uuid,
    leader_firstname    varchar(100),
    leader_lastname     varchar(100),
    leader_comments     varchar(500),
    period              int,
    created_at          timestamp
);
