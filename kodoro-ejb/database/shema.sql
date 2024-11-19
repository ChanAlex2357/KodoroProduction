-- Suppression des tables si elles existent

   DROP TABLE MvtStockDimension CASCADE CONSTRAINTS;
   DROP TABLE TransformationFille CASCADE CONSTRAINTS;
   DROP TABLE Transformation CASCADE CONSTRAINTS;
   DROP TABLE DimensionUsuels CASCADE CONSTRAINTS;
   DROP TABLE Bloc CASCADE CONSTRAINTS;

CREATE TABLE Bloc(
   idBloc VARCHAR2(255) ,
   longueur NUMBER(15,2)   NOT NULL,
   epaisseur NUMBER(15,2)   NOT NULL,
   prixFabrication NUMBER(15,2)   NOT NULL,
   dateFabrication DATE NOT NULL,
   desce VARCHAR2(255) ,
   largeur NUMBER(15,2)   NOT NULL,
   volume NUMBER(15,8)   NOT NULL,
   idOriginalSource VARCHAR2(255) ,
   idParentSource VARCHAR2(255) ,
   PRIMARY KEY(idBloc),
   FOREIGN KEY(idOriginalSource) REFERENCES Bloc(idBloc),
   FOREIGN KEY(idParentSource) REFERENCES Bloc(idBloc)
);

CREATE TABLE DimensionUsuels(
   idDimensionUsuels VARCHAR2(255) ,
   desce VARCHAR2(255) ,
   longueur NUMBER(15,2)   NOT NULL,
   largeur NUMBER(15,2)   NOT NULL,
   epaisseur NUMBER(15,2)   NOT NULL,
   volume NUMBER(15,8)   NOT NULL,
   prixVente NUMBER(15,2)   NOT NULL,
   PRIMARY KEY(idDimensionUsuels)
);

   DROP TABLE Transformation CASCADE CONSTRAINTS;
   
CREATE TABLE Transformation(
   idTransformation VARCHAR2(255) ,
   dateTransformation DATE NOT NULL,
   marge NUMBER(15,2)  NOT NULL,
   idBloc VARCHAR2(255)  NOT NULL,
   PRIMARY KEY(idTransformation),
   FOREIGN KEY(idBloc) REFERENCES Bloc(idBloc)
);

DROP TABLE TransformationFille CASCADE CONSTRAINTS;
CREATE TABLE TransformationFille(
   idTransformationFille VARCHAR2(255) ,
   quantite NUMBER  NOT NULL,
   prixDeRevient NUMBER(15,2)   NOT NULL,
   prixDeRevientUnitaire NUMBER(15,2)   NOT NULL,
   idDimensionUsuels VARCHAR2(255)  NOT NULL,
   idTransformation VARCHAR2(255)  NOT NULL,
   PRIMARY KEY(idTransformationFille),
   FOREIGN KEY(idDimensionUsuels) REFERENCES DimensionUsuels(idDimensionUsuels),
   FOREIGN KEY(idTransformation) REFERENCES Transformation(idTransformation)
);

DROP TABLE MvtStockDimension CASCADE CONSTRAINTS;
CREATE TABLE MvtStockDimension(
   idMvtStockDimension VARCHAR2(255) ,
   sortie   NUMBER(15,2),
   entree  NUMBER(15,2) ,
   prixDeVente NUMBER(15,2),
   prixDeVenteUnitaire NUMBER(15,2),
   prixDeRevient NUMBER(15,2),
   prixDeRevientUnitaire NUMBER(15,2),
   daty DATE NOT NULL,
   idOriginalSource VARCHAR2(255)  NOT NULL,
   idDimensionUsuels VARCHAR2(255)  NOT NULL,
   PRIMARY KEY(idMvtStockDimension),
   FOREIGN KEY(idOriginalSource) REFERENCES Bloc(idBloc),
   FOREIGN KEY(idDimensionUsuels) REFERENCES DimensionUsuels(idDimensionUsuels)
);

CREATE TABLE TypeMvt(
   IdTypeMvt VARCHAR2(255) ,
   val VARCHAR2(255) ,
   desce VARCHAR2(255)  NOT NULL,
   PRIMARY KEY(IdTypeMvt)
);

CREATE TABLE FormuleProduction(
   IdFormuleProduction VARCHAR2(255) ,
   desce VARCHAR2(255)  NOT NULL,
   prixDeRevient NUMBER(15,2)   NOT NULL,
   PRIMARY KEY(IdFormuleProduction)
);

CREATE TABLE Unite(
   idUnite VARCHAR2(255) ,
   val VARCHAR2(255)  NOT NULL,
   desce VARCHAR2(255)  NOT NULL,
   PRIMARY KEY(idUnite)
);

CREATE TABLE Machine(
   idMachine VARCHAR2(255) ,
   desce VARCHAR2(255)  NOT NULL,
   PRIMARY KEY(idMachine)
);

CREATE TABLE Ressource(
   idRessource VARCHAR2(255) ,
   desce VARCHAR2(255)  NOT NULL,
   puAchat NUMBER(15,2)   NOT NULL,
   idUnite VARCHAR2(255)  NOT NULL,
   PRIMARY KEY(idRessource),
   FOREIGN KEY(idUnite) REFERENCES Unite(idUnite)
);

CREATE TABLE FormuleProductionFille(
   idFormuleProductionFille VARCHAR2(255) ,
   quantite NUMBER(15,2)   NOT NULL,
   idRessource VARCHAR2(255)  NOT NULL,
   IdFormuleProduction VARCHAR2(255)  NOT NULL,
   PRIMARY KEY(idFormuleProductionFille),
   FOREIGN KEY(idRessource) REFERENCES Ressource(idRessource),
   FOREIGN KEY(IdFormuleProduction) REFERENCES FormuleProduction(IdFormuleProduction)
);

CREATE TABLE Production(
   IdProduction VARCHAR2(255) ,
   dateProduction DATE NOT NULL,
   prTheorique NUMBER(15,2)   NOT NULL,
   prPratique NUMBER(15,2)   NOT NULL,
   IdFormuleProduction VARCHAR2(255)  NOT NULL,
   idBloc VARCHAR2(255)  NOT NULL,
   idMachine VARCHAR2(255)  NOT NULL,
   PRIMARY KEY(IdProduction),
   FOREIGN KEY(IdFormuleProduction) REFERENCES FormuleProduction(IdFormuleProduction),
   FOREIGN KEY(idBloc) REFERENCES Bloc(idBloc),
   FOREIGN KEY(idMachine) REFERENCES Machine(idMachine)
);
