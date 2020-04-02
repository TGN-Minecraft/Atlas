package net.avicus.atlas.parsing.simple.number;

import net.avicus.atlas.document.ParsingException;
import net.avicus.atlas.document.StringHolder;
import net.avicus.atlas.parsing.SimpleParser;
import net.avicus.atlas.parsing.simple.NumberFactory;

/**
 * Parser used to parse instances of a number {@link T} from {@link StringHolder}s.
 *
 * @param <T> type of number being parsed
 * @author Austin Mayes
 */
public class NumberParser<T extends Number> implements SimpleParser<T> {

  private final NumberFactory<T> factory;

  /**
   * Constructor.
   *
   * @param factory used to parse the specific number type
   */
  public NumberParser(NumberFactory<T> factory) {
    this.factory = factory;
  }

  @Override
  public T parseRequired(StringHolder holder) throws ParsingException {
    try {
      return factory.parse(holder.asRequiredString());
    } catch (NumberFormatException e) {
      throw new ParsingException(holder.parent(), e.getMessage());
    }
  }
}
