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
    tf.prixDeRevientUnitaire,
    tf.idTransformation,
    t.dateTransformation,
    t.idBloc,
    t.marge,
    t.desce AS desceBloc,
    t.idOriginalSource,
    t.idParentSource,
    dim.prixVente,
    dim.desce as desceDim
from TransformationFille tf
join Transformation_Lib t on t.idTransformation = tf.idTransformation
join DimensionUsuels dim on tf.idDimensionUsuels = dim.idDimensionUsuels;

CREATE or replace view EtatStockDimension  as 
SELECT
    mvts.idDimensionUsuels,
    mvts.idOriginalSource,
    mvts.entree,
    mvts.sortie,
    (mvts.entree - mvts.sortie) AS quantite,
    mvts.prixDeRevient,
    mvts.prixDeVente,
    CASE 
        WHEN (mvts.entree - mvts.sortie) != 0 THEN mvts.prixDeRevient / (mvts.entree - mvts.sortie)
        ELSE NULL
    END AS prixDeRevientMoyenne
FROM (
    SELECT
        mvt.idDimensionUsuels, 
        mvt.idOriginalSource,
        SUM(NVL(mvt.entree, 0)) AS entree,
        SUM(NVL(mvt.sortie, 0)) AS sortie,
        SUM(NVL(mvt.prixDeRevient, 0)) AS prixDeRevient,
        SUM(NVL(mvt.prixDeVente, 0)) AS prixDeVente
    FROM 
        MvtStockDimension mvt
    GROUP BY 
        mvt.idDimensionUsuels, 
        mvt.idOriginalSource
) mvts;

CREATE or replace view EtatStockDIM  as 
SELECT
    mvts.idDimensionUsuels,
    mvts.entree,
    mvts.sortie,
    (mvts.entree - mvts.sortie) AS quantite,
    mvts.prixDeRevient,
    mvts.prixDeVente,
    CASE 
        WHEN (mvts.entree - mvts.sortie) != 0 THEN mvts.prixDeRevient / (mvts.entree - mvts.sortie)
        ELSE NULL
    END AS prixDeRevientMoyenne
FROM (
    SELECT
        mvt.idDimensionUsuels,
        SUM(NVL(mvt.entree, 0)) AS entree,
        SUM(NVL(mvt.sortie, 0)) AS sortie,
        SUM(NVL(mvt.prixDeRevient, 0)) AS prixDeRevient,
        SUM(NVL(mvt.prixDeVente, 0)) AS prixDeVente
    FROM 
        MvtStockDimension mvt
    GROUP BY 
        mvt.idDimensionUsuels
) mvts;