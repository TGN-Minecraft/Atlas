package net.avicus.atlas.localization;

/**
 * Localizations used across the plugin.
 *
 * @author Austin Mayes
 */
public class GlobalLocalizations {

  public static final GlobalLocalizations INSTANCE;

  static {
    INSTANCE = new GlobalLocalizations();
  }

  private LocaleBundle bundle;

  /**
   * Constructor. Sets the bundle to an empty one.
   */
  public GlobalLocalizations() {
    this.bundle = new LocaleBundle();
  }

  public LocaleBundle getBundle() {
    return bundle;
  }

  public void setBundle(LocaleBundle bundle) {
    this.bundle = bundle;
  }
}
