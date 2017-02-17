package fr.hytoria.api.listener;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.hytoria.api.MySql.ban.BanSQL;
import fr.hytoria.api.Player.ApiPlayer;
import fr.hytoria.api.Player.PlayerCache;

public class PlayerJoin implements Listener {

	BanSQL ban = new BanSQL();
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event){
		
		ApiPlayer player = new ApiPlayer(event.getPlayer());
		player.createaccount(player.getUniqueId());
		UUID uuid = player.getUniqueId();
		
    	PlayerCache playercache = new PlayerCache(player.getRankId(), player.getcoins(), player.getPayout(),
    			player.getAllPermission(), ban.isban(uuid), 
    			ban.getExpirationDate(uuid), ban.getReason(uuid), ban.getOwner(uuid));		
		if(playercache.isBan()){
			event.setJoinMessage(null);
			player.kickPlayer("§cVous êtes banni\n"
					+ "Par: " + ban.getOwner(uuid) + "\n"
							+ "Pour: " + ban.getReason(uuid) + "\n"
									+ "Jusqu'au : " + ban.getExpirationDate(uuid));
		}
		
		
		
	}
	
	
}
