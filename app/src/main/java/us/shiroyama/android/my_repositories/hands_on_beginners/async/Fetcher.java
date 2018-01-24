package us.shiroyama.android.my_repositories.hands_on_beginners.async;

/**
 * Fetcher for {@link T}
 *
 * @author Fumihiko Shiroyama
 */

interface Fetcher<T> {
  T fetch();

  class StringFetcher implements Fetcher<String> {
    @Override
    public String fetch() {
      return "OK";
    }
  }
}
