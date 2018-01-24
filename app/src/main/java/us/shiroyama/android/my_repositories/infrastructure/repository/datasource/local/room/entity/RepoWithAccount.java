package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.support.annotation.VisibleForTesting;

/**
 * POJO to hold {@link RoomRepositoryEntity} with {@link RoomAccountEntity}
 *
 * @author Fumihiko Shiroyama
 */

public class RepoWithAccount {
  @Embedded(prefix = "repo_")
  public RoomRepositoryEntity repository;

  @Embedded(prefix = "account_")
  public RoomAccountEntity account;

  public RepoWithAccount() {
  }

  @Ignore
  @VisibleForTesting(otherwise = VisibleForTesting.NONE)
  public RepoWithAccount(RoomRepositoryEntity repository, RoomAccountEntity account) {
    this.repository = repository;
    this.account = account;
  }
}
