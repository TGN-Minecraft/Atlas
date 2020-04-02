package net.avicus.atlas.parsing.simple;

import net.avicus.atlas.document.ParsingException;
import net.avicus.atlas.document.StringHolder;
import net.avicus.atlas.parsing.SimpleParser;

/**
 * Parses {@link Boolean}s from {@link StringHolder}s.
 *
 * @author Austin Mayes
 */
public class BooleanParser implements SimpleParser<Boolean> {

  @Override
  public Boolean parseRequired(StringHolder holder) throws ParsingException {
    String text = holder.asRequiredString().toLowerCase();

    switch (text) {
      case "true":
      case "yes":
      case "allow":
      case "on":
        return true;
      case "false":
      case "no":
      case "deny":
      case "off":
        return false;
      default:
        throw new ParsingException(holder.parent(), "Invalid boolean.");
    }
  }
}
