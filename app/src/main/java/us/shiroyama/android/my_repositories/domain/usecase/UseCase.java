package us.shiroyama.android.my_repositories.domain.usecase;

import android.support.annotation.NonNull;

import com.squareup.otto.Bus;

import java.util.concurrent.Future;

import us.shiroyama.android.my_repositories.domain.usecase.executor.Task;
import us.shiroyama.android.my_repositories.domain.usecase.executor.TaskQueue;
import us.shiroyama.android.my_repositories.domain.usecase.executor.TaskTicket;

/**
 * Base UseCase
 *
 * @author Fumihiko Shiroyama
 */

public abstract class UseCase<PARAM> {
  @NonNull
  private final TaskQueue taskQueue;

  @NonNull
  protected final Bus bus;

  protected UseCase(@NonNull TaskQueue taskQueue, @NonNull Bus bus) {
    this.taskQueue = taskQueue;
    this.bus = bus;
  }

  @NonNull
  public TaskTicket enqueue(PARAM param) {
    Task task = buildTask(param);
    Future<?> future = taskQueue.enqueue(task);
    return new TaskTicket(task.getId(), future);
  }

  protected abstract Task buildTask(PARAM param);
}
