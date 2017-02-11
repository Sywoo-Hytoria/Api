package fr.hytoria.api.MySql;

/**
 * Created by Sywoo on 06/02/2017.
 */

public class SQLConnection
{

	private static MySql mysql = new MySql();

	public static void Connection() { 
		MySql.Connection("jdbc:mysql://127.0.0.1:3306/************", "root", "**************");
	}

	public static void Deconnection()
	{
	}
}