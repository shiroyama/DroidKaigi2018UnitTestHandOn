package us.shiroyama.android.my_repositories.domain.usecase.executor;

import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Each Task in {@link TaskQueue}
 *
 * @author Fumihiko Shiroyama
 */

public abstract class Task implements Runnable {
  @NonNull
  private final String id;

  public Task() {
    this.id = UUID.randomUUID().toString();
  }

  @NonNull
  public String getId() {
    return id;
  }
}
