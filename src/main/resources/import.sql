INSERT INTO prueba2.autores (id,apellido,fecha_nacimiento,nacionalidad,nombre,seudonimo) VALUES ('JUA1999','Perez','22-02-1999','chileno','Juan','Perez');
INSERT INTO prueba2.autores (id,apellido,fecha_nacimiento,nacionalidad,nombre,seudonimo) VALUES ('MAT1998','San martin','28-05-1998','chileno','Matias','Egami');
INSERT INTO prueba2.autores (id,apellido,fecha_nacimiento,nacionalidad,nombre,seudonimo) VALUES ('NIC1999','Cortes','22-02-1999','chileno','Nicolas','Brops');

INSERT INTO prueba2.categorias (id,descripcion) VALUES (1,'DRAMA');
INSERT INTO prueba2.categorias (id,descripcion) VALUES (2,'ROMANCE');
INSERT INTO prueba2.categorias (id,descripcion) VALUES (3,'SUSPENSO');
INSERT INTO prueba2.categorias (id,descripcion) VALUES (4,'AVENTURA');
INSERT INTO prueba2.categorias (id,descripcion) VALUES (5,'COMEDIA');

INSERT INTO prueba2.libros (id,anio,cant_paginas,descripcion,isbn,precio,titulo,autor_id,categoria_id) VALUES (1, 2020, 300, 'libro de drama', '123',10000,'La rosa de guadalupe', 'JUA1999', 1);
INSERT INTO prueba2.libros (id,anio,cant_paginas,descripcion,isbn,precio,titulo,autor_id,categoria_id) VALUES (2, 2021, 500, 'Historias de un gato', '123',30000, 'Neko no Mamushi', 'MAT1998', 4);

INSERT INTO prueba2.authorities (id,authority) VALUES(1, 'ROLE_ADMIN');
INSERT INTO prueba2.authorities (id,authority) VALUES(2, 'ROLE_USER');

INSERT INTO prueba2.users VALUES(1, 1, '$2a$10$WFuaTEudUoCugT2Slt0UsO3PWr2bwWX4XSPMi20GrbGFcgp687jUW', 'Usuario1',1);
INSERT INTO prueba2.users VALUES(2, 1, '$2a$10$WFuaTEudUoCugT2Slt0UsO3PWr2bwWX4XSPMi20GrbGFcgp687jUW', 'Usuario2',2);
