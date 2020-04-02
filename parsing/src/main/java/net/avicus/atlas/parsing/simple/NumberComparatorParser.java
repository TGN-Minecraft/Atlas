package net.avicus.atlas.parsing.simple;

import net.avicus.atlas.document.ParsingException;
import net.avicus.atlas.document.StringHolder;
import net.avicus.atlas.parsing.SimpleParser;
import net.avicus.atlas.utils.number.NumberComparator;

/**
 * Parses {@link NumberComparator}s from {@link StringHolder}s.
 *
 * @author Avicus Network
 */
public class NumberComparatorParser implements SimpleParser<NumberComparator> {

  @Override
  public NumberComparator parseRequired(StringHolder holder) throws ParsingException {
    String text = holder.asRequiredString().toLowerCase().replace(" ", "");

    if (text.equals("equals")) {
      return NumberComparator.EQUALS;
    } else if (text.equals("less_than") || text.equals("less")) {
      return NumberComparator.LESS_THAN;
    } else if (text.equals("less_than_equal")) {
      return NumberComparator.LESS_THAN_EQUAL;
    } else if (text.equals("greater_than") || text.equals("greater")) {
      return NumberComparator.GREATER_THAN;
    } else if (text.equals("greater_than_equal")) {
      return NumberComparator.GREATER_THAN_EQUAL;
    } else if (text.startsWith("mod")) {
      String mod = text.replace("_", "").replace("mod", "");
      int modValue = Integer.parseInt(mod);
      return NumberComparator.MODULO(modValue);
    }

    throw new ParsingException(holder.parent(), "Invalid number comparator.");
  }
}
