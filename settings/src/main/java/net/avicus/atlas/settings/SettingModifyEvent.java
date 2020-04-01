package net.avicus.atlas.settings;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Called when a setting if modified by a player.
 */
public class SettingModifyEvent extends Event {

  private static final HandlerList handlers = new HandlerList();
  @Getter
  private final SettingContext setting;
  @Getter
  private final Player player;

  public SettingModifyEvent(SettingContext setting, Player player) {
    this.setting = setting;
    this.player = player;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

  public HandlerList getHandlers() {
    return handlers;
  }
}
