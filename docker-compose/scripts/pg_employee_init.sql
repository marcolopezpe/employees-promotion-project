\c db_employee;

CREATE TABLE tb_employee (
    id uuid 		default gen_random_uuid() not null primary key,
    firstname		varchar(100),
    lastname		varchar(100),
    address     	varchar(500),
    email           varchar(100),
    entry_date      date,
    created_at      timestamp without time zone
);

INSERT INTO tb_employee (firstname, lastname, address, entry_date, created_at)
VALUES ('MARCO ANTONIO', 'LOPEZ CAMACHO', 'LIMA PERU', 'MARCOLOPEZPE@OUTLOOK.COM', '2023-10-15', now());
