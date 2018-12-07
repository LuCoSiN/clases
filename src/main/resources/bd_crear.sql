/* Populate tables */
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(1, 'Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(2, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(3, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(4, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(5, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(6, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(7, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(8, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(9, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(10, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(11, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(12, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(13, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(14, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(15, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(16, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(17, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(18, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(19, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(20, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(21, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(22, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(23, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(24, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(25, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(26, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(27, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(28, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(29, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(30, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO alumnos (id, nombre, apellido, email, create_at, foto) VALUES(31, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');

CREATE TABLE USERS
(
  ID           INT not null AUTO_INCREMENT,
  USERNAME     VARCHAR(45) not null,
  PASSWORD     VARCHAR(128) not null,
  ENABLED      TINYINT(1) not null DEFAULT 1,
  PRIMARY KEY (ID),
  UNIQUE INDEX username_unique (username asc));
  
 CREATE TABLE AUTHORITIES
 (
  ID            INT not null AUTO_INCREMENT,
  USER_ID     	INT not null,
  AUTHORITY		VARCHAR(45) NOT NULL,
  PRIMARY KEY (ID),
  UNIQUE INDEX userid_authority_unique (USER_ID ASC, AUTHORITY ASC),
  CONSTRAINT FK_AUTHORITIES_USERS
  FOREIGN KEY (USER_ID)
  REFERENCES USERS(ID)
  ON DELETE CASCADE
  ON UPDATE CASCADE);
 
 INSERT INTO USERS (USERNAME,PASSWORD,ENABLED) VALUES ('admin', '$2a$10$W6dhH41jlxlgr0NvfyQ9aeAZT/1kCZ3qBHsD2UPHXKJv1gIlshIdq',1);
 INSERT INTO USERS (USERNAME,PASSWORD,ENABLED) VALUES ('alvaro', '$2a$10$YK1POFslXMcCMLaAYHKG3OkKxN82zRQ.ZIv/gRbjAcADf9xOTC.yG',1);
 INSERT INTO AUTHORITIES (USER_ID,AUTHORITY) VALUES(1,'ROLE_USER');
 INSERT INTO AUTHORITIES (USER_ID,AUTHORITY) VALUES(1,'ROLE_ADMIN');
 INSERT INTO AUTHORITIES (USER_ID,AUTHORITY) VALUES(2,'ROLE_USER');


