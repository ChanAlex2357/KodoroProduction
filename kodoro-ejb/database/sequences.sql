-- Sequence creation
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
