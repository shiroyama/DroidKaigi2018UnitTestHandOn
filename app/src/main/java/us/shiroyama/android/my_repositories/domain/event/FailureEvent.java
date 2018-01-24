package us.shiroyama.android.my_repositories.domain.event;

import android.support.annotation.NonNull;

/**
 * Base Failure Event
 *
 * @author Fumihiko Shiroyama
 */

public abstract class FailureEvent<T extends Exception> extends Event<T> {
  public FailureEvent(@NonNull T item) {
    super(item);
  }

  public T getError() {
    return getItem();
  }
}
