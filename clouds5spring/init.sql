-- Truncate avec réinitialisation des séquences auto-incrément
DROP TABLE IF EXISTS photo CASCADE;
DROP TABLE IF EXISTS historiquestatusroute CASCADE;
DROP TABLE IF EXISTS routeprobleme CASCADE;
DROP TABLE IF EXISTS usertentativehistorique CASCADE;
DROP TABLE IF EXISTS user_ CASCADE;
DROP TABLE IF EXISTS routestatus CASCADE;
DROP TABLE IF EXISTS routeentreprise CASCADE;
DROP TABLE IF EXISTS userrole CASCADE;


CREATE TABLE IF NOT EXISTS userrole(
   id SERIAL,
   label VARCHAR(50),
   PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS routeentreprise(
   id SERIAL,
   label VARCHAR(50),
   PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS routestatus(
   id SERIAL,
   label VARCHAR(50),
   valeur INTEGER,
   PRIMARY KEY(id)
);

-- Création de la table user_ avec les colonnes manquantes
CREATE TABLE IF NOT EXISTS user_(
   id SERIAL,
   pseudo VARCHAR(100),  -- Colonne manquante
   email VARCHAR(100),
   password VARCHAR(100),
   nbrtentative INTEGER,
   id_userrole INTEGER,
   blockedAt TIMESTAMP,  -- Colonne manquante
   firebase_uid VARCHAR(255),  -- Colonne manquante (nom correspondant à l'entité)
   PRIMARY KEY(id),
   FOREIGN KEY(id_userrole) REFERENCES userrole(id),
   UNIQUE(firebase_uid)  -- Contrainte d'unicité
);

CREATE TABLE IF NOT EXISTS usertentativehistorique(
   id SERIAL,
   datehistorique TIMESTAMP,
   id_user INTEGER,
   PRIMARY KEY(id),
   FOREIGN KEY(id_user) REFERENCES user_(id)
);

-- Ajout des colonnes manquantes à routeprobleme (si nécessaire)
CREATE TABLE IF NOT EXISTS routeprobleme(
   id SERIAL,
   surface NUMERIC(15,2),
   budget NUMERIC(15,2),
   id_routeentreprise INTEGER,
   longitude NUMERIC(15,6),
   problemedescription VARCHAR(255),
   latitude NUMERIC(15,6),
   id_routestatus INTEGER,
   id_user INTEGER,
   firebaseId VARCHAR(255),  -- Colonne mentionnée dans l'erreur
   updatedAt TIMESTAMP,  -- Colonne mentionnée dans l'erreur
   PRIMARY KEY(id),
   FOREIGN KEY(id_routeentreprise) REFERENCES routeentreprise(id),
   FOREIGN KEY(id_routestatus) REFERENCES routestatus(id),
   FOREIGN KEY(id_user) REFERENCES user_(id)
);

CREATE TABLE IF NOT EXISTS historiquestatusroute(
   id SERIAL,
   datehistorique TIMESTAMP,
   id_routeprobleme INTEGER,
   id_routestatus INTEGER,
   PRIMARY KEY(id),
   FOREIGN KEY(id_routeprobleme) REFERENCES routeprobleme(id),
   FOREIGN KEY(id_routestatus) REFERENCES routestatus(id)
);

CREATE TABLE photo(
   id SERIAL,
   bytes BYTEA,
   id_routeprobleme INTEGER,
   PRIMARY KEY(id),
   FOREIGN KEY(id_routeprobleme) REFERENCES routeprobleme(id)
);


-- Option 1: Tronquer avec CASCADE (recommandé)
TRUNCATE TABLE photo, historiquestatusroute, routeprobleme, usertentativehistorique, user_, routestatus, routeentreprise, userrole
RESTART IDENTITY CASCADE;

-- Option 2: Tronquer dans l'ordre inverse des dépendances
-- TRUNCATE TABLE historiquestatusroute RESTART IDENTITY CASCADE;
-- TRUNCATE TABLE routeprobleme RESTART IDENTITY CASCADE;
-- TRUNCATE TABLE usertentativehistorique RESTART IDENTITY CASCADE;
-- TRUNCATE TABLE user_ RESTART IDENTITY CASCADE;
-- TRUNCATE TABLE routestatus RESTART IDENTITY CASCADE;
-- TRUNCATE TABLE routeentreprise RESTART IDENTITY CASCADE;
-- TRUNCATE TABLE userrole RESTART IDENTITY CASCADE;

-- Insertion des données
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

-- Insertion des utilisateurs avec les nouvelles colonnes
INSERT INTO user_(pseudo, email, password, nbrtentative, id_userrole, blockedAt, firebase_uid) VALUES
('manager', 'manager@gmail.com', 'manager', 0, 1, NULL, NULL);

INSERT INTO user_(pseudo, email, password, nbrtentative, id_userrole, blockedAt, firebase_uid) VALUES
('jean', 'jean@gmail.com', 'jean123', 0, 2, NULL, NULL);

INSERT INTO user_(pseudo, email, password, nbrtentative, id_userrole, blockedAt, firebase_uid) VALUES
('kaiamba', 'kaiamba@gmail.com', 'kaiamba123', 0, 2, NULL, NULL);

INSERT INTO user_(pseudo, email, password, nbrtentative, id_userrole, blockedAt, firebase_uid) VALUES
('test1', 'test1@gmail.com', 'test123', 0, 2, NULL, NULL);

INSERT INTO user_(pseudo, email, password, nbrtentative, id_userrole, blockedAt, firebase_uid) VALUES
('test2', 'test2@gmail.com', 'test123', 0, 2, NULL, NULL);

-- Mise à jour du mot de passe
UPDATE user_
SET password = 'manager4'
WHERE id = 4;