package fr.hytoria.api.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class MessagingChannel
  implements PluginMessageListener
{
  public void onPluginMessageReceived(String s, Player player, byte[] bytes)
  {
    ByteArrayDataInput input = ByteStreams.newDataInput(bytes);
    String subchannel = input.readUTF();
    System.out.println(subchannel);
   // String permission;
    if (subchannel.equalsIgnoreCase("messages")) {
     // permission = input.readUTF();
      for (Player players : Bukkit.getOnlinePlayers()) {
    	  players.sendMessage(input.readUTF());
      }
    }
  }
}