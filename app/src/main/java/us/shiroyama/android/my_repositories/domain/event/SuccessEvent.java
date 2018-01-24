package us.shiroyama.android.my_repositories.domain.event;

import android.support.annotation.NonNull;

/**
 * Base Success Event
 *
 * @author Fumihiko Shiroyama
 */

public abstract class SuccessEvent<T> extends Event<T> {
  public SuccessEvent(@NonNull T item) {
    super(item);
  }
}
