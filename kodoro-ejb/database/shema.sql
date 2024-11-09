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
   idBloc VARCHAR2(255)  NOT NULL,
   PRIMARY KEY(idTransformation),
   FOREIGN KEY(idBloc) REFERENCES Bloc(idBloc)
);

DROP TABLE TransformationFille CASCADE CONSTRAINTS;
CREATE TABLE TransformationFille(
   idTransformationFille VARCHAR2(255) ,
   quantite VARCHAR2(255)  NOT NULL,
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
   idOriginalSource VARCHAR2(255)  NOT NULL,
   idDimensionUsuels VARCHAR2(255)  NOT NULL,
   PRIMARY KEY(idMvtStockDimension),
   FOREIGN KEY(idOriginalSource) REFERENCES Bloc(idBloc),
   FOREIGN KEY(idDimensionUsuels) REFERENCES DimensionUsuels(idDimensionUsuels)
);

CREATE TABLE EtatStockDimension(
   idEtatStockDimension VARCHAR2(255) ,
   idOriginalSource VARCHAR2(255)  NOT NULL,
   idDimensionUsuels VARCHAR2(255)  NOT NULL,
   quantite NUMBER(15,2),
   valeurtotal NUMBER(15,2),
   valeurUnitaire NUMBER(15,2),
   PRIMARY KEY(idEtatStockDimension),
   FOREIGN KEY(idOriginalSource) REFERENCES Bloc(idBloc),
   FOREIGN KEY(idDimensionUsuels) REFERENCES DimensionUsuels(idDimensionUsuels)
);
