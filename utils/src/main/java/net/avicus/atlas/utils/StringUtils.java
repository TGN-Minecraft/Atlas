package net.avicus.atlas.utils;


import java.text.Normalizer;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.meta.ItemMeta;
import org.joda.time.Duration;
import org.joda.time.Seconds;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public final class StringUtils {

  /**
   * A pattern for non-latin characters.
   */
  private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
  /**
   * A pattern for whitespace characters.
   */
  private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
  /**
   * The string non-latin characters are replaced with.
   */
  private static final String REPLACEMENT = ""; // Empty space!

  private static PeriodFormatter periodFormatter = new PeriodFormatterBuilder()
      .appendDays().appendSuffix("d")
      .appendHours().appendSuffix("h")
      .appendMinutes().appendSuffix("m")
      .appendSeconds().appendSuffix("s")
      .appendSeconds()
      .toFormatter();

  public static Duration toDuration(String format) {
    String text = format.toLowerCase().replace(" ", "");

    if (text.equals("oo")) {
      return Seconds.MAX_VALUE.toStandardDuration();
    } else if (text.equals("-oo")) {
      return Seconds.MIN_VALUE.toStandardDuration();
    }

    return periodFormatter.parsePeriod(text).toStandardDuration();
  }

  /**
   * Wrap a long {@link ItemMeta} lore line and append it to a list of lore with last colors copied
   * over.
   *
   * @param lore the result list
   * @param length the length to wrap on
   * @param string the string to wrap
   */
  public static void wrapLoreCopyColors(final List<String> lore, final int length,
      final String string) {
    String previous = "";
    for (String wrapped : WordUtils.wrap(string, length).split(SystemUtils.LINE_SEPARATOR)) {
      if (!previous.isEmpty()) {
        previous = ChatColor.getLastColors(previous);
      }

      lore.add(previous + wrapped);
      previous = wrapped;
    }
  }

  /**
   * Transform a string into a slugged string.
   *
   * @param string the original string
   * @return the slugged string
   */
  public static String slugify(String string) {
    return NON_LATIN.matcher(
        Normalizer.normalize(WHITESPACE.matcher(string).replaceAll("-"), Normalizer.Form.NFD))
        .replaceAll(REPLACEMENT)
        .toLowerCase(Locale.ENGLISH);
  }

  /**
   * Converts a list of strings to make a nice English list as a string.
   *
   * @param list List of strings to compound.
   * @param prefix Prefix to add before each element in the resulting string.
   * @param suffix Suffix to add after each element in the resulting string.
   * @return String version of the list of strings.
   */
  public static String listToEnglishCompound(Collection<?> list, String prefix, String suffix) {
    StringBuilder builder = new StringBuilder();
    int i = 0;
    for (Object str : list) {
      if (i != 0) {
        if (i == list.size() - 1) {
          if (list.size() > 2) {
            builder.append(", and ");
          } else {
            builder.append(" and ");
          }
        } else {
          builder.append(", ");
        }
      }

      builder.append(prefix).append(str).append(suffix);
      i++;
    }

    return builder.toString();
  }

  /**
   * Shorthand for listToEnglishCompound(list, "", "")
   *
   * @param list List of strings to compound.
   * @return String version of the list of strings.
   */
  public static String listToEnglishCompound(Collection<String> list) {
    return listToEnglishCompound(list, "", "");
  }

  public static String toPercent(double proportion) {
    int percent = (int) Math.floor(proportion * 100.0);
    return percent + "%";
  }

  public static String secondsToClock(int seconds) {
    int hours = seconds / 3600;
    int minutes = (seconds % 3600) / 60;
    int secs = seconds % 60;

    if (hours == 0) {
      return String.format("%02d:%02d", minutes, secs);
    }

    return String.format("%02d:%02d:%02d", hours, minutes, secs);
  }

  public static <T> String join(List<T> parts, String delimeter, Stringify<T> stringify) {
    String text = "";
    for (T part : parts) {
      text += stringify.on(part);
      if (parts.indexOf(part) != parts.size() - 1) {
        text += delimeter;
      }
    }
    return text;
  }

  public interface Stringify<T> {

    String on(T object);
  }
}
