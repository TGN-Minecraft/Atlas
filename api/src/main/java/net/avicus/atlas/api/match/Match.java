package net.avicus.atlas.api.match;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import net.avicus.atlas.api.map.AtlasMap;
import net.avicus.atlas.api.module.Module;
import net.avicus.atlas.api.registry.Registry;
import net.avicus.atlas.localization.text.Localizable;
import net.avicus.atlas.utils.Version;
import org.bukkit.World;
import org.bukkit.entity.Player;

public interface Match {

  Collection<Player> getPlayers();

  @SuppressWarnings("unchecked")
  <T extends Module> Optional<T> getModule(Class<T> type);

  boolean hasModule(Class<? extends Module> type);

  <T extends Module> T getRequiredModule(Class<T> type);

  void addModule(Module module);

  File getFolder();

  void warn(Exception e);

  void warnDeprecation(String message, Version changed);

  World getWorld();

  void load() throws IOException;

  void open();

  void close();

  void broadcast(Localizable translation);

  void importantBroadcast(Localizable translation);

  AtlasMap getMap();

  MatchFactory getFactory();

  Registry getRegistry();

  java.util.Set<Module> getModules();

  String getId();

  boolean isLoaded();
}
