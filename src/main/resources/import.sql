INSERT INTO clientes (id, nombre, apellido, email, create_at) VALUES ('04c8a9da-59e5-4228-a338-3c0108037551', 'Andrés', 'Guzmán', 'profesor@bolsadeideas.com', '2018-01-01');
INSERT INTO clientes (id, nombre, apellido, email, create_at) VALUES ('04c8a9da-59e5-4228-a338-3c0108037552', 'Mr. John', 'Doe', 'john.doe@gmail.com', '2018-01-02');
INSERT INTO clientes (id, nombre, apellido, email, create_at) VALUES ('04c8a9da-59e5-4228-a338-3c0108037553', 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2018-01-03');
INSERT INTO clientes (id, nombre, apellido, email, create_at) VALUES ('04c8a9da-59e5-4228-a338-3c0108037554', 'Rasmus', 'Lerdorft', 'rasmus.lerdorft@gmail.com', '2018-01-04');
INSERT INTO clientes (id, nombre, apellido, email, create_at) VALUES ('04c8a9da-59e5-4228-a338-3c0108037555', 'Erich', 'Gamma', 'erich.gamma@gmail.com', '2018-02-01');
INSERT INTO clientes (id, nombre, apellido, email, create_at) VALUES ('04c8a9da-59e5-4228-a338-3c0108037556', 'Richard', 'Helm', 'richard.helm@gmail.com', '2018-02-10');
INSERT INTO clientes (id, nombre, apellido, email, create_at) VALUES ('04c8a9da-59e5-4228-a338-3c0108037557', 'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2018-02-18');
INSERT INTO clientes (id, nombre, apellido, email, create_at) VALUES ('04c8a9da-59e5-4228-a338-3c0108037558', 'John', 'Vlissides', 'john.vlissides@gmail.com', '2018-02-28');
INSERT INTO clientes (id, nombre, apellido, email, create_at) VALUES ('04c8a9da-59e5-4228-a338-3c0108037559', 'Dr. James', 'Gosling', 'james.gosling@gmail.com', '2018-03-03');
INSERT INTO clientes (id, nombre, apellido, email, create_at) VALUES ('04c8a9da-59e5-4228-a338-3c0108037550', 'Magma', 'Lee', 'magma.lee@gmail.com', '2018-03-04');
INSERT INTO clientes (id, nombre, apellido, email, create_at) VALUES ('04c8a9da-59e5-4228-a338-3c0108037511', 'Tornado', 'Roe', 'tornado.roe@gmail.com', '2018-03-05');
INSERT INTO clientes (id, nombre, apellido, email, create_at) VALUES ('04c8a9da-59e5-4228-a338-3c0108037521', 'Jade', 'Doe', 'jade.doe@gmail.com', '2018-03-06');

INSERT INTO regiones (id, nombre) VALUES ('01c8a9da-59e5-4228-a338-3c0108037521', 'Sudamérica');
INSERT INTO regiones (id, nombre) VALUES ('02c8a9da-59e5-4228-a338-3c0108037521', 'Centroamérica');
INSERT INTO regiones (id, nombre) VALUES ('03c8a9da-59e5-4228-a338-3c0108037521', 'Norteamérica');
INSERT INTO regiones (id, nombre) VALUES ('04c8a9da-59e5-4228-a338-3c0108037521', 'Europa');
INSERT INTO regiones (id, nombre) VALUES ('05c8a9da-59e5-4228-a338-3c0108037521', 'Asia');
INSERT INTO regiones (id, nombre) VALUES ('06c8a9da-59e5-4228-a338-3c0108037521', 'Africa');
INSERT INTO regiones (id, nombre) VALUES ('07c8a9da-59e5-4228-a338-3c0108037521', 'Oceanía');
INSERT INTO regiones (id, nombre) VALUES ('08c8a9da-59e5-4228-a338-3c0108037521', 'Antártida');

INSERT INTO usuarios (id, username, password, enabled, nombre, apellido, email) VALUES ('14c8a9da-59e5-4228-a338-3c0108037521', 'andres','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq',true, 'Andres', 'Guzman','profesor@bolsadeideas.com');
INSERT INTO usuarios (id, username, password, enabled, nombre, apellido, email) VALUES ('24c8a9da-59e5-4228-a338-3c0108037521', 'admin','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK',true, 'John', 'Doe','jhon.doe@bolsadeideas.com');

INSERT INTO roles (id, nombre) VALUES ('04c1a9da-59e5-4228-a338-3c0108037521', 'ROLE_USER');
INSERT INTO roles (id, nombre) VALUES ('04c2a9da-59e5-4228-a338-3c0108037521', 'ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES ('14c8a9da-59e5-4228-a338-3c0108037521', '04c1a9da-59e5-4228-a338-3c0108037521');
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES ('24c8a9da-59e5-4228-a338-3c0108037521', '04c2a9da-59e5-4228-a338-3c0108037521');
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES ('24c8a9da-59e5-4228-a338-3c0108037521', '04c1a9da-59e5-4228-a338-3c0108037521');

INSERT INTO productos (id, nombre, precio, create_at) VALUES('04c1a9da-59e5-4228-a338-1c0108037521', 'Panasonic Pantalla LCD', 259990, current_date);
INSERT INTO productos (id, nombre, precio, create_at) VALUES('04c1a9da-59e5-4228-a338-2c0108037521', 'Sony Camara digital DSC-W320B', 123490, current_date);
INSERT INTO productos (id, nombre, precio, create_at) VALUES('04c1a9da-59e5-4228-a338-3c0108037521', 'Apple iPod shuffle', 1499990, current_date);
INSERT INTO productos (id, nombre, precio, create_at) VALUES('04c1a9da-59e5-4228-a338-4c0108037521', 'Sony Notebook Z110', 37990, current_date);
INSERT INTO productos (id, nombre, precio, create_at) VALUES('04c1a9da-59e5-4228-a338-5c0108037521', 'Hewlett Packard Multifuncional F2280', 69990, current_date);
INSERT INTO productos (id, nombre, precio, create_at) VALUES('04c1a9da-59e5-4228-a338-6c0108037521', 'Bianchi Bicicleta Aro 26', 69990, current_date);
INSERT INTO productos (id, nombre, precio, create_at) VALUES('04c1a9da-59e5-4228-a338-7c0108037521', 'Mica Comoda 5 Cajones', 299990, current_date);

INSERT INTO facturas (id, descripcion, observacion, cliente_id, create_at) VALUES('04c1a1da-59e5-4228-a338-3c0108037521', 'Factura equipos de oficina', null, '04c8a9da-59e5-4228-a338-3c0108037551', current_date);

INSERT INTO facturas_items (id, cantidad, factura_id, producto_id) VALUES('04c1a1da-59e5-4228-a338-1c0108037521', 1, '04c1a1da-59e5-4228-a338-3c0108037521', '04c1a9da-59e5-4228-a338-1c0108037521');
INSERT INTO facturas_items (id, cantidad, factura_id, producto_id) VALUES('04c1a1da-59e5-4228-a338-2c0108037521', 2, '04c1a1da-59e5-4228-a338-3c0108037521', '04c1a9da-59e5-4228-a338-4c0108037521');
INSERT INTO facturas_items (id, cantidad, factura_id, producto_id) VALUES('04c1a1da-59e5-4228-a338-3c0108037521', 1, '04c1a1da-59e5-4228-a338-3c0108037521', '04c1a9da-59e5-4228-a338-5c0108037521');
INSERT INTO facturas_items (id, cantidad, factura_id, producto_id) VALUES('04c1a1da-59e5-4228-a338-4c0108037521', 1, '04c1a1da-59e5-4228-a338-3c0108037521', '04c1a9da-59e5-4228-a338-7c0108037521');

INSERT INTO facturas (id, descripcion, observacion, cliente_id, create_at) VALUES('04c1a2da-59e5-4228-a338-3c0108037521', 'Factura Bicicleta', 'Alguna nota importante!', '04c8a9da-59e5-4228-a338-3c0108037551', current_date);
INSERT INTO facturas_items (id, cantidad, factura_id, producto_id) VALUES('04c1a1da-59e5-4228-a338-5c0108037521', 3, '04c1a2da-59e5-4228-a338-3c0108037521', '04c1a9da-59e5-4228-a338-6c0108037521');