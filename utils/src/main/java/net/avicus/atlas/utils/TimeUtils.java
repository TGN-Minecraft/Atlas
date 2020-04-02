package net.avicus.atlas.utils;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.impl.ResourcesTimeFormat;
import org.ocpsoft.prettytime.units.JustNow;

public final class TimeUtils {

  public static final Duration INF_POSITIVE = ChronoUnit.FOREVER.getDuration();

  public static PrettyTime prettyTime(Locale locale) {
    final PrettyTime pretty = new PrettyTime(locale);
    // "moments ago" only shows when less than 5 seconds
    pretty.getUnits()
        .stream()
        .filter(unit -> unit instanceof JustNow)
        .forEach(unit -> ((JustNow) unit).setMaxQuantity(10000L));
    return pretty;
  }

  public static PrettyTime removeFutureSuffix(PrettyTime pt) {
    for (TimeUnit unit : pt.getUnits()) {
      ((ResourcesTimeFormat) pt.getFormat(unit)).setFutureSuffix("");
    }
    return pt;
  }

  public static long[] secToMs(final double s1, final double s2, final double s3,
      final double s4, final double s5, final double s6) {
    return new long[]{(long) (s1 * 1000d), (long) (s2 * 1000d), (long) (s3 * 1000d),
        (long) (s4 * 1000d), (long) (s5 * 1000d), (long) (s6 * 1000d)};
  }

  public static long[] secToMs(final double s1) {
    final long v = (long) (s1 * 1000d);
    return new long[]{v, v, v, v, v, v};
  }


  /**
   * Parse a duration, and treat simple numbers with no declaration as pure seconds.
   *
   * @param text to parse
   * @return the parsed duration
   * @throws DateTimeParseException if parsing fails
   */
  public static Duration parseDurationOrSeconds(String text) throws DateTimeParseException {
    if ("oo".equals(text)) {
      return INF_POSITIVE;
    }

    // If text looks like a plain number, try to parse it as seconds,
    // but be fairly strict so we don't accidentally parse a time as
    // a number.
    if (text.matches("^\\s*-?[0-9]+(\\.[0-9]+)?\\s*$")) {
      try {
        return Duration.ofMillis((long) (1000 * Double.parseDouble(text)));
      } catch (NumberFormatException ignored) {
      }
    }

    return parseDuration(text);
  }

  /**
   * See {@link #parseDurationOrSeconds(String)}. If the string is empty, the default argument will
   * be returned.
   *
   * @throws DateTimeParseException if parsing of the text fails
   */
  public static Duration parseDurationOrSeconds(String text, Duration def)
      throws DateTimeParseException {
    if (text == null || text.length() == 0) {
      return def;
    } else {
      return parseDurationOrSeconds(text);
    }
  }

  /**
   * Parse a {@link Duration} from a formatted string, adding on the needed prefix as needed.
   *
   * @param text to parse
   * @return the duration represented by the string
   * @throws DateTimeParseException if parsing fails
   */
  public static Duration parseDuration(CharSequence text) throws DateTimeParseException {
    if (text.length() > 0) {
      switch (text.charAt(0)) {
        case '-':
        case 'P':
        case 'p':
          return Duration.parse(text);
      }
    }

    return Duration.parse("PT" + text);
  }
}
