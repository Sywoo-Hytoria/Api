package fr.hytoria.api.Utils.VirtualMenu;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class VirtualInventoryHandler implements Listener{
	
  public static HashMap<Player, VirtualMenu> playervm = new HashMap<>();
  public static ArrayList<InventoryItem> ii = new ArrayList<>();

  @EventHandler
  public void onClick(InventoryClickEvent e) { Player player = (Player)e.getWhoClicked();

    VirtualMenu vm = (VirtualMenu)playervm.get(player);

    if (e.getInventory() == null) return;
    if (e.getCurrentItem() == null) return;
    if (e.getInventory() == null) return;
    if (!e.getInventory().getName().equals(vm.name)) return;

    ItemStack item = e.getCurrentItem();
    ItemMeta itemm = item.getItemMeta();

    if (item.getType().equals(Material.SKULL_ITEM)) {
      SkullMeta skullmeta = (SkullMeta)item.getItemMeta();
      if ((skullmeta.getOwner().equals(player.getName())) && 
        (skullmeta.getLore() != null)) {
        e.setCancelled(true);
      }

    }

    if (player == vm.owner)
      for (VirtualItem vi : vm.items) {
        ItemStack is = vi.getItem();
        ItemMeta im = is.getItemMeta();
        if ((!item.getType().equals(is.getType())) || 
          (!itemm.getDisplayName().equals(im.getDisplayName()))) continue;
        e.setCancelled(true);
        if ((vi.requirePerm) && (!player.hasPermission(vi.permission))) {
          player.sendMessage("§cVous n'avez pas la permission d'utiliser cet item!");
          return;
        }
        vi.onUse();
      }
  }

  @EventHandler
  public void onCloseInventory(InventoryCloseEvent e)
  {
    Player player = (Player)e.getPlayer();
  }
  @EventHandler
  public void onInteract(PlayerInteractEvent e) {
    Player player = e.getPlayer();
    ItemStack is = e.getItem();
    if (is == null) return;
    for (InventoryItem inv : ii) {
      if ((inv.owner != player) || 
        (!is.getItemMeta().getDisplayName().equals(inv.itemMeta.getDisplayName()))) continue;
      inv.onUse();
    }
  }

  @EventHandler
  public void onOpen(InventoryOpenEvent e)
  {
    Player player = (Player)e.getPlayer();
  }
}