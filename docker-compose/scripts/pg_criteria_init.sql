\c db_criteria;

CREATE TABLE tb_criteria (
    id                  uuid gen_random_uuid() not null primary key,
    level               varchar(10),
    description         varchar(200),
    type                varchar(50), -- CERTIFICATIONS/PROJECTS/YEARS
    expected_value      int,
    created_at          timestamp
);

INSERT INTO tb_criteria (level, description, type, expected_value, created_at)
VALUES
    ('JUNIOR', 'AÑOS DE EXPERIENCIA', 'YEARS', 0),
    ('JUNIOR', 'CERTIFICACIONES OFICIALES', 'CERTIFICATIONS', 0),
    ('JUNIOR', 'PROYECTOS EN PRODUCCION', 'PROJECTS', 0),
    ('MIDDLE', 'AÑOS DE EXPERIENCIA', 'YEARS', 5),
    ('MIDDLE', 'CERTIFICACIONES OFICIALES', 'CERTIFICATIONS', 3),
    ('MIDDLE', 'PROYECTOS EN PRODUCCION', 'PROJECTS', 3),
    ('SENIOR', 'AÑOS DE EXPERIENCIA', 'YEARS', 8),
    ('SENIOR', 'CERTIFICACIONES OFICIALES', 'CERTIFICATIONS', 5),
    ('SENIOR', 'PROYECTOS EN PRODUCCION', 'PROJECTS', 5),
    ('MASTER', 'AÑOS DE EXPERIENCIA', 'YEARS', 15),
    ('MASTER', 'CERTIFICACIONES OFICIALES', 'CERTIFICATIONS', 10),
    ('MASTER', 'PROYECTOS EN PRODUCCION', 'PROJECTS', 10);
