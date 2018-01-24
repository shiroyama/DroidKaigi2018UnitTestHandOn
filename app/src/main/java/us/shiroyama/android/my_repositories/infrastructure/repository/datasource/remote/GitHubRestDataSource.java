package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.remote;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import us.shiroyama.android.my_repositories.infrastructure.entity.RepositoryEntity;
import us.shiroyama.android.my_repositories.infrastructure.exception.ApiException;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.remote.service.GitHubService;

/**
 * Remote Data Source for GitHub
 *
 * @author Fumihiko Shiroyama
 */

public class GitHubRestDataSource extends BaseRestDataSource<List<RepositoryEntity>> implements GitHubRemoteDataSource {
  @NonNull
  private final GitHubService gitHubService;

  public GitHubRestDataSource(@NonNull Gson gson, @NonNull GitHubService gitHubService) {
    super(gson);
    this.gitHubService = gitHubService;
  }

  @NonNull
  @Override
  public List<RepositoryEntity> getUserRepositories(@NonNull String account) throws IOException, ApiException {
    Response<List<RepositoryEntity>> response = gitHubService.getUserRepositories(
        account,
        Sort.UPDATED.value,
        Direction.DESC.value
    ).execute();
    return stripResult(response);
  }

  private enum Sort {
    CREATED("created"),
    UPDATED("updated"),
    PUSHED("pushed"),
    FULL_NAME("full_name");

    @NonNull
    private final String value;

    Sort(@NonNull String value) {
      this.value = value;
    }
  }

  private enum Direction {
    ASC("asc"),
    DESC("desc");

    @NonNull
    private final String value;

    Direction(@NonNull String value) {
      this.value = value;
    }
  }
}
