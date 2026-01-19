CREATE TABLE userRole(
   id SERIAL,
   label VARCHAR(50) ,
   PRIMARY KEY(id)
);

CREATE TABLE routeEntreprise(
   id SERIAL,
   label VARCHAR(50) ,
   PRIMARY KEY(id)
);

CREATE TABLE routeStatus(
   id SERIAL,
   label VARCHAR(50) ,
   valeur INTEGER,
   PRIMARY KEY(id)
);

CREATE TABLE user_(
   id SERIAL,
   email VARCHAR(100) ,
   password VARCHAR(100) ,
   nbrTentative INTEGER,
   id_userRole INTEGER,
   PRIMARY KEY(id),
   FOREIGN KEY(id_userRole) REFERENCES userRole(id)
);

CREATE TABLE userTentativeHistorique(
   id SERIAL,
   dateHistorique TIMESTAMP,
   id_user INTEGER,
   PRIMARY KEY(id),
   FOREIGN KEY(id_user) REFERENCES user_(id)
);

CREATE TABLE routeProbleme(
   id SERIAL ,
   surface NUMERIC(15,2)  ,
   budget NUMERIC(15,2)  ,
   id_routeEntreprise INTEGER,
   id_routeStatus INTEGER,
   PRIMARY KEY(id),
   FOREIGN KEY(id_routeEntreprise) REFERENCES routeEntreprise(id),
   FOREIGN KEY(id_routeStatus) REFERENCES routeStatus(id)
);

CREATE TABLE historiqueStatusRoute(
   id SERIAL,
   dateHistorique TIMESTAMP,
   id_routeProbleme INTEGER,
   id_routeStatus INTEGER,
   PRIMARY KEY(id),
   FOREIGN KEY(id_routeProbleme) REFERENCES routeProbleme(id),
   FOREIGN KEY(id_routeStatus) REFERENCES routeStatus(id)
);

INSERT INTO userRole(label) VALUES ('manager');
INSERT INTO userRole(label) VALUES ('utilisateur');
INSERT INTO userRole(label) VALUES ('admin');

INSERT INTO routeStatus(label, valeur) VALUES ('signale', 1);
INSERT INTO routeStatus(label, valeur) VALUES ('nouveau', 2);
INSERT INTO routeStatus(label, valeur) VALUES ('en cours', 3);
INSERT INTO routeStatus(label, valeur) VALUES ('termine', 4);

INSERT INTO routeEntreprise(label) VALUES ('Entreprise A');
INSERT INTO routeEntreprise(label) VALUES ('Entreprise B');
INSERT INTO routeEntreprise(label) VALUES ('Entreprise C');


INSERT INTO user_(email, password, nbrTentative, id_userRole) VALUES
('manager@gmail.com', 'manager', 0, 1);

INSERT INTO user_(email, password, nbrTentative, id_userRole) VALUES
('jean@gmail.com', 'jean123', 0, 2);

INSERT INTO user_(email, password, nbrTentative, id_userRole) VALUES
('kaiamba@gmail.com', 'kaiamba123', 0, 2);


UPDATE user_
SET password = 'manager4'
WHERE id = 4;
