package net.avicus.atlas.parsing.simple;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import net.avicus.atlas.document.ParsingException;
import net.avicus.atlas.document.StringHolder;
import net.avicus.atlas.parsing.SimpleParser;

/**
 * Parse a standard {@link URL} from a {@link StringHolder}.
 *
 * <p>URLs must be in line RFC2396 in order to be valid. See https://www.ietf.org/rfc/rfc2396.txt
 * for more details.
 *
 * @author Austin Mayes
 */
public class URLParser implements SimpleParser<URL> {

  @Override
  public URL parseRequired(StringHolder holder) throws ParsingException {
    try {
      URL url = new URL(holder.asRequiredString());
      // more validation
      url.toURI();
      return url;
    } catch (MalformedURLException e) {
      throw new ParsingException(holder.parent(), "Invalid URL.");
    } catch (URISyntaxException e) {
      throw new ParsingException(holder.parent(), "Invalid URI.");
    }
  }
}
