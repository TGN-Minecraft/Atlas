package net.avicus.atlas.menus;

import java.util.Collection;

/**
 * A GUI interface that a player can view.
 */
public interface Menu<T extends MenuItem> {

  /**
   * Opens the menu.
   */
  void open();

  /**
   * Closes the menu.
   */
  void close();

  Collection<T> getItems();
}
