package fr.hytoria.api.listener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import fr.hytoria.api.Main;
import fr.hytoria.api.Utils.VirtualMenu.VirtualInventoryHandler;

public class ListenerMananager implements org.bukkit.event.Listener{
	
	private Main instance = Main.getinstance();
	
public void register(){

	PluginManager pm = Bukkit.getPluginManager();
	pm.registerEvents(new VirtualInventoryHandler(), this.instance);
	
	
	}
}