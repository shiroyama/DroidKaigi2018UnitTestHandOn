package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomAccountEntity;

/**
 * {@link Dao} for {@link RoomAccountEntity}
 *
 * @author Fumihiko Shiroyama
 */

@Dao
public interface RoomAccountDao {
  @Query("SELECT * FROM account")
  List<RoomAccountEntity> getAll();

  @Query("SELECT * FROM account WHERE login = :account LIMIT 1")
  RoomAccountEntity findByAccount(String account);

  @Insert
  void insertAll(RoomAccountEntity... accounts);

  @Delete
  void deleteAll(RoomAccountEntity... accounts);
}
