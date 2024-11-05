CREATE TABLE Bloc (
   IdBloc VARCHAR2(255),
   longueur NUMBER(15,2) NOT NULL,
   largeur NUMBER(15,2) NOT NULL,
   epaisseur NUMBER(15,2) NOT NULL,
   prixFabrication NUMBER(15,2) NOT NULL,
   daty DATE NOT NULL,
   desce VARCHAR2(255),
   IdOriginalSource VARCHAR2(255),
   IdParentSource VARCHAR2(255), 
   PRIMARY KEY(IdBloc),
   FOREIGN KEY(IdSource) REFERENCES Bloc(IdBloc)
);

CREATE TABLE Transformation (
   IdTransformation VARCHAR2(255),
   daty DATE NOT NULL,
   IdBloc VARCHAR2(255) NOT NULL,
   PRIMARY KEY(IdTransformation),
   FOREIGN KEY(IdBloc) REFERENCES Bloc(IdBloc)
);

CREATE TABLE DimensionUsuels (
   IdDimensionUsuels VARCHAR2(255),
   longueur NUMBER(15,2) NOT NULL,
   largeur NUMBER(15,2) NOT NULL,
   epaisseur NUMBER(15,2) NOT NULL,
   prixVente NUMBER(15,2) NOT NULL,
   PRIMARY KEY(IdDimensionUsuels)
);

CREATE TABLE TransformationFille (
   IdTransformationFille VARCHAR2(255),
   quantite NUMBER(10) NOT NULL,
   prixDeRevient NUMBER(15,2) NOT NULL,
   IdDimensionUsuels VARCHAR2(255) NOT NULL,
   IdTransformation VARCHAR2(255) NOT NULL,
   PRIMARY KEY(IdTransformationFille)
);
