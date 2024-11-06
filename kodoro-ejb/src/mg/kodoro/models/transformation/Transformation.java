package mg.kodoro.models.transformation;

import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.utils.ValidationUtils;
import utils.TimeUtils;

public class Transformation extends MaClassMAPTable{
    private String  idTransformation;
    private String  idBloc;
    private double  marge;
    private Date    dateTransformation;

    protected TransformationFille[] detailsTransformation;

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
        System.out.println("DATE FABRICATION : "+dateTransformation);
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
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        this.createObject(localconn);
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


    public TransformationFille[] getDetailsTransformation(Connection conn) throws Exception{
        if (this.detailsTransformation == null) {
            TransformationFille[] transF = new TransformationFille[1];
            transF[0] = new TransformationFille();
            transF[0].setIdTransformation(this.getIdTransformation());
    
            transF = (TransformationFille[]) CGenUtil.rechercher( transF[0],null,null,conn,"");
            if (transF.length <= 0) {
                return new TransformationFille[0];
            }
            this.detailsTransformation = transF; 
        }
        return this.detailsTransformation;
    }


    public double getMontantTransformation(Connection conn) throws Exception{
        TransformationFille[] details = this.getDetailsTransformation(conn);
        return TransformationFille.getSommeMontantVente(details,conn);
    }
    

}
