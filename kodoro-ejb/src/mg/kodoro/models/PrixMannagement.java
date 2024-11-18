package mg.kodoro.models;

import java.sql.Connection;

public interface PrixMannagement {
    public void updatePrixDeRevient(double tauxDeChange);
    public void updatePrixDeRevient(double tauxDeChange , Connection conn) throws Exception;
}