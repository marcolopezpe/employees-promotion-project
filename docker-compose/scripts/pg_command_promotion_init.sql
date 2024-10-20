\c db_command_promotion;

CREATE TABLE tb_promotion (
    id                  uuid default gen_random_uuid() not null primary key,
    employee_id         uuid,
    proposed_level      varchar(10),
    status              varchar(20),
    request_date        date,
    decision_date       date,
    leader_id           uuid,
    leader_comments     varchar(500),
    period              int,
    created_at          timestamp
);
