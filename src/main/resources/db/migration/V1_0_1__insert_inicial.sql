INSERT INTO users
(nome, password, login, role)
VALUES( 'Gustavo', '$2a$10$FIAVke/0eAlODOenddCUiugqZMM0HqU62S3LWJy5SX4ffg234Rdme', 'galasdalas@gmail.com', 'ADMIN');

INSERT INTO clientes
(user_id,contato, nome)
VALUES((SELECT MAX(ID) FROM users),'11995671164', 'Geraldo');

INSERT INTO clientes
(contato, nome, user_id)
VALUES('11995671164', 'Geraldo', (SELECT MAX(ID) FROM users));

INSERT INTO clientes
(contato, nome, user_id)
VALUES('11995671165', 'Grimaldo', (SELECT MAX(ID) FROM users));

INSERT INTO clientes
(contato, nome, user_id)
VALUES('11995671166', 'Gabriel', (SELECT MAX(ID) FROM users));

INSERT INTO clientes
(contato, nome, user_id)
VALUES('11995671167', 'Grealdo', (SELECT MAX(ID) FROM users));
