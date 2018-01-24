package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.remote;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.List;

import us.shiroyama.android.my_repositories.infrastructure.entity.RepositoryEntity;
import us.shiroyama.android.my_repositories.infrastructure.exception.ApiException;

/**
 * Remote Data Source Interface for GitHub
 *
 * @author Fumihiko Shiroyama
 */

public interface GitHubRemoteDataSource {
  @NonNull
  List<RepositoryEntity> getUserRepositories(@NonNull String account) throws IOException, ApiException;
}
