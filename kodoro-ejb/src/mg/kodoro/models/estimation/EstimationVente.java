package mg.kodoro.models.estimation;

import java.sql.Connection;

import mg.kodoro.models.Bloc;
import mg.kodoro.models.DimensionUsuels;
import mg.kodoro.models.transformation.Transformation;
import mg.kodoro.models.transformation.TransformationFille;

public class EstimationVente {
    private Bloc[] blocRestantes;
    private double volumeRestantes;
    private Transformation[] transformation;
    private TransformationFille[] detailsTransformation;

    private DimensionUsuels dimProfit;
    private DimensionUsuels dimMinPerte;

    public EstimationPrix vente;
    public EstimationPrix optimal;
    public EstimationPrix minimal; 
    private Bloc bloc;
    

    public EstimationVente(Bloc bloc , Connection conn) throws Exception{
        setBloc(bloc);
        setVente(new EstimationPrix());
        setOptimal(new EstimationPrix());
        setMinimal(new EstimationPrix());

        setVente(conn);
        setOptimal(conn);
        setMinimal(conn);
        
    }
    private void setOptimal(Connection conn) throws Exception {
        Bloc[] restes = this.getBlocRestantes(conn);
        DimensionUsuels dim = DimensionUsuels.getDimensinoUsuelsWithMaxRapportVolumePrix(conn);
        setDimProfit(dim);
        this.getOptimal().setChiffreAffaireTheorique(getEstimationDimensionByBlocs(restes, dim));
        this.getOptimal().setPrixDeRevientTotal(getPrixRevientTheorique(restes, dim));
    }   

    private void setMinimal(Connection conn) throws Exception {
        Bloc[] restes = this.getBlocRestantes(conn);
        DimensionUsuels dim = DimensionUsuels.getDimensionUsuelsWithMinimalVolume(conn);
        setDimMinPerte(dim);

        this.getMinimal().setChiffreAffaireTheorique(getEstimationDimensionByBlocs(restes, dim));
        this.getMinimal().setPrixDeRevientTotal(getPrixRevientTheorique(restes, dim));
    }

    private void setVente(Connection conn) throws Exception {
        this.getVente().setChiffreAffaireTheorique( this.getBloc().getEstimationVente(conn));
        this.getVente().setPrixDeRevientTotal( this.getBloc().getEstimationPrixDeRevientTotal(conn));
    }
   
    
    public double getEstimationDimensionByBlocs(Bloc[] blocs , DimensionUsuels dimensionUsuels){
        if (blocs == null || blocs.length <= 0 || dimensionUsuels == null) {
            return 0;
        }
        // Evaluer la quantite possible
        double qte = dimensionUsuels.getEstimationQte(blocs[0]);
        // Estimer le montant de vente
        return dimensionUsuels.getMontantVente(qte);
    }

    public double getPrixRevientTheorique(Bloc[] blocs , DimensionUsuels dimensionUsuels)  {
        if (blocs == null || blocs.length <= 0 || dimensionUsuels == null) {
            return 0;
        }
        // Evaluer la quantite possible
        double qte = dimensionUsuels.getEstimationQte(blocs[0]);

        double volume = qte * dimensionUsuels.getVolume();
        // Estimer le montant de vente
        return blocs[0].getPrixUnitaireVolume() * volume;
    }
    
    public void setBloc(Bloc bloc) {
        this.bloc = bloc;
    }
    public Bloc[] getBlocRestantes(){
        return this.blocRestantes;
    }
    public Bloc[] getBlocRestantes(Connection conn) throws Exception {
        if (this.blocRestantes == null || this.blocRestantes.length <= 0) {
            this.blocRestantes = this.getBloc().getRestes(conn);
            setVolumeRestantes(Bloc.getSommeVolume(this.blocRestantes));
        }
        return blocRestantes;
    }
    
    public Transformation[] getTransformation() {
        return transformation;
    }
    
    public void setTransformation(Transformation[] transformation) {
        this.transformation = transformation;
    }
    
    public TransformationFille[] getDetailsTransformation() {
        return detailsTransformation;
    }
    
    public void setDetailsTransformation(TransformationFille[] detailsTransformation) {
        this.detailsTransformation = detailsTransformation;
    }
    
    public Bloc getBloc(){
        return this.bloc;
    }
    public String getIdBloc(){
        return this.getBloc().getIdBloc();
    }
    public double getVolumeRestantes() {
        return volumeRestantes;
    }
    public void setVolumeRestantes(double volumeRestantes) {

        this.volumeRestantes = volumeRestantes;
    }
    public void setBlocRestantes(Bloc[] blocRestantes) {
        this.blocRestantes = blocRestantes;
    }
    public EstimationPrix getVente() {
        return vente;
    }
    public void setVente(EstimationPrix vente) {
        this.vente = vente;
    }
    public EstimationPrix getOptimal() {
        return optimal;
    }
    public void setOptimal(EstimationPrix optimal) {
        this.optimal = optimal;
    }
    public EstimationPrix getMinimal() {
        return minimal;
    }
    public void setMinimal(EstimationPrix minimal) {
        this.minimal = minimal;
    }
    public DimensionUsuels getDimProfit() {
        return dimProfit;
    }
    public void setDimProfit(DimensionUsuels dimProfit) {
        this.dimProfit = dimProfit;
    }
    public DimensionUsuels getDimMinPerte() {
        return dimMinPerte;
    }
    public void setDimMinPerte(DimensionUsuels dimMinPerte) {
        this.dimMinPerte = dimMinPerte;
    }
}

