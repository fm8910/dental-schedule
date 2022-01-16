-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');
INSERT INTO users(username, password, role, complete_names) VALUES('christy', '$2a$10$0.sx37/fBVMAxmasa3M5.uvNUPPXj6HSvjdyOsPONvG2WCVjq1KVW', 'doctor', 'Christy Schumm');
-- password = 1234
INSERT INTO users(username, password, role, complete_names) VALUES('nstanton', '$2a$10$3GigtQXq.9U9xSSQ8Vylee7P/QdIK40gknKjmgFeCF7zaOxi5/eZq', 'doctor', 'Natalia Stanton Jr.');
-- password = password
INSERT INTO users(username, password, role, complete_names) VALUES('nmurazik', '$2a$10$QwO.0dmFJjC11ePYUEHleunxDytcLltSqCnU0DpZKPHGB3k4musUe', 'doctor', 'Nola Murazik V');
-- password = password123
INSERT INTO users(username, password, role, complete_names) VALUES('jduran', '$2a$10$U3OlRLVcPpGAHUgJgSU4wuwE4TLli7a67JmozrZeNOyWNFyzczx6i', 'doctor', 'Juan Bautista Duran');

INSERT INTO users(username, password, role, complete_names) VALUES('lrivera', '$2a$10$0.sx37/fBVMAxmasa3M5.uvNUPPXj6HSvjdyOsPONvG2WCVjq1KVW', 'doctor', 'Lupe Rivera');
-- password = 1234
INSERT INTO users(username, password, role, complete_names) VALUES('mvega', '$2a$10$3GigtQXq.9U9xSSQ8Vylee7P/QdIK40gknKjmgFeCF7zaOxi5/eZq', 'doctor', 'Marisol Vega');
-- password = password
INSERT INTO users(username, password, role, complete_names) VALUES('arodriguez', '$2a$10$QwO.0dmFJjC11ePYUEHleunxDytcLltSqCnU0DpZKPHGB3k4musUe', 'doctor', 'Alfredo Rodriguez');
-- password = password123
INSERT INTO users(username, password, role, complete_names) VALUES('jgutierrez', '$2a$10$U3OlRLVcPpGAHUgJgSU4wuwE4TLli7a67JmozrZeNOyWNFyzczx6i', 'doctor', 'Jefferson Gutierritos');

INSERT INTO users(username, password, role, complete_names) VALUES('ayakitori', '$2a$10$QwO.0dmFJjC11ePYUEHleunxDytcLltSqCnU0DpZKPHGB3k4musUe', 'doctor', 'Alvin Yakitori');
-- password = password123
INSERT INTO users(username, password, role, complete_names) VALUES('lsmit', '$2a$10$U3OlRLVcPpGAHUgJgSU4wuwE4TLli7a67JmozrZeNOyWNFyzczx6i', 'doctor', 'Leonardo Montenegro Smith');
-- password = password123
INSERT INTO users(username, password, role, complete_names) VALUES('jlorenzo', '$2a$10$U3OlRLVcPpGAHUgJgSU4wuwE4TLli7a67JmozrZeNOyWNFyzczx6i', 'doctor', 'Juan Lorenzo');

insert into doctors ( name, user_id)
SELECT u.complete_names, u.id FROM users u WHERE role = 'doctor';

insert into patients ( name, user_id)
SELECT u.complete_names, u.id FROM users u WHERE role = 'paciente';