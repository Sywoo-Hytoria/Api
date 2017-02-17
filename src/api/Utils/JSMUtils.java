package fr.hytoria.api.Utils;


import org.bukkit.entity.Player;

import fr.hytoria.api.Utils.enumerations.ClickEvents;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class JSMUtils {

	/*
	 * @author OpperFx
	 * @param Ce message doit rester dans la classe.
	 */

	public String message;

	public TextComponent textComp = new  TextComponent(message);

	public JSMUtils(String message){
		this.message = message;
	}

	public void setHoverEvent(String hoverMessage){
		textComp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverMessage).create()));
	}

	public void setClickEvent(ClickEvents clicksEvents, String urlOrCommand){
		if(clicksEvents == ClickEvents.EXECUTE_CMD){
			textComp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, urlOrCommand));
		}else if(clicksEvents == ClickEvents.PROPOSE_CMD){
			textComp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, urlOrCommand));
		}else if(clicksEvents == ClickEvents.OPEN_URL){
			textComp.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, urlOrCommand));
		}
	}

	public void sendMessageTo(Player player){
		player.spigot().sendMessage(textComp);
	}
}

