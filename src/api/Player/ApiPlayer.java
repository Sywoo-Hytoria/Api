package fr.hytoria.api.Player;

import java.lang.reflect.Field;
import java.util.UUID;

import fr.hytoria.api.Main;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;

/**
 * Created by Sywoo on 06/02/2017.
 */
public class ApiPlayer extends CraftPlayer{

    private UUID uuid;
    private static CraftPlayer craftPlayer;

    public ApiPlayer(Player player) {
        super((CraftServer) Bukkit.getServer(), ((CraftPlayer)craftPlayer).getHandle());
        this.uuid = craftPlayer.getUniqueId();
        this.craftPlayer = ((CraftPlayer)craftPlayer);
    }

    public void sendMessageWithBungee(String message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("message");
        out.writeUTF(message);
        this.craftPlayer.sendPluginMessage(Main.getinstance(), "server", out.toByteArray());
    }
    public void sendTo(String servername) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(servername);
        this.craftPlayer.sendPluginMessage(Main.getinstance(), "BungeeCord", out.toByteArray());
    }
    public void sendHeaderFooter(String headerMSG, String footerMSG) {
        PlayerConnection connection = this.craftPlayer.getHandle().playerConnection;
        IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{ text: \"" + headerMSG + "\" }");
        IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{ text: \"" + footerMSG + "\" }");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try
        {
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, header);
            headerField.setAccessible(!headerField.isAccessible());

            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footer);
            footerField.setAccessible(!footerField.isAccessible());

            connection.sendPacket(packet);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void sendActionBarMessage(String actionBarMSG) {
        IChatBaseComponent actionBar = IChatBaseComponent.ChatSerializer.a("{ text: \"" + actionBarMSG + "\" }");
        PacketPlayOutChat packet = new PacketPlayOutChat(actionBar, (byte) 2);
        this.craftPlayer.getHandle().playerConnection.sendPacket(packet);
    }


}
