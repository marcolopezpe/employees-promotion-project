\c db_criteria;

CREATE TABLE tb_criteria (
    id                  uuid default gen_random_uuid() not null primary key,
    level               varchar(10),
    description         varchar(200),
    type                varchar(20), -- CERTIFICATIONS/PROJECTS/YEARS
    expected_value      int,
    created_at          timestamp
);

INSERT INTO tb_criteria (level, description, type, expected_value, created_at)
VALUES
    ('JUNIOR', 'AÑOS DE EXPERIENCIA', 'YEARS', 0, CURRENT_TIMESTAMP),
    ('JUNIOR', 'CERTIFICACIONES OFICIALES', 'CERTIFICATIONS', 0, CURRENT_TIMESTAMP),
    ('JUNIOR', 'PROYECTOS EN PRODUCCION', 'PROJECTS', 0, CURRENT_TIMESTAMP),
    ('MIDDLE', 'AÑOS DE EXPERIENCIA', 'YEARS', 5, CURRENT_TIMESTAMP),
    ('MIDDLE', 'CERTIFICACIONES OFICIALES', 'CERTIFICATIONS', 3, CURRENT_TIMESTAMP),
    ('MIDDLE', 'PROYECTOS EN PRODUCCION', 'PROJECTS', 3, CURRENT_TIMESTAMP),
    ('SENIOR', 'AÑOS DE EXPERIENCIA', 'YEARS', 8, CURRENT_TIMESTAMP),
    ('SENIOR', 'CERTIFICACIONES OFICIALES', 'CERTIFICATIONS', 5, CURRENT_TIMESTAMP),
    ('SENIOR', 'PROYECTOS EN PRODUCCION', 'PROJECTS', 5, CURRENT_TIMESTAMP),
    ('MASTER', 'AÑOS DE EXPERIENCIA', 'YEARS', 15, CURRENT_TIMESTAMP),
    ('MASTER', 'CERTIFICACIONES OFICIALES', 'CERTIFICATIONS', 10, CURRENT_TIMESTAMP),
    ('MASTER', 'PROYECTOS EN PRODUCCION', 'PROJECTS', 10, CURRENT_TIMESTAMP);
