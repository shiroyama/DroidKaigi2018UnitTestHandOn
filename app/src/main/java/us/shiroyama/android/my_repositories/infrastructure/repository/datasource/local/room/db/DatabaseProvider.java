package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.db;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * {@link AppDatabase} Provider
 *
 * @author Fumihiko Shiroyama
 */

public class DatabaseProvider {
  private static final String DB_NAME = "github_db";

  public AppDatabase createDatabase(@NonNull Context context) {
    return Room.databaseBuilder(context.getApplicationContext(),
        AppDatabase.class, DB_NAME)
        .allowMainThreadQueries()
        .build();
  }

  public static class Factory {
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(@NonNull Context context) {
      if (instance == null) {
        instance = new DatabaseProvider().createDatabase(context);
      }
      return instance;
    }
  }
}
