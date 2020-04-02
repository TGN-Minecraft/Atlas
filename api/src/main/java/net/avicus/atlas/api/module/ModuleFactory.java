package net.avicus.atlas.api.module;

import java.util.Optional;
import net.avicus.atlas.api.match.Match;
import net.avicus.atlas.document.Node;

public interface ModuleFactory<M extends Module> {

  Optional<M> build(Match match, Node root) throws ModuleBuildException;
}
