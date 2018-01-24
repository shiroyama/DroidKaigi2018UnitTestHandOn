package us.shiroyama.android.my_repositories.domain.usecase.executor;

import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import us.shiroyama.android.my_repositories.common.Singleton;

/**
 * Task Queue for Asynchronous Tasks
 *
 * @author Fumihiko Shiroyama
 */

public class TaskQueue {
  @NonNull
  private final ExecutorService executorService;

  public TaskQueue(@NonNull ExecutorService executorService) {
    this.executorService = executorService;
  }

  public Future<?> enqueue(@NonNull Task task) {
    return executorService.submit(task);
  }

  public enum Factory implements Singleton<TaskQueue> {
    INSTANCE;

    @NonNull
    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);

    @NonNull
    private final TaskQueue taskQueue = new TaskQueue(executorService);

    @NonNull
    @Override
    public TaskQueue get() {
      return taskQueue;
    }
  }
}
