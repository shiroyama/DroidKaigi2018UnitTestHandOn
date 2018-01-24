package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;

import java.util.List;

import us.shiroyama.android.my_repositories.infrastructure.entity.RepositoryEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.db.AppDatabase;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RepoWithAccount;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomAccountEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomRepositoryEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.mapper.RepositoryEntityMapper;

/**
 * Local Data Source for GitHub using Room
 *
 * @author Fumihiko Shiroyama
 */

public class GitHubRoomDataSource implements GitHubLocalDataSource {
  @NonNull
  private final AppDatabase db;

  @NonNull
  private final RepositoryEntityMapper repositoryEntityMapper;

  public GitHubRoomDataSource(@NonNull AppDatabase db, @NonNull RepositoryEntityMapper repositoryEntityMapper) {
    this.db = db;
    this.repositoryEntityMapper = repositoryEntityMapper;
  }

  @Override
  public List<RepositoryEntity> getUserRepositories(@NonNull String account) {
    List<RepoWithAccount> repoWithAccountList = db.repositoryDao().findByAccount(account);
    return repositoryEntityMapper.convertList(repoWithAccountList);
  }

  @Override
  public void insertRepositoriesAndAccounts(@NonNull List<RoomRepositoryEntity> repositories, @NonNull List<RoomAccountEntity> accounts) {
    db.repositoryDao().insertRepositoriesAndAccounts(repositories, Stream.of(accounts).distinct().toList());
  }

  @Override
  public void deleteAndInsertRepositoriesAndAccounts(@NonNull String account, @NonNull List<RoomRepositoryEntity> repositories, @NonNull List<RoomAccountEntity> accounts) {
    db.repositoryDao().deleteAndInsertRepositoriesAndAccounts(account, repositories, Stream.of(accounts).distinct().toList());
  }
}
