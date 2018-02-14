/*------------------------------------------------------------
*        Script SQLSERVER 
------------------------------------------------------------*/


/*------------------------------------------------------------
-- Creation DATABASE
------------------------------------------------------------*/
CREATE DATABASE BOARDBOOK_DB

/*------------------------------------------------------------
-- Table: FICHE
------------------------------------------------------------*/
CREATE TABLE FICHE(
	idFiche          INT IDENTITY (1,1) NOT NULL ,
	dateDepart       DATE NOT NULL ,
	carburantNbLitre REAL  NOT NULL ,
	carburantMontant REAL  NOT NULL ,
	nbKmEntree       INT  NOT NULL ,
	nbKmSortie       INT NULL ,
	commentaire      TEXT NULL  ,
	dateCloture      DATE NULL ,
	cloture          bit  NOT NULL ,
	idDeplacement    INT  NOT NULL ,
	idLieuDepart     INT  NOT NULL ,
	idVehicule       INT  NOT NULL ,
	idLieuArrivee    INT  NOT NULL ,
	CONSTRAINT prk_constraint_FICHE PRIMARY KEY NONCLUSTERED (idFiche)
);


/*------------------------------------------------------------
-- Table: UTILISATEUR
------------------------------------------------------------*/
CREATE TABLE UTILISATEUR(
	idUtilisateur  INT IDENTITY (1,1) NOT NULL ,
	identifiant    VARCHAR (25) NOT NULL ,
	mdp            VARCHAR (25) NOT NULL ,
	nom            VARCHAR (25) NOT NULL ,
	prenom         VARCHAR (25) ,
	administrateur bit  NOT NULL ,
	conducteur     bit  NOT NULL ,
	CONSTRAINT prk_constraint_UTILISATEUR PRIMARY KEY NONCLUSTERED (idUtilisateur)
);


/*------------------------------------------------------------
-- Table: DEPLACEMENT
------------------------------------------------------------*/
CREATE TABLE DEPLACEMENT(
	idDeplacement INT IDENTITY (1,1) NOT NULL ,
	nature        VARCHAR (30) NOT NULL ,
	actif bit NOT NULL,
	CONSTRAINT prk_constraint_DEPLACEMENT PRIMARY KEY NONCLUSTERED (idDeplacement)
);


/*------------------------------------------------------------
-- Table: LIEU
------------------------------------------------------------*/
CREATE TABLE LIEU(
	idLieu INT IDENTITY (1,1) NOT NULL ,
	nom    VARCHAR (100) NOT NULL ,
	actif bit NOT NULL,
	CONSTRAINT prk_constraint_LIEU PRIMARY KEY NONCLUSTERED (idLieu)
);


/*------------------------------------------------------------
-- Table: VEHICULE
------------------------------------------------------------*/
CREATE TABLE VEHICULE(
	idVehicule      INT IDENTITY (1,1) NOT NULL ,
	immatriculation VARCHAR (10) NOT NULL ,
	marque          VARCHAR (25)  ,
	disponible      bit NOT NULL,
	CONSTRAINT prk_constraint_VEHICULE PRIMARY KEY NONCLUSTERED (idVehicule)
);


/*------------------------------------------------------------
-- Table: RENSEIGNER
------------------------------------------------------------*/
CREATE TABLE RENSEIGNER(
	idFiche       INT  NOT NULL ,
	idUtilisateur INT  NOT NULL ,
	CONSTRAINT prk_constraint_RENSEIGNER PRIMARY KEY NONCLUSTERED (idFiche,idUtilisateur)
);


ALTER TABLE FICHE ADD CONSTRAINT FK_FICHE_idDeplacement FOREIGN KEY (idDeplacement) REFERENCES DEPLACEMENT(idDeplacement);

ALTER TABLE FICHE ADD CONSTRAINT FK_FICHE_idLieu FOREIGN KEY (idLieuDepart) REFERENCES LIEU(idLieu);
ALTER TABLE FICHE ADD CONSTRAINT FK_FICHE_idLieu_LIEU FOREIGN KEY (idLieuArrivee) REFERENCES LIEU(idLieu);

