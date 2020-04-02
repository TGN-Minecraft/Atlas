package net.avicus.atlas.api.map;

import java.util.List;
import net.avicus.atlas.api.map.author.Author;
import net.avicus.atlas.utils.Version;

public interface AtlasMap {

  Version getSpecification();

  String getSlug();

  String getName();

  Version getVersion();

  List<Author> getAuthors();

  List<Author> getContributors();
}
