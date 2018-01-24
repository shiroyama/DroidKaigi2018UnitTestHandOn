package us.shiroyama.android.my_repositories.common;

import android.support.annotation.NonNull;

/**
 * Singleton Factory Interface
 *
 * @author Fumihiko Shiroyama
 */

public interface Singleton<T> {
  @NonNull
  T get();
}
