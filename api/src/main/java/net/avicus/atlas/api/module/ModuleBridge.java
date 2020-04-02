package net.avicus.atlas.api.module;

public interface ModuleBridge<T extends Module> {

  void onOpen(T module);

  void onClose(T module);
}
