package net.avicus.atlas.parsing.simple;

import java.util.List;
import net.avicus.atlas.document.ParsingException;
import net.avicus.atlas.document.StringHolder;
import net.avicus.atlas.parsing.SimpleParser;
import net.avicus.atlas.utils.Version;

/**
 * Parses {@link Version}s from {@link StringHolder}s. Versions must be in semantic format.
 *
 * @author Austin Mayes
 */
public class VersionParser implements SimpleParser<Version> {

  private final SimpleParser<Integer> integerParser;
  private final ListParser listParser;

  /**
   * Constructor.
   *
   * @param integerParser to parse the individual version parts
   * @param listParser used to parse lists
   */
  public VersionParser(SimpleParser<Integer> integerParser, ListParser listParser) {
    this.integerParser = integerParser;
    this.listParser = listParser;
  }

  @Override
  public Version parseRequired(StringHolder holder) throws ParsingException {
    List<StringHolder> list = listParser.parseRequiredList(holder, ".", true);
    if (list.size() != 3) {
      throw new ParsingException(holder.parent(), "Version must be in semantic format.");
    }
    return new Version(
        integerParser.parseRequired(list.get(0)),
        integerParser.parseRequired(list.get(1)),
        integerParser.parseRequired(list.get(2)));
  }
}
