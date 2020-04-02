package net.avicus.atlas.api.module;

import org.bukkit.event.Listener;

public interface Module extends Listener {

  default void open() {

  }

  default void close() {

  }
}
