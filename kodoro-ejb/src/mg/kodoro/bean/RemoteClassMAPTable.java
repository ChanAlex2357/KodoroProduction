package mg.kodoro.bean;

import java.sql.Connection;

public abstract class  RemoteClassMAPTable extends MaClassMAPTable{
    public abstract MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception;
    public static void createObjects(RemoteClassMAPTable[] objects , Connection conn , Connection conn2) throws Exception{
        System.out.println(objects);
        for (RemoteClassMAPTable maClassMAPTable : objects) {
            maClassMAPTable.createObject(conn,conn2);
        }
    }
}
