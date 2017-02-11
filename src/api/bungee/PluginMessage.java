package fr.hytoria.api.bungee;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import fr.hytoria.api.Bungee;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PluginMessage
implements Listener
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onReceived(PluginMessageEvent event)
	{
		String msg;
		if (event.getTag().equals("server")) {
			ByteArrayDataInput input = ByteStreams.newDataInput(event.getData());
			String subchannel = input.readUTF();
			System.out.println(subchannel);
			if (subchannel.equalsIgnoreCase("message")) {
				msg = input.readUTF();
				Bungee.instance.getProxy().broadcast(msg);
			} else if (subchannel.equalsIgnoreCase("messages")) {
				System.out.println(input.readUTF());
				for (String string : BungeeCord.getInstance().getServers().keySet()) {
					ServerInfo server = BungeeCord.getInstance().getServerInfo(string);
					server.sendData("scopegames", event.getData());
				}
			}
		}
	}
}
