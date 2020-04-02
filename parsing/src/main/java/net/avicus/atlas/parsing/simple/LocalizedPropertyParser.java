package net.avicus.atlas.parsing.simple;

import com.google.common.base.Splitter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import net.avicus.atlas.document.ParsingException;
import net.avicus.atlas.document.StringHolder;
import net.avicus.atlas.localization.LocaleBundle;
import net.avicus.atlas.localization.LocaleStrings;
import net.avicus.atlas.localization.LocalizedConfigurationProperty;
import net.avicus.atlas.localization.text.Localizable;
import net.avicus.atlas.localization.text.LocalizedText;
import net.avicus.atlas.parsing.SimpleParser;

/**
 * Parser which has the responsibility of creating {@link LocalizedConfigurationProperty localized
 * configuration properties} from {@link StringHolder}s.
 *
 * @author Austin Mayes
 */
public class LocalizedPropertyParser implements SimpleParser<LocalizedConfigurationProperty> {

  private final @Nullable
  LocaleBundle bundle;

  /**
   * Constructor.
   *
   * @param bundle to use for message retrieval
   */
  public LocalizedPropertyParser(@Nullable LocaleBundle bundle) {
    this.bundle = bundle;
  }

  @Override
  public LocalizedConfigurationProperty parseRequired(StringHolder holder) throws ParsingException {
    if (this.bundle == null) {
      return new LocalizedConfigurationProperty(holder.asRequiredString());
    }

    String text = holder.asRequiredString();

    List<Localizable> arguments = new ArrayList<>();

    // Matches anything within { }.
    Pattern pattern = Pattern.compile("\\{([^}]*)\\}");
    Matcher matcher = pattern.matcher(text);

    int argNum = 0;

    while (matcher.find()) {
      // Grabs the content within each { }.
      String raw = matcher.group(1);

      // Separate localized ids by comma
      List<String> split = Splitter.on(",").splitToList(raw);

      // The first is the key
      String key = split.get(0);
      List<Localizable> keyArgs = new ArrayList<>();

      // The others are arguments that go into the key
      for (int i = 1; i < split.size(); i++) {
        keyArgs.add(new LocalizedText(this.bundle, split.get(i)));
      }

      // Add and move onto the next one
      arguments.add(new LocalizedText(this.bundle, key, keyArgs));
      text = text.replace(raw, argNum + "");
      argNum++;
    }

    return new LocalizedConfigurationProperty(LocaleStrings.addColors(text), arguments);
  }
}
