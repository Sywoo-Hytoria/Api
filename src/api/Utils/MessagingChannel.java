package fr.hytoria.api.Utils;

/**
 * Created by Stephane on 06/02/2017.
 */

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import fr.hytoria.api.Player.ApiPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;


public class MessagingChannel
        implements PluginMessageListener
{
    public void onPluginMessageReceived(String s, Player player, byte[] bytes)
    {
        ByteArrayDataInput input = ByteStreams.newDataInput(bytes);
        String subchannel = input.readUTF();
        System.out.println(subchannel);
        String permission;
        if (subchannel.equalsIgnoreCase("messages")) {
            permission = input.readUTF();
            for (Player players : Bukkit.getOnlinePlayers()) {
                ApiPlayer scopePlayer = new ApiPlayer(players);
                if (scopePlayer.hasPermission(permission))
                    scopePlayer.sendMessage(input.readUTF());
            }
        }
    }
}