package fr.hytoria.api.Utils.VirtualMenu;

import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryItem{
  public Player owner;
  public ItemStack itemstack;
  public ItemMeta itemMeta;
  public boolean permNeeded;
  public String permssion;
  public int slot;

  public InventoryItem(Player owner, Material itemStack, int slot, String name, @Nullable List<String> lore, boolean permNeeded, @Nullable String permission)
  {
    this.owner = owner;
    this.itemstack = new ItemStack(itemStack);
    this.permNeeded = permNeeded;
    this.permssion = permission;
    this.slot = slot;
    this.itemMeta = this.itemstack.getItemMeta();
    this.itemMeta.setDisplayName(name);
    this.itemMeta.setLore(lore);
    this.itemstack.setItemMeta(this.itemMeta);
    VirtualInventoryHandler.ii.add(this);
  }

  public void onUse() {

  }

  public void give() {
    Inventory inv = this.owner.getInventory();
    inv.setItem(this.slot, this.itemstack);
  }
}