ALTER TABLE FICHE ADD CONSTRAINT FK_FICHE_idVehicule FOREIGN KEY (idVehicule) REFERENCES VEHICULE(idVehicule);

ALTER TABLE RENSEIGNER ADD CONSTRAINT FK_RENSEIGNER_idFiche FOREIGN KEY (idFiche) REFERENCES FICHE(idFiche);

ALTER TABLE RENSEIGNER ADD CONSTRAINT FK_RENSEIGNER_idUtilisateur FOREIGN KEY (idUtilisateur) REFERENCES UTILISATEUR(idUtilisateur);


/*------------------------------------------------------------
-- Jeux d'essai
------------------------------------------------------------*/
INSERT INTO LIEU (nom,actif)
VALUES ('Rennes (Chartres de Bretagne)',1);
INSERT INTO LIEU (nom,actif)
VALUES ('Nantes (Saint Herblain)',1);
INSERT INTO LIEU (nom,actif)
VALUES ('Nantes (Hub Creatic)',1);
INSERT INTO LIEU (nom,actif)
VALUES ('Niort',1);

INSERT INTO VEHICULE (immatriculation,marque,disponible)
VALUES ('AV-856-KL','Renault',1);
INSERT INTO VEHICULE (immatriculation,marque,disponible)
VALUES ('BN-256-PO','Citroën',1);
INSERT INTO VEHICULE (immatriculation,marque,disponible)
VALUES ('TR-123-JN','Peugeot',0);

INSERT INTO UTILISATEUR (identifiant,mdp,nom,prenom,administrateur,conducteur)
VALUES ('toto','toto','totoNom','totoPrenom',1,1);
INSERT INTO UTILISATEUR (identifiant,mdp,nom,prenom,administrateur,conducteur)
VALUES ('riri','riri','ririNom','ririPrenom',0,1);
INSERT INTO UTILISATEUR (identifiant,mdp,nom,prenom,administrateur,conducteur)
VALUES ('loulou','loulou','loulouNom','loulouPrenom',0,1);

INSERT INTO DEPLACEMENT(nature,actif)
VALUES ('Cours',1);
INSERT INTO DEPLACEMENT(nature,actif)
VALUES ('Réunion',1);
INSERT INTO DEPLACEMENT(nature,actif)
VALUES ('Course',1);
INSERT INTO DEPLACEMENT(nature,actif)
VALUES ('Déchèterie',1);
INSERT INTO DEPLACEMENT(nature,actif)
VALUES ('Garage',0);
INSERT INTO DEPLACEMENT(nature,actif)
VALUES ('Commercial',0);
INSERT INTO DEPLACEMENT(nature,actif)
VALUES ('Assistance technique',1);
INSERT INTO DEPLACEMENT(nature,actif)
VALUES ('Divers',1);
INSERT INTO DEPLACEMENT(nature,actif)
VALUES ('Régulation',1);

INSERT INTO FICHE(dateDepart,carburantNbLitre,carburantMontant,nbKmEntree,nbKmSortie,commentaire,dateCloture,cloture,idDeplacement,idLieuDepart,idVehicule,idLieuArrivee)
VALUES ('2018/1/17',20,45,100,200,'1er Voyage','2018/1/20',1,2,1,2,3);

INSERT INTO FICHE(dateDepart,carburantNbLitre,carburantMontant,nbKmEntree,nbKmSortie,commentaire,dateCloture,cloture,idDeplacement,idLieuDepart,idVehicule,idLieuArrivee)
VALUES ('2018/1/20',20,45,200,300,'2eme Voyage','2018/1/21',1,2,1,2,3);

INSERT INTO FICHE(dateDepart,carburantNbLitre,carburantMontant,nbKmEntree,nbKmSortie,commentaire,dateCloture,cloture,idDeplacement,idLieuDepart,idVehicule,idLieuArrivee)
VALUES ('2018/1/17',20,45,200,300,'1eme Voyage bis','2018/1/20',1,4,1,1,3);

INSERT INTO RENSEIGNER(idFiche,idUtilisateur)
VALUES (1,1);
INSERT INTO RENSEIGNER(idFiche,idUtilisateur)
VALUES (5,1);