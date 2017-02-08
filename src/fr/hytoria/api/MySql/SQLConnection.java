package fr.hytoria.api.MySql;

/**
 * Created by Sywoo on 06/02/2017.
 */

public class SQLConnection
{

    private static MySql mysql = new MySql();

    public static void Connection(String tablename, String user, String password) {
        MySql.Connection("jdbc:mysql://127.0.0.1:3306/"+tablename, user, password);
    }

    public static void Deconnection()
    {
        MySql.Deconnection();
    }
}