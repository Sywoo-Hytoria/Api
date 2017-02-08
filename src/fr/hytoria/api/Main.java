package fr.hytoria.api;

import fr.hytoria.api.Utils.MessagingChannel;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Sywoo on 05/02/2017.
 */
public class Main extends JavaPlugin{

//Instance
    public static Main instance;
    public static Main getinstance(){
        return instance;
    }
//fin de l'instance

    public ConsoleCommandSender sender;

    @Override
    public void onEnable() {

        instance = this;
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "server");
        getServer().getMessenger().registerIncomingPluginChannel(this, "server", new MessagingChannel());
    }
}

