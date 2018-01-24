package us.shiroyama.android.my_repositories.infrastructure.repository;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.List;

import us.shiroyama.android.my_repositories.infrastructure.entity.RepositoryEntity;
import us.shiroyama.android.my_repositories.infrastructure.exception.ApiException;

/**
 * Repository Interface for GitHub
 *
 * @author Fumihiko Shiroyama
 */

public interface GitHubRepository {
  @NonNull
  List<RepositoryEntity> getUserRepositories(@NonNull String account) throws IOException, ApiException;

  List<RepositoryEntity> refreshUserRepositories(@NonNull String account) throws IOException, ApiException;
}
