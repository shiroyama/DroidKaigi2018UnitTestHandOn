package us.shiroyama.android.my_repositories.domain.mapper;

import android.support.annotation.NonNull;

import us.shiroyama.android.my_repositories.common.ListAwareMapper;
import us.shiroyama.android.my_repositories.common.Singleton;
import us.shiroyama.android.my_repositories.domain.model.Repository;
import us.shiroyama.android.my_repositories.infrastructure.entity.RepositoryEntity;

import static android.text.TextUtils.isEmpty;

/**
 * Mapper from {@link RepositoryEntity} to {@link Repository}
 *
 * @author Fumihiko Shiroyama
 */

public class RepositoryMapper extends ListAwareMapper<RepositoryEntity, Repository> {
  @NonNull
  private final AccountMapper accountMapper;

  public RepositoryMapper(@NonNull AccountMapper accountMapper) {
    this.accountMapper = accountMapper;
  }

  @NonNull
  @Override
  public Repository convert(@NonNull RepositoryEntity repositoryEntity) {
    String description = isEmpty(repositoryEntity.getDescription())
        ? "no description"
        : repositoryEntity.getDescription();
    return new Repository(
        repositoryEntity.getId(),
        repositoryEntity.getName(),
        repositoryEntity.getFullName(),
        repositoryEntity.getHtmlUrl(),
        repositoryEntity.isPrivate(),
        description,
        accountMapper.convert(repositoryEntity.getOwner()),
        repositoryEntity.getCreatedAt(),
        repositoryEntity.getUpdatedAt(),
        repositoryEntity.getPushedAt()
    );
  }

  public enum Factory implements Singleton<RepositoryMapper> {
    INSTANCE;

    private final RepositoryMapper instance = new RepositoryMapper(AccountMapper.Factory.INSTANCE.get());

    @NonNull
    @Override
    public RepositoryMapper get() {
      return instance;
    }
  }
}
