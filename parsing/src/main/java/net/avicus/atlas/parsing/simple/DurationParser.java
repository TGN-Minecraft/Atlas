package net.avicus.atlas.parsing.simple;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import net.avicus.atlas.document.ParsingException;
import net.avicus.atlas.document.StringHolder;
import net.avicus.atlas.parsing.SimpleParser;
import net.avicus.atlas.utils.TimeUtils;

/**
 * Parses {@link Duration}s from {@link StringHolder}s.
 *
 * @author Austin Mayes
 */
public class DurationParser implements SimpleParser<Duration> {

  @Override
  public Duration parseRequired(StringHolder holder) throws ParsingException {
    try {
      return TimeUtils.parseDurationOrSeconds(holder.asRequiredString());
    } catch (DateTimeParseException e) {
      throw new ParsingException(holder.parent(), "Invalid date format supplied.");
    }
  }
}
