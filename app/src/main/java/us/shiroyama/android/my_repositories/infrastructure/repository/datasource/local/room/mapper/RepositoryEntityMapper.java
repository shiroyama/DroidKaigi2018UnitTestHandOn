package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.mapper;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;

import java.util.List;

import us.shiroyama.android.my_repositories.common.Singleton;
import us.shiroyama.android.my_repositories.infrastructure.entity.RepositoryEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RepoWithAccount;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomAccountEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomRepositoryEntity;

/**
 * Mapper from {@link RoomRepositoryEntity} to {@link RepositoryEntity}
 *
 * @author Fumihiko Shiroyama
 */

public class RepositoryEntityMapper {
  @NonNull
  private final AccountEntityMapper accountEntityMapper;

  public RepositoryEntityMapper(@NonNull AccountEntityMapper accountEntityMapper) {
    this.accountEntityMapper = accountEntityMapper;
  }

  @NonNull
  public RepositoryEntity convert(@NonNull RoomRepositoryEntity roomRepositoryEntity, @NonNull RoomAccountEntity roomAccountEntity) {
    return new RepositoryEntity(
        roomRepositoryEntity.getId(),
        roomRepositoryEntity.getName(),
        roomRepositoryEntity.getFullName(),
        roomRepositoryEntity.getHtmlUrl(),
        roomRepositoryEntity.isPrivate(),
        roomRepositoryEntity.getDescription(),
        accountEntityMapper.convert(roomAccountEntity),
        roomRepositoryEntity.getCreatedAt(),
        roomRepositoryEntity.getUpdatedAt(),
        roomRepositoryEntity.getPushedAt()
    );
  }

  @NonNull
  public List<RepositoryEntity> convertList(@NonNull List<RepoWithAccount> repoWithAccountList) {
    return Stream.of(repoWithAccountList)
        .map(repoWithAccount -> {
          RoomRepositoryEntity repository = repoWithAccount.repository;
          RoomAccountEntity account = repoWithAccount.account;
          return convert(repository, account);
        })
        .toList();
  }

  public enum Factory implements Singleton<RepositoryEntityMapper> {
    INSTANCE;

    private final RepositoryEntityMapper instance = new RepositoryEntityMapper(AccountEntityMapper.Factory.INSTANCE.get());

    @NonNull
    @Override
    public RepositoryEntityMapper get() {
      return instance;
    }
  }
}
