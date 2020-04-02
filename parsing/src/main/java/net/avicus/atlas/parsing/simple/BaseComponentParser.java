package net.avicus.atlas.parsing.simple;

import net.avicus.atlas.document.ParsingException;
import net.avicus.atlas.document.StringHolder;
import net.avicus.atlas.parsing.SimpleParser;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * Parses plain {@link BaseComponent}s from {@link StringHolder}s.
 *
 * @author Austin Mayes
 */
public class BaseComponentParser implements SimpleParser<BaseComponent> {

  @Override
  public BaseComponent parseRequired(StringHolder holder) throws ParsingException {
    String text = ChatColor.translateAlternateColorCodes('^', holder.asRequiredString());
    return new TextComponent(TextComponent.fromLegacyText(text));
  }
}
