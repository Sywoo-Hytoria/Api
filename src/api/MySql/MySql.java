package fr.hytoria.api.MySql;

/**
 * Created by Sywoo on 06/02/2017.
 */

import fr.hytoria.api.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {

    protected static Connection connection;
    private static String host;
    private static String username;
    private static String password;


    public static void Connection(String hostadress, String bddusername, String bddpassword)
    {
        if (!isConnected())
            try
            {
                connection = DriverManager.getConnection(hostadress, bddusername, bddpassword);
                host = hostadress;
                username = bddusername;
                password = bddpassword;



            } catch (SQLException exception)
            {
                exception.printStackTrace();
            }
    }

    public static Connection Connection()
    {
        Connection connection = null;
        if (!isConnected()) {
            try
            {
                connection = DriverManager.getConnection(host, username, password);
                Main.getinstance().sender.sendMessage("[LOG] Connexion de la BBD effectué !");

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        return connection;
    }

    public static void Deconnection() {
        if (isConnected())
            try {
                connection.close();
                Main.getinstance().sender.sendMessage("[LOG] Déconnexion de la BBD effectué!");
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
    }

    public static boolean isConnected()
    {
        try {
            if ((connection == null) || (connection.isClosed()) || (!connection.isValid(5))) return false;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return true;
    }
}