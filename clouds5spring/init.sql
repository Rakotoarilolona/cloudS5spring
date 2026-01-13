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

CREATE TABLE user(
   id SERIAL,
   email VARCHAR(100) ,
   nom VARCHAR(100) ,
   prenom VARCHAR(100) ,
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
   FOREIGN KEY(id_user) REFERENCES user(id)
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

INSERT INTO userRole(label) VALUES ('manager');
INSERT INTO userRole(label) VALUES ('utilisateur');

INSERT INTO routeStatus(label, valeur) VALUES ('signale', 1);
INSERT INTO routeStatus(label, valeur) VALUES ('nouveau', 2);
INSERT INTO routeStatus(label, valeur) VALUES ('en cours', 3);
INSERT INTO routeStatus(label, valeur) VALUES ('termine', 4);

INSERT INTO routeEntreprise(label) VALUES ('Entreprise A');
INSERT INTO routeEntreprise(label) VALUES ('Entreprise B');
INSERT INTO routeEntreprise(label) VALUES ('Entreprise C');


INSERT INTO user(email, nom, prenom, password, nbrTentative, id_userRole) VALUES
('manager@gmail.com', 'manager', 'manager', 'manager', 0, 1);

INSERT INTO user(email, nom, prenom, password, nbrTentative, id_userRole) VALUES
('jean@gmail.com', 'Rigo', 'Jean', 'jean123', 0, 2);

INSERT INTO user(email, nom, prenom, password, nbrTentative, id_userRole) VALUES
('kaiamba@gmail.com', 'Kaiamba', 'Kaiamba', 'kaiamba123', 0, 2);
