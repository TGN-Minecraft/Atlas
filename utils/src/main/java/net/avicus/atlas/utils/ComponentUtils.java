package net.avicus.atlas.utils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.util.ChatPaginator;

public final class ComponentUtils {

  public static BaseComponent copyStyle(BaseComponent source, BaseComponent destination) {
    destination.setColor(source.getColorRaw());
    destination.setBold(source.isBoldRaw());
    destination.setItalic(source.isItalicRaw());
    destination.setUnderlined(source.isUnderlinedRaw());
    destination.setStrikethrough(source.isStrikethroughRaw());
    destination.setObfuscated(source.isObfuscatedRaw());
    destination.setInsertion(source.getInsertion());
    destination.setClickEvent(source.getClickEvent());
    destination.setHoverEvent(source.getHoverEvent());

    return destination;
  }

  public static String padChatMessage(BaseComponent message, String padChar, ChatColor padColor,
      ChatColor messageColor) {
    return padChatMessage(message.toPlainText(), padChar, padColor, messageColor);
  }

  public static BaseComponent padChatComponent(BaseComponent message, String padChar,
      ChatColor padColor, ChatColor messageColor) {
    return new TextComponent(
        padChatMessage(message.toPlainText(), padChar, padColor, messageColor));
  }

  public static BaseComponent padTextComponent(BaseComponent message, String padChar,
      ChatColor padColor, ChatColor messageColor) {
    return padTextComponent(message, padChar, padColor.toString(), messageColor);
  }

  public static BaseComponent padTextComponent(BaseComponent message, String padChar,
      String padColor, ChatColor messageColor) {
    final String pad = paddingFor(message.toPlainText(), padChar);
    final TextComponent component = new TextComponent(padColor + pad + ChatColor.RESET);
    BaseComponent copy = copyStyle(message, new TextComponent(' ' + message.toPlainText() + ' '));
    copy.setColor(messageColor.asBungee());
    component.addExtra(copy);
    component.addExtra(ChatColor.RESET.toString() + padColor + pad);
    return component;
  }

  public static String padChatMessage(String message, String padChar, ChatColor padColor,
      ChatColor messageColor) {
    message = " " + message + " ";
    String pad = paddingFor(message, padChar);
    return padColor + pad + ChatColor.RESET + messageColor + message + ChatColor.RESET + padColor
        + pad;
  }

  private static String paddingFor(String text, String padChar) {
    return com.google.common.base.Strings.repeat(padChar,
        (ChatPaginator.GUARANTEED_NO_WRAP_CHAT_PAGE_WIDTH - ChatColor.stripColor(text).length() - 2)
            / (padChar.length() * 2));
  }

  public static BaseComponent blankLine(ChatColor color) {
    TextComponent line = new TextComponent(com.google.common.base.Strings
        .repeat(" ", (ChatPaginator.GUARANTEED_NO_WRAP_CHAT_PAGE_WIDTH) + 4));
    line.setStrikethrough(true);
    line.setColor(color.asBungee());
    return line;
  }
}
