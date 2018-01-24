package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.mapper;

import android.support.annotation.NonNull;

import us.shiroyama.android.my_repositories.common.ListAwareMapper;
import us.shiroyama.android.my_repositories.common.Singleton;
import us.shiroyama.android.my_repositories.infrastructure.entity.AccountEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomAccountEntity;

/**
 * Mapper from {@link RoomAccountEntity} to {@link AccountEntity}
 *
 * @author Fumihiko Shiroyama
 */

public class AccountEntityMapper extends ListAwareMapper<RoomAccountEntity, AccountEntity> {
  @NonNull
  @Override
  public AccountEntity convert(@NonNull RoomAccountEntity roomAccountEntity) {
    return new AccountEntity(
        roomAccountEntity.getId(),
        roomAccountEntity.getLogin(),
        roomAccountEntity.getAvatarUrl(),
        roomAccountEntity.getUrl()
    );
  }

  public enum Factory implements Singleton<AccountEntityMapper> {
    INSTANCE;

    private final AccountEntityMapper instance = new AccountEntityMapper();

    @NonNull
    @Override
    public AccountEntityMapper get() {
      return instance;
    }
  }
}
