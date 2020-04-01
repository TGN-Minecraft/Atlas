package net.avicus.atlas.menus.inventory;

import net.avicus.atlas.menus.MenuItem;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * A menu item that can be clicked, and handles clicks.
 */
public interface ClickableInventoryMenuItem extends MenuItem {

  void onClick(InventoryClickEvent event);
}
