package mg.kodoro.models.transformation;

import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.models.Bloc;
import mg.kodoro.utils.ValidationUtils;
import utils.TimeUtils;

public class Transformation extends MaClassMAPTable{
    private String  idTransformation;
    private String  idBloc;
    private double  marge;
    private Date    dateTransformation;

    protected TransformationFilleLib[] detailsTransformation;

    public Transformation(String idBloc, double marge, Date dateTransformation) {
        this.idBloc = idBloc;
        this.marge = marge;
        this.dateTransformation = dateTransformation;
    }

    public Transformation(){
        setNomTable("TRANSFORMATION");
    }
    
    public Transformation ( String idBloc , String marge , String dateTransformation){
        setIdBloc(idBloc);
        setMarge(marge);
        setDateTransformation(getDateTransformation());
    }

    // Setters et Getters
    public String getIdTransformation() {
        return idTransformation;
    }
    public void setIdTransformation(String idTransformation) {
        this.idTransformation = idTransformation;
    }
    public String getIdBloc() {
        return idBloc;
    }
    public void setIdBloc(String idBloc) {
        this.idBloc = idBloc;
    }
    public double getMarge() {
        return marge;
    }
    public void setMarge(double marge) {
        this.marge = marge;
    }
    public void setMarge(String marge){
        setMarge(ValidationUtils.validatePositiveStringDouble(marge));
    }
    public Date getDateTransformation() {
        return dateTransformation;
    }
    public void setDateTransformation(String dateTransformation) throws ParseException{
        if (dateTransformation == null) {
            throw new IllegalArgumentException ("Veuillez saisir une date valide");
        }
        setDateTransformation(TimeUtils.convertToSqlDate(dateTransformation,"eng"));
    }
    public void setDateTransformation(Date dateTransformation) {
        this.dateTransformation = dateTransformation;
    }
    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("TRANSFORMATION");
        super.createObject(c);
        return this;
    }
    
    @Override
    public String getAttributIDName() {
        return "idTransformation";
    }
    @Override
    public String getTuppleID() {
        return this.getIdTransformation();
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("TRANS", "GET_TRANS_SEQ");
        this.setIdTransformation( makePK(c) );
    }


    public TransformationFilleLib[] getDetailsTransformation(Connection conn) throws Exception{
        if (this.detailsTransformation == null) {
            TransformationFilleLib[] transF = new TransformationFilleLib[1];
            transF[0] = new TransformationFilleLib();
            transF[0].setIdTransformation(this.getIdTransformation());
    
            transF = (TransformationFilleLib[]) CGenUtil.rechercher( transF[0],null,null,conn,"");
            if (transF.length <= 0) {
                return new TransformationFilleLib[0];
            }
            this.detailsTransformation = transF; 
        }
        return this.detailsTransformation;
    }

    public static double getSommePrixDeRevient(TransformationLib[] transformations, Connection conn) throws Exception {
        double sommeRevient = 0;
        for (TransformationLib trans : transformations) {
            sommeRevient += trans.getMontantPrixDeRevient(conn);
        }
        return sommeRevient;
    }
    public static double getSommeMontantVente(TransformationLib[] transformations , Connection conn)throws Exception {
        double sommeVente = 0;
        for (TransformationLib trans : transformations) {
            sommeVente += trans.getMontantVente(conn);
        }
        return sommeVente;
    }
    public double getMontantPrixDeRevient(Connection conn) throws Exception{
        TransformationFilleLib[] details = this.getDetailsTransformation(conn);
        return TransformationFilleLib.getSommeMontantPrixDeRevient(details,conn);
    }
    public double getMontantVente(Connection conn) throws Exception{
        TransformationFilleLib[] details = this.getDetailsTransformation(conn);
        return TransformationFilleLib.getSommeMontantVente(details,conn);
    }
    
    public void updatePrixDeRevientTransformation(double taux , Connection conn) throws Exception {
        setNomTable("TRANSFORMATION");
        TransformationFilleLib[] transF = getDetailsTransformation(conn);
        for (TransformationFilleLib transformationFille : transF) {
            transformationFille.updatePrixDeRevient(taux,conn);
        }
    }

    public Bloc getBloc(Connection conn) throws Exception {
        return Bloc.getById(this.getIdBloc(), conn);
    }

    public static TransformationLib getById(String idTransformation,Connection conn) throws Exception {
        TransformationLib lib = new TransformationLib();
        lib.setIdTransformation(idTransformation);

        TransformationLib[] libs = (TransformationLib[]) CGenUtil.rechercher(lib, null,null,conn,"");

        if (libs.length > 0) {
            return libs[0];
        }
        return null;
    }

    public TransformationLib getAsTransformationLib(Connection conn) throws Exception {
        return Transformation.getById(this.getIdTransformation(), conn);
    }
}
