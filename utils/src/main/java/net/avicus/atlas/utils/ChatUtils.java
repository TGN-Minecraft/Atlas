package net.avicus.atlas.utils;

import com.google.common.base.Preconditions;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

public class ChatUtils {

  public static String addColors(String message) {
    if (message == null) {
      return null;
    }
    return ChatColor.translateAlternateColorCodes('^', message);
  }

  public static String removeColors(String message) {
    return message == null ? null : ChatColor.stripColor(message);
  }

  public static ChatColor toChatColor(DyeColor color) {
    switch (color) {
      case WHITE:
        return ChatColor.WHITE;
      case ORANGE:
        return ChatColor.GOLD;
      case MAGENTA:
        return ChatColor.LIGHT_PURPLE;
      case LIGHT_BLUE:
        return ChatColor.BLUE;
      case YELLOW:
        return ChatColor.YELLOW;
      case LIME:
        return ChatColor.GREEN;
      case PINK:
        return ChatColor.LIGHT_PURPLE;
      case GRAY:
        return ChatColor.DARK_GRAY;
      case SILVER:
        return ChatColor.GRAY;
      case CYAN:
        return ChatColor.DARK_AQUA;
      case PURPLE:
        return ChatColor.DARK_PURPLE;
      case BLUE:
        return ChatColor.BLUE;
      case BROWN:
        return ChatColor.GOLD;
      case GREEN:
        return ChatColor.DARK_GREEN;
      case RED:
        return ChatColor.DARK_RED;
      case BLACK:
        return ChatColor.GRAY;
      default:
        throw new IllegalArgumentException();
    }
  }

  public static ChatColor toChatColor(double proportion) {
    Preconditions.checkArgument(proportion >= 0 && proportion <= 1);
    if (proportion <= 0.15) {
      return ChatColor.RED;
    } else if (proportion <= 0.5) {
      return ChatColor.YELLOW;
    } else {
      return ChatColor.GREEN;
    }
  }
}
