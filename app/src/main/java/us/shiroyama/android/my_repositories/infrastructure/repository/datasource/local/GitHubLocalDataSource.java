package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local;

import android.support.annotation.NonNull;

import java.util.List;

import us.shiroyama.android.my_repositories.infrastructure.entity.RepositoryEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomAccountEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomRepositoryEntity;

/**
 * Local Data Source Interface for GitHub
 *
 * @author Fumihiko Shiroyama
 */

public interface GitHubLocalDataSource {
  List<RepositoryEntity> getUserRepositories(@NonNull String account);

  void insertRepositoriesAndAccounts(@NonNull List<RoomRepositoryEntity> repositories, @NonNull List<RoomAccountEntity> accounts);

  void deleteAndInsertRepositoriesAndAccounts(@NonNull String account, @NonNull List<RoomRepositoryEntity> repositories, @NonNull List<RoomAccountEntity> accounts);
}
