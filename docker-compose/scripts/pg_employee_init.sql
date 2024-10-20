\c db_employee;

CREATE TABLE tb_employee (
    id                  uuid default gen_random_uuid() not null primary key,
    firstname		    varchar(100),
    lastname		    varchar(100),
    address     	    varchar(500),
    email               varchar(100),
    current_level       varchar(10), -- JUNIOR/MIDDLE/SENIOR/MASTER
    hire_date           date,
    certifications      int,
    production_projects int,
    created_at          timestamp
);

CREATE TABLE tb_level_history (
    id                  uuid default gen_random_uuid() not null primary key,
    employee_id         uuid,
    previos_level       VARCHAR(10),
    new_level           VARCHAR(10),
    change_date         date,
    evaluated_by        uuid,
    comments            varchar(500),
    created_at          timestamp
);

INSERT INTO tb_employee (
    firstname,
    lastname, 
    address, 
    email, 
    current_level,
    hire_date,
    certifications,
    production_projects, 
    created_at)
VALUES (
    'MARCO ANTONIO', 
    'LOPEZ CAMACHO', 
    'LIMA PERU', 
    'MARCOLOPEZPE@OUTLOOK.COM', 
    'JUNIOR',
    '2023-10-16',
    0,
    0,
    CURRENT_TIMESTAMP);
