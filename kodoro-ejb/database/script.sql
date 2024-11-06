create or REPLACE view v_blocorigine as
SELECT *
from Bloc
where idParentSource is null;

CREATE or REPLACE view v_blocmere as
(
    SELECT idBloc from v_blocorigine 
)
UNION
(
    SELECT DISTINCT idParentSource
    from Bloc
    where idParentSource is NOT NULL
);


SELECT idBloc from Bloc WHERE idOriginalSource = 'BLC000022' and idBloc NOT IN ( SELECT idBloc from v_blocmere);


CREATE OR REPLACE VIEW Transformation_Lib AS
SELECT 
    t.idTransformation,
    t.dateTransformation,
    t.idBloc,
    b.longueur AS longueur,
    b.largeur AS largeur,
    b.epaisseur AS epaisseur,
    b.prixFabrication,
    b.dateFabrication,
    b.desce AS desce,
    b.idOriginalSource,
    b.idParentSource
FROM 
    Transformation t
JOIN 
    Bloc b ON t.idBloc = b.idBloc;

    
