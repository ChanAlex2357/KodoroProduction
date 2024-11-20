package mg.kodoro.models.blockggen;


import java.sql.Connection;

import mg.kodoro.models.Bloc;
import mg.kodoro.models.production.Machine;
import mg.kodoro.utils.blockggen.GenerateurBloc;

public class AdminBlocGen {
    protected int quantite;
    protected DataBloc dataBloc;
    protected GenerateurBloc blocGen;
    protected Machine[] machines;
    
    public  AdminBlocGen(){
        setQuantite(1);
    }
    public AdminBlocGen( int qte ,double LMin, double LMax, double lMin, double lMax,
    double eMin, double eMax, double aMin, double aMax, double marge){
        setQuantite(qte);
        setDataBloc(new DataBloc(LMin, LMax, lMin, lMax, eMin, eMax, aMin, aMax, marge));
    }
    public AdminBlocGen(String qte , String LMin, String LMax, String lMin, String lMax,
    String eMin, String eMax, String aMin, String aMax, String marge){
        setQuantite(Integer.parseInt(qte));
        setDataBloc(new DataBloc(LMin, LMax, lMin, lMax, eMin, eMax, aMin, aMax, marge));
    }

    public Bloc[] generate(Connection conn)throws Exception {
        Bloc[] blocs = new Bloc[this.getQuantite()];
        Machine machine = null;
        for (Bloc bloc : blocs) {
            // Generer le bloc
            bloc = new Bloc(blocGen, conn);
            // Machine aleatoire

            // Produire le bloc par la machine
            machine.produire(bloc, conn);
        }
        return blocs;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public DataBloc getDataBloc() {
        return dataBloc;
    }
    public void setDataBloc(DataBloc dataBloc) {
        this.dataBloc = dataBloc;
    }
    public GenerateurBloc getBlocGen() {
        if(this.blocGen == null){
            setBlocGen(new GenerateurBloc(this.getDataBloc()));
        }
        return blocGen;
    }
    public void setBlocGen(GenerateurBloc blocGen) {
        this.blocGen = blocGen;
    }

}
