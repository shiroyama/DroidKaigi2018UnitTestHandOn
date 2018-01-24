package us.shiroyama.android.my_repositories.infrastructure.repository;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import us.shiroyama.android.my_repositories.common.GsonProvider;
import us.shiroyama.android.my_repositories.infrastructure.entity.AccountEntity;
import us.shiroyama.android.my_repositories.infrastructure.entity.RepositoryEntity;
import us.shiroyama.android.my_repositories.infrastructure.exception.ApiException;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.GitHubLocalDataSource;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.GitHubRoomDataSource;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.db.DatabaseProvider;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomAccountEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomRepositoryEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.mapper.RepositoryEntityMapper;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.remote.GitHubRemoteDataSource;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.remote.GitHubRestDataSource;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.remote.service.GitHubService;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.remote.service.RetrofitProvider;

/**
 * Repository for GitHub
 *
 * @author Fumihiko Shiroyama
 */

public class GitHubInfraRepository implements GitHubRepository {
  @NonNull
  private final GitHubRemoteDataSource remoteDataSource;

  @NonNull
  private final GitHubLocalDataSource localDataSource;

  public GitHubInfraRepository(@NonNull GitHubRemoteDataSource remoteDataSource, @NonNull GitHubLocalDataSource localDataSource) {
    this.remoteDataSource = remoteDataSource;
    this.localDataSource = localDataSource;
  }

  @NonNull
  @Override
  public List<RepositoryEntity> getUserRepositories(@NonNull String account) throws IOException, ApiException {
    List<RepositoryEntity> localRepositories = localDataSource.getUserRepositories(account);
    if (!localRepositories.isEmpty()) {
      return localRepositories;
    }
    List<RepositoryEntity> remoteRepositories = remoteDataSource.getUserRepositories(account);
    if (remoteRepositories.isEmpty()) {
      return remoteRepositories;
    }
    List<RoomRepositoryEntity> roomRepositoryEntities = new ArrayList<>(remoteRepositories.size());
    List<RoomAccountEntity> roomAccountEntities = new ArrayList<>(remoteRepositories.size());
    for (RepositoryEntity remoteRepository : remoteRepositories) {
      roomRepositoryEntities.add(entityToRoom(remoteRepository));
      roomAccountEntities.add(entityToRoom(remoteRepository.getOwner()));
    }
    localDataSource.insertRepositoriesAndAccounts(roomRepositoryEntities, roomAccountEntities);
    return remoteRepositories;
  }

  @Override
  public List<RepositoryEntity> refreshUserRepositories(@NonNull String account) throws IOException, ApiException {
    List<RepositoryEntity> remoteRepositories = remoteDataSource.getUserRepositories(account);
    if (remoteRepositories.isEmpty()) {
      return remoteRepositories;
    }
    List<RoomRepositoryEntity> roomRepositoryEntities = new ArrayList<>(remoteRepositories.size());
    List<RoomAccountEntity> roomAccountEntities = new ArrayList<>(remoteRepositories.size());
    for (RepositoryEntity remoteRepository : remoteRepositories) {
      roomRepositoryEntities.add(entityToRoom(remoteRepository));
      roomAccountEntities.add(entityToRoom(remoteRepository.getOwner()));
    }
    localDataSource.deleteAndInsertRepositoriesAndAccounts(account, roomRepositoryEntities, roomAccountEntities);
    return remoteRepositories;
  }

  private RoomRepositoryEntity entityToRoom(RepositoryEntity from) {
    return new RoomRepositoryEntity(
        from.getId(),
        from.getName(),
        from.getFullName(),
        from.getHtmlUrl(),
        from.isPrivate(),
        from.getDescription(),
        from.getOwner().getId(),
        from.getCreatedAt(),
        from.getUpdatedAt(),
        from.getPushedAt()
    );
  }

  private RoomAccountEntity entityToRoom(AccountEntity from) {
    return new RoomAccountEntity(
        from.getId(),
        from.getLogin(),
        from.getAvatarUrl(),
        from.getUrl()
    );
  }

  public static class Factory {
    private static GitHubRepository instance;

    @NonNull
    public static synchronized GitHubRepository get(@NonNull Context context) {
      if (instance == null) {
        GitHubRemoteDataSource remote = new GitHubRestDataSource(
            GsonProvider.INSTANCE.get(),
            RetrofitProvider.INSTANCE.get().create(GitHubService.class)
        );
        GitHubLocalDataSource local = new GitHubRoomDataSource(
            DatabaseProvider.Factory.getInstance(context),
            RepositoryEntityMapper.Factory.INSTANCE.get()
        );
        instance = new GitHubInfraRepository(remote, local);
      }
      return instance;
    }
  }
}
