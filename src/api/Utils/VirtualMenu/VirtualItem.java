package fr.hytoria.api.Utils.VirtualMenu;

import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.hytoria.api.Main;

public class VirtualItem implements Listener{
	
  public ItemStack is;
  public ItemMeta im;
  public Player owner;
  public boolean requirePerm;
  public String permission;
  public String name;
  public byte byt;

  public VirtualItem(Player owner, Material mat, String name, @Nullable int count, @Nullable List<String> lore, boolean requirePerm, @Nullable String perm)
  {
    this.is = new ItemStack(mat, count);
    this.im = this.is.getItemMeta();
    this.im.setDisplayName(name);
    this.im.setLore(lore);
    this.is.setItemMeta(this.im);
    this.permission = perm;
    this.requirePerm = requirePerm;
    this.owner = owner;
    this.name = name;
    Main.getinstance().getServer().getPluginManager().registerEvents(this, Main.getinstance());
  }
  
  public VirtualItem(Player owner, Material mat,String name, @Nullable int count, @Nullable List<String> lore, boolean requirePerm, @Nullable String perm, byte byt)
  {
    this.is = new ItemStack(mat, count, byt);
    this.im = this.is.getItemMeta();
    this.im.setDisplayName(name);
    this.im.setLore(lore);
    this.is.setItemMeta(this.im);
    this.permission = perm;
    this.requirePerm = requirePerm;
    this.owner = owner;
    this.name = name;
    this.byt = byt;
    Main.getinstance().getServer().getPluginManager().registerEvents(this, Main.getinstance());
  }

  public ItemStack getItem() {
    return this.is;
  }

  public void onUse()
  {
  }
}