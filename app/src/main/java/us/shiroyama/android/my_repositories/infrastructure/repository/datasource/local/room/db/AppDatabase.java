package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.dao.RoomAccountDao;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.dao.RoomRepositoryDao;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomAccountEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomRepositoryEntity;

/**
 * Room Database
 *
 * @author Fumihiko Shiroyama
 */

@Database(entities = {RoomAccountEntity.class, RoomRepositoryEntity.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
  public abstract RoomAccountDao accountDao();

  public abstract RoomRepositoryDao repositoryDao();
}
