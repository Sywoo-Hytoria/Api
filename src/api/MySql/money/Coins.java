package fr.hytoria.api.MySql.money;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import fr.hytoria.api.MySql.MySql;
import fr.hytoria.api.MySql.SQLConnection;

public class Coins extends MySql
{
	public void createAccount(UUID uuid)
	{
		if (!isConnected())
			SQLConnection.Connection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT `money` FROM `coins` WHERE `uuid`=?");
			statement.setString(1, uuid.toString());
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next()) {
				statement.close();
				statement = connection.prepareStatement("INSERT INTO `coins`(uuid, money) VALUES (?,?)");
				statement.setString(1, uuid.toString());
				statement.setInt(2, 0);
				statement.executeUpdate();
				statement.close();
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	public Integer getCoins(UUID uuid) {
		if (!isConnected())
			SQLConnection.Connection();
		int money = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT `money` FROM `coins` WHERE `uuid`=?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				resultSet.first();
				money = resultSet.getInt("money");
				preparedStatement.close();
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}

		return Integer.valueOf(money);
	}

	public void addCoins(UUID uuid, int money) {
		if (!isConnected())
			SQLConnection.Connection();
		money += getCoins(uuid).intValue();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `coins` SET `money`=? WHERE `uuid`=?");
			preparedStatement.setInt(1, money);
			preparedStatement.setString(2, uuid.toString());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	public void removeCoins(UUID uuid, int money) {
		if (!isConnected())
			SQLConnection.Connection();
		money = getCoins(uuid).intValue() - money;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `coins` SET `money`=? WHERE `uuid`=?");
			preparedStatement.setInt(1, money);
			preparedStatement.setString(2, uuid.toString());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
