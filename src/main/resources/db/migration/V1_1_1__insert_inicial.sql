INSERT INTO usuarios
(foto, nome, senha, usuario)
VALUES(NULL, 'Gustavo', '$2a$10$BophUi6FRohFY6voaKkcIuW1e8YPlrLbOFB3xUerDwQN4W4JMYBQy', 'galasdalas@gmail.com');

INSERT INTO clientes
(usuario_id,contato, nome)
VALUES((SELECT MAX(ID) FROM USUARIOS),'11995671164', 'Geraldo');

INSERT INTO clientes
(contato, nome, usuario_id)
VALUES('11995671164', 'Geraldo', (SELECT MAX(ID) FROM USUARIOS));

INSERT INTO clientes
(contato, nome, usuario_id)
VALUES('11995671165', 'Grimaldo', (SELECT MAX(ID) FROM USUARIOS));

INSERT INTO clientes
(contato, nome, usuario_id)
VALUES('11995671166', 'Gabriel', (SELECT MAX(ID) FROM USUARIOS));

INSERT INTO clientes
(contato, nome, usuario_id)
VALUES('11995671167', 'Grealdo', (SELECT MAX(ID) FROM USUARIOS));
