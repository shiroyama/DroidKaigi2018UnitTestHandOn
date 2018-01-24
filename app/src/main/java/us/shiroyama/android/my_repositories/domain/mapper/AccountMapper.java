package us.shiroyama.android.my_repositories.domain.mapper;

import android.support.annotation.NonNull;

import us.shiroyama.android.my_repositories.common.ListAwareMapper;
import us.shiroyama.android.my_repositories.common.Singleton;
import us.shiroyama.android.my_repositories.domain.model.Account;
import us.shiroyama.android.my_repositories.infrastructure.entity.AccountEntity;

/**
 * Mapper from {@link AccountEntity} to {@link Account}
 *
 * @author Fumihiko Shiroyama
 */

public class AccountMapper extends ListAwareMapper<AccountEntity, Account> {
  @NonNull
  @Override
  public Account convert(@NonNull AccountEntity accountEntity) {
    return new Account(
        accountEntity.getId(),
        accountEntity.getLogin(),
        accountEntity.getAvatarUrl(),
        accountEntity.getUrl()
    );
  }

  public enum Factory implements Singleton<AccountMapper> {
    INSTANCE;

    private final AccountMapper instance = new AccountMapper();

    @NonNull
    @Override
    public AccountMapper get() {
      return instance;
    }
  }
}
