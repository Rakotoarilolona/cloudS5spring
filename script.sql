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
