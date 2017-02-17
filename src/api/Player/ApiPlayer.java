package fr.hytoria.api.Player;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.hytoria.api.Main;
import fr.hytoria.api.MySql.ban.BanSQL;
import fr.hytoria.api.MySql.grade.GradeSQL;
import fr.hytoria.api.MySql.money.Coins;
import fr.hytoria.api.MySql.money.PayoutCoins;
import fr.hytoria.api.MySql.perm.PermSQL;
import fr.hytoria.api.Utils.ABT;
import fr.hytoria.api.Utils.enumerations.ServerList;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;

/**
 * Created by Sywoo on 06/02/2017.
 */
public class ApiPlayer extends CraftPlayer{

    private  UUID uuid;
    private static CraftPlayer craftPlayer;
    private GradeSQL gradeSQL = new GradeSQL();
    private Coins coins = new Coins();
    private PayoutCoins coinspay = new PayoutCoins();
    private PermSQL permissions = new PermSQL();
    private BanSQL ban = new BanSQL();
    
    public ApiPlayer(Player craftPlayer) {
		super((CraftServer)Bukkit.getServer(), ((CraftPlayer)craftPlayer).getHandle());
		this.uuid = craftPlayer.getUniqueId();
		this.craftPlayer = ((CraftPlayer)craftPlayer);
	}

    
    /*
     * Envoie simplifier
     */
    public void createaccount(UUID uuid){
    	coins.createAccount(uuid);
    	gradeSQL.createAccount(uuid);
    	coinspay.createAccount(uuid);
    	permissions.setupPerms(Bukkit.getOfflinePlayer(uuid).getPlayer());
    	PlayerCache playercache = new PlayerCache(getRankId(), getcoins(), getPayout(),
    			getAllPermission(), ban.isban(uuid), 
    			ban.getExpirationDate(uuid), ban.getReason(uuid), ban.getOwner(uuid));
    	
    	
    }
    

    public void sendMessageWithBungee(String message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("message");
        out.writeUTF(message);
        craftPlayer.sendPluginMessage(Main.getinstance(), "server", out.toByteArray());
      }


	public void sendTo(ServerList serverList) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(serverList.getServer());
		craftPlayer.sendPluginMessage(Main.getinstance(), "BungeeCord", out.toByteArray());
	}

	public void sendTo(String name) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(name);
		craftPlayer.sendPluginMessage(Main.getinstance(), "BungeeCord", out.toByteArray());
	}
    public void sendHeaderFooter(String headerMSG, String footerMSG) {
        PlayerConnection connection = craftPlayer.getHandle().playerConnection;
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
        craftPlayer.getHandle().playerConnection.sendPacket(packet);
    }
    public void sendTitle(String title, String subtitle, int ticks){
        ABT abt = new ABT();
        abt.sendTitle(craftPlayer, title, subtitle, ticks);
       }
    /*
     * Gestion des grades
     */
	public int getRankId() {
		return this.gradeSQL.getRankId(this.uuid).intValue();
	}
	public String getRankName() {
		return this.gradeSQL.getRank(this.uuid);
	}

	public void setRank(Integer rank) {
		this.gradeSQL.setRank(this.uuid, rank);
	}

	public String getRankPrefix() {
		return this.gradeSQL.getRankPrefix(this.uuid);
	}
	public Integer getcoins() {
		return this.coins.getCoins(this.uuid);
	}

	public void addcoins(int money) {
		this.coins.addCoins(this.uuid, money);
	}

	public void removecoins(int money) {
		this.coins.removeCoins(this.uuid, money);
	}
	
	/*
	 * Gestion des coins 
	 * 
	 */
	public Integer getPayout(){
		return this.coinspay.getCoins(uuid);
	}
	public void addPayout(int money) {
		this.coinspay.addCoins(this.uuid, money);
	}

	public void removePayout(int money) {
		this.coinspay.removeCoins(this.uuid, money);
	}
	/*
	 * 
	 * 
	 */
	@SuppressWarnings("static-access")
	public void addPermission(String permission){
		permissions.addPermission(this.craftPlayer, permission);
	}
	
	@SuppressWarnings("static-access")
	public void removePermission(String permission){
		permissions.removePermission(this.craftPlayer, permission);
	}
	public String[] getAllPermission(){
		
		return permissions.getAllPermission();
	}
    /*
     * 
     */


}
