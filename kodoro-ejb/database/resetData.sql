BEGIN
   -- Désactivation des contraintes de clé étrangère
   EXECUTE IMMEDIATE 'ALTER TABLE Transformation DISABLE CONSTRAINT TRANSFORMATION_IDBLOC_FK';
   EXECUTE IMMEDIATE 'ALTER TABLE TransformationFille DISABLE CONSTRAINT TRANSFORMATIONFILLE_IDDIMENSIONUSUELS_FK';
   EXECUTE IMMEDIATE 'ALTER TABLE TransformationFille DISABLE CONSTRAINT TRANSFORMATIONFILLE_IDTRANSFORMATION_FK';

   -- Suppression des données
    DELETE FROM TransformationFille;
    DELETE FROM MvtStockDimension;
    DELETE FROM Transformation;
    DELETE FROM BLoc where idparentsource is not null;
   --  DELETE FROM DimensionUsuels;
   EXECUTE IMMEDIATE 'DELETE FROM Bloc';

   -- Réactivation des contraintes de clé étrangère
   EXECUTE IMMEDIATE 'ALTER TABLE Transformation ENABLE CONSTRAINT TRANSFORMATION_IDBLOC_FK';
   EXECUTE IMMEDIATE 'ALTER TABLE TransformationFille ENABLE CONSTRAINT TRANSFORMATIONFILLE_IDDIMENSIONUSUELS_FK';
   EXECUTE IMMEDIATE 'ALTER TABLE TransformationFille ENABLE CONSTRAINT TRANSFORMATIONFILLE_IDTRANSFORMATION_FK';

   COMMIT;
END;
/
