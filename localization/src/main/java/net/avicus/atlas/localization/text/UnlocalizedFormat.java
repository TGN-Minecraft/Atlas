package net.avicus.atlas.localization.text;

import net.avicus.atlas.localization.TextStyle;

/**
 * Represents a format that takes arguments for localization with {@link UnlocalizedText}s.
 */
public class UnlocalizedFormat implements LocalizableFormat<UnlocalizedText> {

  private final String text;

  public UnlocalizedFormat(String text) {
    this.text = text;
  }

  @Override
  public UnlocalizedText with(TextStyle style, Localizable... arguments) {
    return new UnlocalizedText(this.text, style, arguments);
  }

  @Override
  public UnlocalizedText with(Localizable... arguments) {
    return new UnlocalizedText(this.text, arguments);
  }
}
