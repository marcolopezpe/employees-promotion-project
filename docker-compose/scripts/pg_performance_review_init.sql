\c db_performance_review;

CREATE TABLE tb_eligible_group (
    id uuid 		    default gen_random_uuid() not null primary key,
    group_evaluation    varchar(20),
    year                int,
    month               int,
    date_evaluation     date,
    created_at          timestamp without time zone
);

CREATE TABLE tb_eligible_employee (
    id uuid             default gen_random_uuid() not null primary key,
    employee_id         uuid,
    eligible_group_id   uuid,
    created_at          timestamp without time zone
);

CREATE TABLE tb_parameter_eligible (
    id uuid             default gen_random_uuid() not null primary key,
    name                varchar(100),
    description         varchar(500),
    parameter_key       varchar(100),
    parameter_value     varchar(100),
    created_at          timestamp without time zone
);

INSERT INTO tb_parameter_eligible (name, description, parameter_key, parameter_value, created_at)
VALUES ('POR CANTIDAD DE AÑOS', 'EL EMPLEADO SERÁ PROMOVIDO SI TIENE AL MENOS ESTA CANTIDAD DE AÑOS EN LA EMPRESA.', 'YEARS_OF_ENTRY_DATE', '1', now());
