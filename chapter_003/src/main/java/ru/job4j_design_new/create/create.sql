CREATE DATABASE roles_and_rules_db;

\c roles_and_rules_db;

CREATE TABLE roles (
    role_id SERIAL PRIMARY KEY,
    rule_name VARCHAR(30) NOT NULL
);

INSERT INTO roles (rule_name) VALUES ('admin');
INSERT INTO roles (rule_name) VALUES ('creator');
INSERT INTO roles (rule_name) VALUES ('guest');

CREATE TABLE rules (
    rule_id SERIAL PRIMARY KEY,
    rule_name VARCHAR(30) NOT NULL,
    description TEXT
);

INSERT INTO rules (rule_name) VALUES ('view');
INSERT INTO rules (rule_name) VALUES ('edit');
INSERT INTO rules (rule_name) VALUES ('delete');
INSERT INTO rules (rule_name) VALUES ('create');

CREATE TABLE roles_rules (
    role_id INT REFERENCES roles (role_id),
    rule_id INT REFERENCES rules (rule_id),
    PRIMARY KEY (role_id, rule_id)
);

INSERT INTO roles_rules VALUES (
(SELECT role_id FROM roles WHERE role_name='admin'),
(SELECT rule_id FROM rules WHERE rule_name='delete')
);
INSERT INTO roles_rules VALUES (
(SELECT role_id FROM roles WHERE role_name='admin'),
(SELECT rule_id FROM rules WHERE rule_name='edit')
);
INSERT INTO roles_rules VALUES (
(SELECT role_id FROM roles WHERE role_name='creator'),
(SELECT rule_id FROM rules WHERE rule_name='view')
);
INSERT INTO roles_rules VALUES (
(SELECT role_id FROM roles WHERE role_name='guest'),
 (SELECT rule_id FROM rules WHERE rule_name='view')
);

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(30) NOT NULL,
    role_id INT REFERENCES roles (role_id)
);

INSERT INTO users (user_name, role_id) VALUES ('Ivan', (SELECT role_id FROM roles WHERE rule_name = 'admin'));
INSERT INTO users (user_name, role_id) VALUES ('Anna', (SELECT role_id FROM roles WHERE rule_name = 'creator'));
INSERT INTO users (user_name, role_id) VALUES ('Vova', (SELECT role_id FROM roles WHERE rule_name = 'guest'));

 CREATE TABLE category (
    categ_id SERIAL PRIMARY KEY,
    categ_name VARCHAR(30) NOT NULL,
    description TEXT
);

INSERT INTO category (categ_name) VALUES ('computer_repair');
INSERT INTO category (categ_name) VALUES ('account_creation');
INSERT INTO category (categ_name) VALUES ('salary');
INSERT INTO category (categ_name) VALUES ('equipment');

CREATE TABLE state (
    state_id SERIAL PRIMARY KEY,
    status_name VARCHAR(30) NOT NULL
);

INSERT INTO state (status_name) VALUES ('open');
INSERT INTO state (status_name) VALUES ('close');
INSERT INTO state (status_name) VALUES ('take to work');
INSERT INTO state (status_name) VALUES ('reject');

CREATE TABLE items (
    item_id SERIAL PRIMARY KEY,
    item_number INT NOT NULL,
    user_id INT REFERENCES users (user_id),
    categ_id INT REFERENCES category (categ_id),
    state_id INT REFERENCES state (state_id)
);

INSERT INTO items (item_number, user_id, categ_id, state_id) VALUES
(354, (SELECT user_id FROM users WHERE user_name = 'Anna'),
(SELECT categ_id FROM category WHERE categ_name = 'salary'),
(SELECT state_id FROM state WHERE status_name = 'take to work')
);
INSERT INTO items (item_number, user_id, categ_id, state_id) VALUES
(467, (SELECT user_id FROM users WHERE user_name = 'Vova'),
(SELECT categ_id FROM category WHERE categ_name = 'salary'),
(SELECT state_id FROM state WHERE status_name = 'open')
);
INSERT INTO items (item_number, user_id, categ_id, state_id) VALUES
(200, (SELECT user_id FROM users WHERE user_name = 'Anna'),
(SELECT categ_id FROM category WHERE categ_name = 'account_creation'),
(SELECT state_id FROM state WHERE status_name = 'reject'));

CREATE TABLE comments (
    comm_id SERIAL PRIMARY KEY,
    description TEXT,
    item_id INT REFERENCES items (item_id)
);

INSERT INTO comments (description, item_id) VALUES
('income statement is ready',
(SELECT item_id FROM items WHERE categ_id = 4 AND user_id = 2)
);
INSERT INTO comments (description, item_id) VALUES
('send data', (SELECT item_id FROM items WHERE categ_id = 2)
);

CREATE TABLE attach (
    at_id SERIAL PRIMARY KEY,
    at_link VARCHAR(300) NOT NULL,
    item_id INT REFERENCES items (item_id)
);

INSERT INTO attach (at_link, item_id) VALUES
('http://clcl.ru/NZT',
(SELECT item_id FROM items WHERE item_number = 200)
);