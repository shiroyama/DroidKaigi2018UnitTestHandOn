package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.annimon.stream.Stream;

import java.util.List;

import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RepoWithAccount;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomAccountEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomRepositoryEntity;

/**
 * {@link Dao} for {@link RoomRepositoryEntity}
 *
 * @author Fumihiko Shiroyama
 */

@Dao
public abstract class RoomRepositoryDao {
  @Query("SELECT * FROM repository")
  public abstract List<RoomRepositoryEntity> getAll();

  @Query("SELECT * FROM repository WHERE account_id = :accountId")
  public abstract List<RoomRepositoryEntity> findByAccountId(long accountId);

  @Query("SELECT repository.id AS repo_id," +
      "repository.name AS repo_name," +
      "repository.full_name AS repo_full_name," +
      "repository.html_url AS repo_html_url," +
      "repository.is_private AS repo_is_private," +
      "repository.description AS repo_description," +
      "repository.account_id AS repo_account_id," +
      "repository.created_at AS repo_created_at," +
      "repository.updated_at AS repo_updated_at," +
      "repository.pushed_at AS repo_pushed_at," +
      "account.id AS account_id," +
      "account.login AS account_login," +
      "account.avatar_url AS account_avatar_url," +
      "account.url AS account_url " +
      "FROM repository INNER JOIN account ON repository.account_id = account.id " +
      "WHERE account.login = :account " +
      "ORDER BY repository.updated_at DESC")
  public abstract List<RepoWithAccount> findByAccount(String account);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insertAllRepositories(RoomRepositoryEntity... repositories);

  @Delete
  public abstract void deleteAllRepositories(RoomRepositoryEntity... repositories);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insertAllAccounts(RoomAccountEntity... accounts);

  @Transaction
  public void insertRepositoriesAndAccounts(List<RoomRepositoryEntity> repositories, List<RoomAccountEntity> accounts) {
    insertAllRepositories(repositories.toArray(new RoomRepositoryEntity[repositories.size()]));
    insertAllAccounts(accounts.toArray(new RoomAccountEntity[accounts.size()]));
  }

  @Transaction
  public void deleteAndInsertRepositoriesAndAccounts(String account, List<RoomRepositoryEntity> repositories, List<RoomAccountEntity> accounts) {
    List<RepoWithAccount> repoWithAccountList = findByAccount(account);
    List<RoomRepositoryEntity> roomRepositoryEntities = Stream.of(repoWithAccountList)
        .map(repoWithAccount -> repoWithAccount.repository)
        .toList();
    if (!roomRepositoryEntities.isEmpty()) {
      deleteAllRepositories(roomRepositoryEntities.toArray(new RoomRepositoryEntity[roomRepositoryEntities.size()]));
    }
    insertAllRepositories(repositories.toArray(new RoomRepositoryEntity[repositories.size()]));
    insertAllAccounts(accounts.toArray(new RoomAccountEntity[accounts.size()]));
  }
}
