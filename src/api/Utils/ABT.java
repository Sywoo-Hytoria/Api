package fr.hytoria.api.Utils;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class ABT {
 
 public void sendTitle(Player player, String title, String subtitle, int ticks){
  
  IChatBaseComponent icbc1 = ChatSerializer.a("{\"text\": \"" + title + "\"}");
  IChatBaseComponent icbc2 = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
  
  PacketPlayOutTitle packett = new PacketPlayOutTitle(EnumTitleAction.TITLE, icbc1);
  PacketPlayOutTitle packetst = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, icbc2);
  
  ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packett);
  ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetst);
  sendTime(player,ticks);
  
 }
 private void sendTime(Player player, int ticks){
  
  PacketPlayOutTitle timepacket = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, 20, ticks, 20);
  ((CraftPlayer) player).getHandle().playerConnection.sendPacket(timepacket);
  
 }
 public void sendActionBar(Player player, String message){
  
  IChatBaseComponent icbc1 = ChatSerializer.a("{\"text\": \"" + message + "\"}");
  
  PacketPlayOutChat ppoc = new PacketPlayOutChat(icbc1, (byte)2);
  
  ((CraftPlayer) player).getHandle().playerConnection.sendPacket(ppoc);
  
 }
 
}