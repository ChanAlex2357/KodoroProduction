-- Sequence creation
-- ! DROP SEQUENCE bloc_seq;
CREATE SEQUENCE bloc_seq
   START WITH 1
   INCREMENT BY 1
   NOCACHE
   NOCYCLE;

-- Function to retrieve the next value
CREATE OR REPLACE FUNCTION GET_BLOC_SEQ
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT bloc_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/


-- ! DROP SEQUENCE dim_seq;
CREATE SEQUENCE dim_seq START WITH 1 INCREMENT by 1 NOCACHE NOCYCLE;
CREATE OR REPLACE FUNCTION GET_DIMUS_SEQ
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT dim_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/


-- ! DROP SEQUENCE trans_seq;
CREATE SEQUENCE trans_seq START WITH 1 INCREMENT by 1;
CREATE OR REPLACE Function GET_TRANS_SEQ
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT trans_seq.NEXTVAL into retour FROM DUAL;
   RETURN retour; 
END;
/


-- ! DROP SEQUENCE transf_seq;
CREATE SEQUENCE transf_seq START WITH 1 INCREMENT by 1;
CREATE OR REPLACE Function GET_TRANSF_SEQ
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT transf_seq.NEXTVAL into retour FROM DUAL;
   RETURN retour; 
END;
/

-- ! DROP SEQUENCE msd_seq;
CREATE SEQUENCE msd_seq START WITH 1 INCREMENT by 1;
CREATE OR REPLACE Function GET_MSD_SEQ
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT msd_seq.NEXTVAL into retour FROM DUAL;
   RETURN retour; 
END;
/

-- * SEQUENCE TABLES

-- Séquence pour TypeMvt
-- ! DROP SEQUENCE type_mvt_seq;
CREATE SEQUENCE type_mvt_seq START WITH 1 INCREMENT BY 1;

-- Fonction pour récupérer le prochain numéro de séquence pour TypeMvt
CREATE OR REPLACE FUNCTION GET_TYPE_MVT_SEQ
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT type_mvt_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
-- Séquence pour FormuleProduction
-- ! DROP SEQUENCE formule_prod_seq;
CREATE SEQUENCE formule_prod_seq START WITH 1 INCREMENT BY 1;

-- Fonction pour récupérer le prochain numéro de séquence pour FormuleProduction
CREATE OR REPLACE FUNCTION GET_FORMULE_PROD_SEQ
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT formule_prod_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
-- Séquence pour Unite
-- ! DROP SEQUENCE unite_seq;
CREATE SEQUENCE unite_seq START WITH 1 INCREMENT BY 1;

-- Fonction pour récupérer le prochain numéro de séquence pour Unite
CREATE OR REPLACE FUNCTION GET_UNITE_SEQ
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT unite_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
-- Séquence pour Machine
-- ! DROP SEQUENCE machine_seq;
CREATE SEQUENCE machine_seq START WITH 1 INCREMENT BY 1;

-- Fonction pour récupérer le prochain numéro de séquence pour Machine
CREATE OR REPLACE FUNCTION GET_MACHINE_SEQ
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT machine_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
-- Séquence pour Ressource
-- ! DROP SEQUENCE ressource_seq;
CREATE SEQUENCE ressource_seq START WITH 1 INCREMENT BY 1;

-- Fonction pour récupérer le prochain numéro de séquence pour Ressource
CREATE OR REPLACE FUNCTION GET_RESSOURCE_SEQ
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT ressource_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
-- Séquence pour FormuleProductionFille
-- ! DROP SEQUENCE formule_prod_fille_seq;
CREATE SEQUENCE formule_prod_fille_seq START WITH 1 INCREMENT BY 1;

-- Fonction pour récupérer le prochain numéro de séquence pour FormuleProductionFille
CREATE OR REPLACE FUNCTION GET_FORMULE_PROD_FILLE_SEQ
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT formule_prod_fille_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
-- Séquence pour Production
-- ! DROP SEQUENCE production_seq;
CREATE SEQUENCE production_seq START WITH 1 INCREMENT BY 1;

-- Fonction pour récupérer le prochain numéro de séquence pour Production
CREATE OR REPLACE FUNCTION GET_PRODUCTION_SEQ
   RETURN NUMBER
IS
   retour NUMBER;
BEGIN
   SELECT production_seq.NEXTVAL INTO retour FROM DUAL;
   RETURN retour;
END;
/
