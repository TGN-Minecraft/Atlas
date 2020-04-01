package net.avicus.atlas.menus.inventory;

import net.avicus.atlas.menus.MenuItem;
import org.bukkit.inventory.ItemStack;

public interface InventoryMenuItem extends MenuItem {

  /**
   * The item stack display in the inventory.
   */
  ItemStack getItemStack();

  /**
   * Check if the inventory should update the display of this item (by subsequently calling {@link
   * #getItemStack()}
   */
  boolean shouldUpdate();

  /**
   * Called after updating the display of this item.
   */
  void onUpdate();
}
