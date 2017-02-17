package fr.hytoria.api;

import java.util.concurrent.TimeUnit;

import org.bukkit.event.EventHandler;

import fr.hytoria.api.MySql.SQLConnection;
import fr.hytoria.api.bungee.PluginMessage;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class Bungee extends Plugin
implements Listener
{
public static Bungee instance;

public void onEnable()
{
  instance = this;
  getProxy().registerChannel("server");
  getProxy().getPluginManager().registerListener(this, new PluginMessage());
  getProxy().getPluginManager().registerListener(this, this);
  BungeeCord.getInstance().getScheduler().schedule(this, new Runnable()
  {
    public void run() {
      SQLConnection.Connection();
    }
  }
  , 0L, 7200L, TimeUnit.SECONDS);
}

public void onDisable()
{
  SQLConnection.Deconnection();
}
@EventHandler
public void onProxiedPlayerJoin(PostLoginEvent event) {
  System.out.println(event.getPlayer().getUniqueId());
}
}
