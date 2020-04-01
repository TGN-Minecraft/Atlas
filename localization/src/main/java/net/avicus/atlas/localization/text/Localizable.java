package net.avicus.atlas.localization.text;

import net.avicus.atlas.localization.TextStyle;
import net.avicus.magnet.api.text.translation.TranslatableComponent;

/**
 * Represents anything that can be translated and sent to players.
 */
public interface Localizable extends TranslatableComponent {

  Localizable[] EMPTY = new Localizable[0];

  /**
   * Get the style of this.
   */
  TextStyle style();

  /**
   * Copy this and its styles.
   */
  Localizable duplicate();
}
