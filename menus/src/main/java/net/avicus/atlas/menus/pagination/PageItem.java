package net.avicus.atlas.menus.pagination;

import java.util.Locale;
import net.avicus.atlas.menus.IndexedMenuItem;
import net.avicus.atlas.menus.inventory.ClickableInventoryMenuItem;
import net.avicus.atlas.menus.inventory.StaticInventoryMenuItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PageItem extends StaticInventoryMenuItem implements IndexedMenuItem,
    ClickableInventoryMenuItem {

  private static final char LEFT_ARROW = '❰';
  private static final char RIGHT_ARROW = '❱';

  private final PaginatedInventory menu;
  private final Player player;
  private final boolean direction;  // negative is left, positive is right
  private final int index;

  public PageItem(PaginatedInventory menu, Player player, boolean direction, int index) {
    this.menu = menu;
    this.player = player;
    this.direction = direction;
    this.index = index;
  }

  @Override
  public ItemStack getItemStack() {
    Locale locale = this.player.getLocale();

    StringBuilder name = new StringBuilder();
    name.append(ChatColor.GRAY);

    if (this.direction) {
      name.append("Next Page ");
      name.append(RIGHT_ARROW);
    } else {
      name.append(LEFT_ARROW);
      name.append(" Previous Page");
    }

    ItemStack stack = new ItemStack(Material.EMPTY_MAP);
    ItemMeta meta = stack.getItemMeta();
    meta.setDisplayName(name.toString());
    stack.setItemMeta(meta);
    return stack;
  }

  @Override
  public int getIndex() {
    return this.index;
  }

  @Override
  public void onClick(InventoryClickEvent event) {
    if (this.direction) {
      this.menu.nextPage();
    } else {
      this.menu.prevPage();
    }
  }
}
