package mg.kodoro.utils;

import java.sql.Connection;

import utilitaire.UtilDB;

public class DbUtils {
    public static Connection getRemoteConn(){
        return new UtilDB().GetConn("gallois","gallois");
    }
}
