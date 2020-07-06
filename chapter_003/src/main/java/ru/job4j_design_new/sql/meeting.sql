CREATE table meeting (
    id SERIAL PRIMARY KEY,
    m_name VARCHAR(100)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    u_name VARCHAR(100)
);

CREATE TABLE status (
    id SERIAL PRIMARY KEY,
    s_name VARCHAR(100)
);

CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    meeting_id INT REFERENCES meeting (id),
    user_id INT REFERENCES users (id),
    status_id INT REFERENCES status (id)
);

1. Нужно написать запрос, который получит список всех заявок и количество подтвердивших участников
SELECT m.m_name, COUNT (status_id) FROM meeting AS m
INNER JOIN items AS i ON m.id = i.meeting_id
INNER JOIN status AS s ON s.id = i.status_id
WHERE s.s_name = 'confirmed' GROUP BY m.m_name;

2. Нужно получить все совещания, где не было ни одной заявки на посещения
SELECT m.m_name FROM meeting AS m
LEFT JOIN items AS i ON m.id = i.meeting_id
WHERE i.meeting_id IS NULL;