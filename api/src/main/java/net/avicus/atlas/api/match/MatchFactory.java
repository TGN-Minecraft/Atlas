package net.avicus.atlas.api.match;

import net.avicus.atlas.api.map.AtlasMap;
import net.avicus.atlas.api.module.Module;
import net.avicus.atlas.api.module.ModuleBuildException;
import net.avicus.atlas.api.module.ModuleFactory;

public interface MatchFactory {

  <F extends ModuleFactory<M>, M extends Module> void register(Class<F> clazz);

  @SuppressWarnings("unchecked")
  <F extends ModuleFactory<M>, M extends Module> F getFactory(Class<F> clazz);

  Match create(AtlasMap map) throws MatchBuildException, ModuleBuildException;
}
