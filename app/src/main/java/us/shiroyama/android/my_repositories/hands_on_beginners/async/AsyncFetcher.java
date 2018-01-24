package us.shiroyama.android.my_repositories.hands_on_beginners.async;

/**
 * Asynchronous Fetcher for {@link T}
 *
 * @author Fumihiko Shiroyama
 */

class AsyncFetcher<T> {
  private final Fetcher<T> fetcher;

  AsyncFetcher(Fetcher<T> fetcher) {
    this.fetcher = fetcher;
  }

  void fetch(OnSuccess<T> onSuccess, OnFailure onFailure) {
    new Thread(() -> {
      try {
        Thread.sleep(1000L); // time intensive task
        onSuccess.onSuccess(fetcher.fetch());
      } catch (Exception e) {
        onFailure.onFailure(e);
      }
    }).start();
  }

  /**
   * Success Callback
   */
  interface OnSuccess<T> {
    void onSuccess(T result);
  }

  /**
   * Failure Callback
   */
  interface OnFailure {
    void onFailure(Exception e);
  }
}
