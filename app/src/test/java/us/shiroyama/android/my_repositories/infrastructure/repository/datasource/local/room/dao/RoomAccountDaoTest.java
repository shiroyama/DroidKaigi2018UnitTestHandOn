package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.dao;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.db.AppDatabase;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.db.DatabaseProvider;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomAccountEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Local Unit Test for {@link RoomAccountDao}
 *
 * @author Fumihiko Shiroyama
 */

@RunWith(RobolectricTestRunner.class)
public class RoomAccountDaoTest {
  private RoomAccountEntity srym = new RoomAccountEntity(1192694, "srym", "https://avatars1.githubusercontent.com/u/1192694?v=4", "https://api.github.com/users/srym");
  private RoomAccountEntity ymnder = new RoomAccountEntity(7427558, "ymnder", "https://avatars0.githubusercontent.com/u/7427558?v=4", "https://api.github.com/users/ymnder");
  private AppDatabase db;

  @Before
  public void setUp() throws Exception {
    Context context = RuntimeEnvironment.application.getApplicationContext();
    db = new DatabaseProvider().createDatabase(context);
  }

  @Test
  public void getAll() throws Exception {
    RoomAccountDao accountDao = db.accountDao();
    assertThat(accountDao.getAll()).isEmpty();

    accountDao.insertAll(srym);
    assertThat(accountDao.getAll())
        .isNotEmpty()
        .hasSize(1);
  }

  @Test
  public void findByAccount() throws Exception {
    RoomAccountDao accountDao = db.accountDao();
    accountDao.insertAll(srym);

    assertThat(accountDao.findByAccount("ymnder")).isNull();
    assertThat(accountDao.findByAccount("srym")).isNotNull();
  }

  @Test
  public void insertAll() throws Exception {
    RoomAccountDao accountDao = db.accountDao();
    assertThat(accountDao.getAll()).isEmpty();

    accountDao.insertAll(srym, ymnder);
    assertThat(accountDao.getAll())
        .isNotEmpty()
        .hasSize(2);
  }

  @Test
  public void deleteAll() throws Exception {
    RoomAccountDao accountDao = db.accountDao();
    accountDao.insertAll(srym, ymnder);
    assertThat(accountDao.getAll())
        .isNotEmpty()
        .hasSize(2);

    accountDao.deleteAll(srym, ymnder);
    assertThat(accountDao.getAll()).isEmpty();
  }

}