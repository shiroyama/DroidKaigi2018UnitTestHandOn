package us.shiroyama.android.my_repositories.domain.usecase.executor;

/**
 * Cancellable Task
 *
 * @author Fumihiko Shiroyama
 */

public interface Cancellable {
  /**
   * @param interrupt whether interrupt the ongoing thread
   */
  void cancel(boolean interrupt);
}
