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


-- SELECT idBloc from Bloc WHERE idOriginalSource = 'BLC000022' and idBloc NOT IN ( SELECT idBloc from v_blocmere);


CREATE OR REPLACE VIEW Transformation_Lib AS
SELECT 
    t.idTransformation,
    t.dateTransformation,
    t.idBloc,
    t.marge,
    b.longueur AS longueur,
    b.largeur AS largeur,
    b.epaisseur AS epaisseur,
    b.volume    AS volume,
    b.prixFabrication,
    b.dateFabrication,
    b.desce AS desce,
    b.idOriginalSource,
    b.idParentSource
FROM 
    Transformation t
JOIN 
    Bloc b ON t.idBloc = b.idBloc;

    
CREATE or replace view TransformationFille_Lib as
SELEct
    tf.idTransformationFille,
    tf.idDimensionUsuels,
    tf.quantite,
    tf.prixDeRevient,
    tf.prixVente,
    tf.idTransformation,
    t.dateTransformation,
    t.idBloc,
    t.marge,
    t.desce AS desce,
    t.idOriginalSource,
    t.idParentSource
from TransformationFille tf
join Transformation_Lib t on t.idTransformation = tf.idTransformation;

CREATE OR REPLACE VIEW EtatStockDimension AS
SELECT 
    mvts.idDimensionUsuels,
    mvts.idOriginalSource,
    mvts.entree,
    mvts.sortie,
    (mvts.entree - mvts.sortie) AS quantite,
    CASE 
        WHEN (mvts.entree - mvts.sortie) != 0 THEN mvts.prixDeRevient / (mvts.entree - mvts.sortie)
        ELSE NULL
    END AS moyenne_prixderevient
FROM (
    SELECT
        mvt.idDimensionUsuels, 
        mvt.idOriginalSource,
        SUM(NVL(mvt.entree, 0)) AS entree,
        SUM(NVL(mvt.sortie, 0)) AS sortie,
        SUM(NVL(mvt.prixDeRevient, 0)) AS prixDeRevient
    FROM 
        MvtStockDimension mvt
    GROUP BY 
        mvt.idDimensionUsuels, 
        mvt.idOriginalSource
) mvts;