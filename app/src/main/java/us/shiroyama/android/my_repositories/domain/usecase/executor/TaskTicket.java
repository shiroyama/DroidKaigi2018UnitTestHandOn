package us.shiroyama.android.my_repositories.domain.usecase.executor;

import android.support.annotation.NonNull;

import java.util.concurrent.Future;

/**
 * A Ticket to keep track of or cancel the {@link Task}
 *
 * @author Fumihiko Shiroyama
 */

public class TaskTicket implements Cancellable {
  @NonNull
  private final String taskId;

  @NonNull
  private final Future<?> future;

  public TaskTicket(@NonNull String taskId, @NonNull Future<?> future) {
    this.taskId = taskId;
    this.future = future;
  }

  @NonNull
  public String getTaskId() {
    return taskId;
  }

  @NonNull
  public Future<?> getFuture() {
    return future;
  }

  @Override
  public void cancel(boolean interrupt) {
    if (future.isDone() || future.isCancelled()) {
      return;
    }
    future.cancel(interrupt);
  }
}
