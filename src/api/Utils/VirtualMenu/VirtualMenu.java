package fr.hytoria.api.Utils.VirtualMenu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import fr.hytoria.api.Main;
import fr.hytoria.api.Player.ApiPlayer;

public class VirtualMenu implements Listener{
  Inventory inv;
  Player owner;
  ApiPlayer sp;
  List<VirtualItem> items;
  String name;

  public VirtualMenu(String name, int size, boolean cancelClick, Player owner)
  {
    this.inv = Bukkit.createInventory(owner, size, name);
    this.owner = owner;
    this.name = name;
    this.sp = new ApiPlayer(owner);
    VirtualInventoryHandler.playervm.put(owner, this);
    this.items = new ArrayList<>();
    Bukkit.getPluginManager().registerEvents(this, Main.getinstance());
  }

  public Inventory getInventory() {
    return this.inv;
  }

  public void open() {
    this.owner.openInventory(this.inv);
    onOpen();
  }

  public void onOpen()
  {
  }

  public void addItem(VirtualItem vi, int slot) {
    this.inv.setItem(slot, vi.getItem());
    this.items.add(vi);
  }
}