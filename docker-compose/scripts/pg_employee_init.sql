\c db_employee;

CREATE TABLE tb_employee (
    id                  uuid default gen_random_uuid() not null primary key,
    firstname		    varchar(100),
    lastname		    varchar(100),
    address     	    varchar(500),
    email               varchar(100),
    current_level       varchar(10), -- JUNIOR/MIDDLE/SENIOR/MASTER
    period_level        int,
    hire_date           date,
    certifications      int,
    production_projects int,
    leader_id           uuid,
    created_at          timestamp
);

CREATE TABLE tb_level_history (
    id                  uuid default gen_random_uuid() not null primary key,
    employee_id         uuid,
    previous_level      VARCHAR(10),
    new_level           VARCHAR(10),
    change_date         date,
    evaluated_by        uuid,
    comments            varchar(500),
    created_at          timestamp
);

BEGIN;
    WITH first_insert AS (
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
            'JOHN', 
            'DOE', 
            'USA', 
            'JOHN.DOE@OUTLOOK.COM', 
            'MASTER',
            '2008-11-16',
            10,
            10,
            CURRENT_TIMESTAMP)
        RETURNING id
    )

INSERT INTO tb_employee (
        firstname,
        lastname, 
        address, 
        email, 
        current_level,
        hire_date,
        certifications,
        production_projects,
        leader_id,
        created_at)
VALUES (
    'MARCO ANTONIO', 
    'LOPEZ CAMACHO', 
    'LIMA PERU', 
    'MARCOLOPEZPE@OUTLOOK.COM', 
    'JUNIOR',
    '2018-10-16',
    3,
    3,
    (SELECT id FROM first_insert),
    CURRENT_TIMESTAMP);

COMMIT;