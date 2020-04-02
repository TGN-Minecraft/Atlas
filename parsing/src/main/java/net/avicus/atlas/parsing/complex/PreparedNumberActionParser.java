package net.avicus.atlas.parsing.complex;

import net.avicus.atlas.document.Node;
import net.avicus.atlas.document.ParsingException;
import net.avicus.atlas.parsing.ComplexParser;
import net.avicus.atlas.parsing.SimpleParser;
import net.avicus.atlas.parsing.simple.NumberActionParser;
import net.avicus.atlas.utils.number.NumberAction;
import net.avicus.atlas.utils.number.PreparedNumberAction;

/**
 * Parser for parsing {@link PreparedNumberAction}s. For non pre-defined actions, a {@link
 * NumberActionParser} should be used instead.
 *
 * @author Austin Mayes
 */
public class PreparedNumberActionParser implements ComplexParser<PreparedNumberAction> {

  private final SimpleParser<Number> numberParser;
  private final SimpleParser<NumberAction> numberActionParser;

  /**
   * Constructor
   *
   * @param numberParser used to parse modifier values
   * @param numberActionParser used to parse base actions
   */
  public PreparedNumberActionParser(
      SimpleParser<Number> numberParser, SimpleParser<NumberAction> numberActionParser) {
    this.numberParser = numberParser;
    this.numberActionParser = numberActionParser;
  }

  @Override
  public PreparedNumberAction parse(Node node) throws ParsingException {
    Number num = numberParser.parseRequired(node.text());
    NumberAction action =
        numberActionParser.parse(node.attribute("action")).orElse(NumberAction.SET);
    return new PreparedNumberAction(num, action);
  }
}
