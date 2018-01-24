package us.shiroyama.android.my_repositories.domain.event;

import android.support.annotation.NonNull;

/**
 * Base Event
 *
 * @author Fumihiko Shiroyama
 */

abstract class Event<T> {
  @NonNull
  private final T item;

  Event(@NonNull T item) {
    this.item = item;
  }

  @NonNull
  public T getItem() {
    return item;
  }
}
