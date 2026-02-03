-- Truncate avec réinitialisation des séquences auto-incrément
TRUNCATE TABLE historiquestatusroute RESTART IDENTITY;
TRUNCATE TABLE routeprobleme RESTART IDENTITY;
TRUNCATE TABLE usertentativehistorique RESTART IDENTITY;
TRUNCATE TABLE user_ RESTART IDENTITY;
TRUNCATE TABLE routestatus RESTART IDENTITY;
TRUNCATE TABLE routeentreprise RESTART IDENTITY;
TRUNCATE TABLE userrole RESTART IDENTITY;

CREATE TABLE userrole(
   id SERIAL,
   label VARCHAR(50) ,
   PRIMARY KEY(id)
);

CREATE TABLE routeentreprise(
   id SERIAL,
   label VARCHAR(50) ,
   PRIMARY KEY(id)
);

CREATE TABLE routestatus(
   id SERIAL,
   label VARCHAR(50) ,
   valeur INTEGER,
   PRIMARY KEY(id)
);

CREATE TABLE user_(
   id SERIAL,
   email VARCHAR(100) ,
   password VARCHAR(100) ,
   nbrtentative INTEGER,
   id_userrole INTEGER,
   PRIMARY KEY(id),
   FOREIGN KEY(id_userrole) REFERENCES userrole(id)
);

CREATE TABLE usertentativehistorique(
   id SERIAL,
   datehistorique TIMESTAMP,
   id_user INTEGER,
   PRIMARY KEY(id),
   FOREIGN KEY(id_user) REFERENCES user_(id)
);

CREATE TABLE routeprobleme(
   id SERIAL ,
   surface NUMERIC(15,2)  ,
   budget NUMERIC(15,2)  ,
   id_routeentreprise INTEGER,
   longitude NUMERIC(15,6) ,
   problemedescription VARCHAR(255) ,
   latitude NUMERIC(15,6) ,
   id_routestatus INTEGER,
   id_user INTEGER,
   PRIMARY KEY(id),
   FOREIGN KEY(id_routeentreprise) REFERENCES routeentreprise(id),
   FOREIGN KEY(id_routestatus) REFERENCES routestatus(id),
   FOREIGN KEY(id_user) REFERENCES user_(id)
);

CREATE TABLE historiquestatusroute(
   id SERIAL,
   datehistorique TIMESTAMP,
   id_routeprobleme INTEGER,
   id_routestatus INTEGER,
   PRIMARY KEY(id),
   FOREIGN KEY(id_routeprobleme) REFERENCES routeprobleme(id),
   FOREIGN KEY(id_routestatus) REFERENCES routestatus(id)
);

INSERT INTO userrole(label) VALUES ('manager');
INSERT INTO userrole(label) VALUES ('utilisateur');
INSERT INTO userrole(label) VALUES ('admin');

INSERT INTO routestatus(label, valeur) VALUES ('signale', 1);
INSERT INTO routestatus(label, valeur) VALUES ('nouveau', 2);
INSERT INTO routestatus(label, valeur) VALUES ('en cours', 3);
INSERT INTO routestatus(label, valeur) VALUES ('termine', 4);

INSERT INTO routeentreprise(label) VALUES ('Entreprise A');
INSERT INTO routeentreprise(label) VALUES ('Entreprise B');
INSERT INTO routeentreprise(label) VALUES ('Entreprise C');


INSERT INTO user_(email, password, nbrtentative, id_userrole) VALUES
('manager@gmail.com', 'manager', 0, 1);

INSERT INTO user_(email, password, nbrtentative, id_userrole) VALUES
('jean@gmail.com', 'jean123', 0, 2);

INSERT INTO user_(email, password, nbrtentative, id_userrole) VALUES
('kaiamba@gmail.com', 'kaiamba123', 0, 2);

INSERT INTO user_(email, password, nbrtentative, id_userrole) VALUES
('test1@gmail.com', 'test123', 0, 2);

INSERT INTO user_(email, password, nbrtentative, id_userrole) VALUES
('test2@gmail.com', 'test123', 0, 2);

UPDATE user_
SET password = 'manager4'
WHERE id = 4;
