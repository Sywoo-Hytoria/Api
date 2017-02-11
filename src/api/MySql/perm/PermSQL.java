package fr.hytoria.api.MySql.perm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import fr.hytoria.api.Main;
import fr.hytoria.api.MySql.MySql;
import fr.hytoria.api.MySql.SQLConnection;
import fr.hytoria.api.MySql.grade.GradeSQL;

public class PermSQL extends MySql
{
  GradeSQL sql = new GradeSQL();

  public void setupPerms(Player player)
  {
    if (!isConnected())
      SQLConnection.Connection();
    int rank = this.sql.getRankId(player.getUniqueId()).intValue();
    try
    {
      PreparedStatement statement = connection.prepareStatement("SELECT `permission` FROM `RankPerm` WHERE `rank_id`=?");
      statement.setInt(1, rank);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String perm = resultSet.getString("permission");
        player.addAttachment(Main.getinstance(), perm, true);
      }
      statement.close();
      statement = connection.prepareStatement("SELECT `permission` FROM `Perm` WHERE `uuid`=?");
      statement.setString(1, player.getUniqueId().toString());
      resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String perm = resultSet.getString("permission");
        player.addAttachment(Main.getinstance(), perm, true);
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  public void addPermission(Player player, String permission) {
    if (!isConnected())
      SQLConnection.Connection();
    try {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO `Perm`(`uuid`, `permission`) VALUES (?,?)");
      statement.setString(1, player.getUniqueId().toString());
      statement.setString(2, permission);
      statement.executeUpdate();
      statement.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    player.addAttachment(Main.getinstance(), permission, true);
  }
  
  public void removePermission(Player player, String permission) {
	    if (!isConnected())
	      SQLConnection.Connection();
	    try {
	      PreparedStatement statement = connection.prepareStatement("DELETE INTO `Perm`(`uuid`, `permission`) VALUES (?,?)");
	      statement.setString(1, player.getUniqueId().toString());
	      statement.setString(2, permission);
	      statement.executeUpdate();
	      statement.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    player.addAttachment(Main.getinstance(), permission, false);

	  }

  public String[] getAllPermission()
  {
    if (!isConnected())
      SQLConnection.Connection();
    return null;
  }
}