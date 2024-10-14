\c db_human_resources;

CREATE TABLE tb_promoted_evaluation (
    id uuid             default gen_random_uuid() not null primary key,
    employee_id         uuid,
    eligible_group_id   uuid,
    promoted            boolean,
    promoted_by         varchar(50),
    promoted_at         date,
    created_at          timestamp without time zone,
    updated_at          timestamp without time zone
);
