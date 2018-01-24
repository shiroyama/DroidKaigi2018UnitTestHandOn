package us.shiroyama.android.my_repositories.domain.usecase;

import android.content.Context;
import android.support.annotation.NonNull;

import com.squareup.otto.Bus;

import java.util.List;

import us.shiroyama.android.my_repositories.common.BusProvider;
import us.shiroyama.android.my_repositories.domain.event.FailureEvent;
import us.shiroyama.android.my_repositories.domain.event.SuccessEvent;
import us.shiroyama.android.my_repositories.domain.mapper.RepositoryMapper;
import us.shiroyama.android.my_repositories.domain.model.Repository;
import us.shiroyama.android.my_repositories.domain.usecase.executor.Task;
import us.shiroyama.android.my_repositories.domain.usecase.executor.TaskQueue;
import us.shiroyama.android.my_repositories.infrastructure.entity.GitHubErrorEntity;
import us.shiroyama.android.my_repositories.infrastructure.entity.RepositoryEntity;
import us.shiroyama.android.my_repositories.infrastructure.exception.ApiException;
import us.shiroyama.android.my_repositories.infrastructure.repository.GitHubInfraRepository;
import us.shiroyama.android.my_repositories.infrastructure.repository.GitHubRepository;

/**
 * UseCase to get {@link Repository}
 *
 * @author Fumihiko Shiroyama
 */

public class GetRepositories extends UseCase<GetRepositories.Param> {
  @NonNull
  private final GitHubRepository gitHubRepository;

  @NonNull
  private final RepositoryMapper repositoryMapper;

  public GetRepositories(@NonNull TaskQueue taskQueue, @NonNull Bus bus, @NonNull GitHubRepository gitHubRepository, @NonNull RepositoryMapper repositoryMapper) {
    super(taskQueue, bus);
    this.gitHubRepository = gitHubRepository;
    this.repositoryMapper = repositoryMapper;
  }

  @Override
  protected Task buildTask(Param param) {
    return new Task() {
      @Override
      public void run() {
        try {
          List<RepositoryEntity> entities = param.refresh
              ? gitHubRepository.refreshUserRepositories(param.account)
              : gitHubRepository.getUserRepositories(param.account);
          List<Repository> repositories = repositoryMapper.convertList(entities);
          bus.post(new OnSuccessGetRepositories(repositories));
        } catch (ApiException e) {
          GitHubErrorEntity gitHubErrorEntity = e.getGitHubErrorEntity();
          String message = gitHubErrorEntity == null
              ? e.getMessage()
              : gitHubErrorEntity.getMessage();
          bus.post(new OnFailureGetRepositories(new Exception(message)));
        } catch (Exception e) {
          bus.post(new OnFailureGetRepositories(e));
        }
      }
    };
  }

  /**
   * Singleton Factory
   */
  public static class Factory {
    private static GetRepositories instance;

    @NonNull
    public static synchronized GetRepositories get(@NonNull Context context) {
      if (instance == null) {
        instance = new GetRepositories(
            TaskQueue.Factory.INSTANCE.get(),
            BusProvider.INSTANCE.get(),
            GitHubInfraRepository.Factory.get(context),
            RepositoryMapper.Factory.INSTANCE.get()
        );
      }
      return instance;
    }
  }

  /**
   * Param
   */
  public static class Param {
    @NonNull
    private final String account;

    private final boolean refresh;

    private Param(@NonNull String account, boolean refresh) {
      this.account = account;
      this.refresh = refresh;
    }

    @NonNull
    public static Param newInstance(@NonNull String account, boolean refresh) {
      return new Param(account, refresh);
    }

    @NonNull
    public static Param newInstance(@NonNull String account) {
      return new Param(account, false);
    }
  }

  /**
   * Success Event
   */
  public static class OnSuccessGetRepositories extends SuccessEvent<List<Repository>> {
    public OnSuccessGetRepositories(@NonNull List<Repository> item) {
      super(item);
    }
  }

  /**
   * Failure Event
   */
  public static class OnFailureGetRepositories extends FailureEvent<Exception> {
    public OnFailureGetRepositories(@NonNull Exception item) {
      super(item);
    }
  }
}
