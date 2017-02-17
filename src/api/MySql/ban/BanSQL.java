package fr.hytoria.api.MySql.ban;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import fr.hytoria.api.MySql.MySql;

public class BanSQL extends MySql
{
  public boolean isban(UUID uuid)
  {
    try
    {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `BanList` WHERE `UUID`=?");
      preparedStatement.setString(1, uuid.toString());
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next())
        return true;
    }
    catch (SQLException exception) {
      exception.printStackTrace();
    }
    return false;
  }

  public Date getExpirationDate(UUID uuid) {
    Date date = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT `expiration_date` FROM `BanList` WHERE `UUID`=?");
      preparedStatement.setString(1, uuid.toString());
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next())
        date = resultSet.getDate("expiration_date");
    }
    catch (SQLException exception) {
      exception.printStackTrace();
    }
    return date;
  }

  public String getReason(UUID uuid) {
    String reason = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT `reason` FROM `BanList` WHERE `UUID`=?");
      preparedStatement.setString(1, uuid.toString());
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next())
        reason = resultSet.getString("reason");
    }
    catch (SQLException exception) {
      exception.printStackTrace();
    }
    return reason;
  }

  public String getOwner(UUID uuid) {
    String owner = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT `Owner` FROM `BanList` WHERE `UUID`=?");
      preparedStatement.setString(1, uuid.toString());
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next())
        owner = resultSet.getString("Owner");
    }
    catch (SQLException exception) {
      exception.printStackTrace();
    }
    return owner;
  }
}