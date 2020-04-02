package net.avicus.atlas.parsing.simple;

import net.avicus.atlas.document.ParsingException;
import net.avicus.atlas.document.StringHolder;
import net.avicus.atlas.parsing.SimpleParser;
import net.avicus.atlas.utils.number.NumberAction;

/**
 * Parses {@link NumberAction}s from {@link StringHolder}s.
 *
 * @author Avicus Network
 */
public class NumberActionParser implements SimpleParser<NumberAction> {

  @Override
  public NumberAction parseRequired(StringHolder holder) throws ParsingException {
    String text = holder.asRequiredString().toLowerCase().replace(" ", "");

    switch (text) {
      case "none":
        return NumberAction.NONE;
      case "set":
        return NumberAction.SET;
      case "add":
        return NumberAction.ADD;
      case "subtract":
        return NumberAction.SUBTRACT;
      case "multiply":
        return NumberAction.MULTIPLY;
      case "divide":
        return NumberAction.DIVIDE;
      case "power":
        return NumberAction.POWER;
    }

    throw new ParsingException(holder.parent(), "Invalid number action.");
  }
}
