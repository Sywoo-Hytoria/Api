package fr.hytoria.api.MySql.grade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import fr.hytoria.api.MySql.MySql;
import fr.hytoria.api.MySql.SQLConnection;

public class GradeSQL extends MySql
{
	public void createAccount(UUID uuid)
	{
		if (!isConnected())
			SQLConnection.Connection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT `rank_id` FROM `User` WHERE uuid=?");
			statement.setString(1, uuid.toString());
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next()) {
				statement.close();
				statement = connection.prepareStatement("INSERT INTO `User`(`uuid`, `rank_id`) VALUES (?,?)");
				statement.setString(1, uuid.toString());
				statement.setInt(2, 1);
				statement.executeUpdate();
				statement.close();
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	public String getRank(UUID uuid)
	{
		String rank = "error";
		if (!isConnected())
			SQLConnection.Connection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT `rank_id` FROM `User` WHERE uuid=?");
			statement.setString(1, uuid.toString());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int rank_id = resultSet.getInt("rank_id");
				statement.close();
				statement = connection.prepareStatement("SELECT `name' FROM `Rank` WHERE `id`=?");
				statement.setInt(1, rank_id);
				resultSet = statement.executeQuery();
				if (resultSet.next()) {
					rank = resultSet.getString("name");
				}
				statement.close();
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return rank;
	}

	public Integer getRankId(UUID uuid) {
		if (!isConnected())
			SQLConnection.Connection();
		Integer rank = Integer.valueOf(0);
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT `rank_id` FROM `User` WHERE uuid=?");
			statement.setString(1, uuid.toString());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				rank = Integer.valueOf(resultSet.getInt("rank_id"));
				statement.close();
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return rank;
	}

	public void setRank(UUID uuid, Integer rankid) {
		if (!isConnected())
			SQLConnection.Connection();
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE `User` SET `rank_id`=? WHERE `uuid`=?");
			statement.setInt(1, rankid.intValue());
			statement.setString(2, uuid.toString());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	public String getRankPrefix(UUID uuid) {
		if (!isConnected())
			SQLConnection.Connection();
		String prefix = "error";
		int rank = getRankId(uuid).intValue();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT `prefix` FROM `Rank` WHERE `id`=?");
			statement.setInt(1, rank);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				prefix = resultSet.getString("prefix");
			}

			statement.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return prefix;
	}

	public String getRankPrefix(int id) {
		if (!isConnected())
			SQLConnection.Connection();
		String prefix = "INCONNUS AU BATAILLON";
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT `prefix` FROM `Rank` WHERE `id`=?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				prefix = resultSet.getString("prefix");
			}

			statement.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return prefix;
	}
}

