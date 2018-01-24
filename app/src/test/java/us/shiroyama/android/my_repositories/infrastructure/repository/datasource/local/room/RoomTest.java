package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room;

import android.content.Context;

import com.annimon.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.threeten.bp.ZonedDateTime;

import java.util.Arrays;
import java.util.List;

import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.dao.RoomAccountDao;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.dao.RoomRepositoryDao;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.db.AppDatabase;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.db.DatabaseProvider;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RepoWithAccount;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomAccountEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomRepositoryEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Room Test
 *
 * @author Fumihiko Shiroyama
 */

@RunWith(RobolectricTestRunner.class)
public class RoomTest {
  private AppDatabase db;

  @Before
  public void setUp() throws Exception {
    Context context = RuntimeEnvironment.application.getApplicationContext();
    db = new DatabaseProvider().createDatabase(context);
  }

  @Test
  public void account_dao_test() throws Exception {
    RoomAccountDao dao = db.accountDao();
    List<RoomAccountEntity> accounts = dao.getAll();
    assertThat(accounts).isEmpty();

    RoomAccountEntity srym = new RoomAccountEntity(1192694, "srym", "https://avatars1.githubusercontent.com/u/1192694?v=4", "https://api.github.com/users/srym");
    dao.insertAll(srym);

    accounts = dao.getAll();
    assertThat(accounts)
        .isNotEmpty()
        .hasSize(1);

    srym = dao.findByAccount("srym");
    assertThat(srym).isNotNull();

    RoomAccountEntity ymnder = dao.findByAccount("ymnder");
    assertThat(ymnder).isNull();
  }

  @Test
  public void repository_dao_test() throws Exception {
    RoomRepositoryDao dao = db.repositoryDao();
    List<RoomRepositoryEntity> repositories = dao.getAll();
    assertThat(repositories).isEmpty();

    ZonedDateTime createdAt = ZonedDateTime.parse("2012-09-05T02:17:05Z");
    ZonedDateTime updatedAt = ZonedDateTime.parse("2018-01-26T05:21:03Z");
    ZonedDateTime pushedAt = ZonedDateTime.parse("2018-01-26T05:21:02Z");
    RoomRepositoryEntity repoDotfiles = new RoomRepositoryEntity(
        5681402,
        "dotfiles",
        "srym/dotfiles",
        "https://github.com/srym/dotfile",
        false,
        "this is description",
        1192694,
        createdAt,
        updatedAt,
        pushedAt
    );
    dao.insertAllRepositories(repoDotfiles);

    repositories = dao.getAll();
    assertThat(repositories)
        .isNotEmpty()
        .hasSize(1);

    List<RoomRepositoryEntity> byAccountId = dao.findByAccountId(1234);
    assertThat(byAccountId).isEmpty();

    byAccountId = dao.findByAccountId(1192694);
    assertThat(byAccountId)
        .isNotEmpty()
        .hasSize(1);
  }

  @Test
  public void repo_with_account_test() throws Exception {
    RoomAccountDao accountDao = db.accountDao();
    RoomAccountEntity srym = new RoomAccountEntity(1192694, "srym", "https://avatars1.githubusercontent.com/u/1192694?v=4", "https://api.github.com/users/srym");
    accountDao.insertAll(srym);

    List<RoomAccountEntity> accounts = accountDao.getAll();
    assertThat(accounts)
        .isNotEmpty()
        .hasSize(1);

    RoomRepositoryDao repositoryDao = db.repositoryDao();
    List<RepoWithAccount> repos = repositoryDao.findByAccount("srym");
    assertThat(repos).isEmpty();

    ZonedDateTime createdAt = ZonedDateTime.parse("2012-09-05T02:17:05Z");
    ZonedDateTime updatedAt = ZonedDateTime.parse("2018-01-26T05:21:03Z");
    ZonedDateTime pushedAt = ZonedDateTime.parse("2018-01-26T05:21:02Z");
    RoomRepositoryEntity repoDotfiles = new RoomRepositoryEntity(
        5681402,
        "dotfiles",
        "srym/dotfiles",
        "https://github.com/srym/dotfile",
        false,
        "this is description",
        1192694,
        createdAt,
        updatedAt,
        pushedAt
    );
    repositoryDao.insertAllRepositories(repoDotfiles);

    repos = repositoryDao.findByAccount("srym");
    assertThat(repos)
        .isNotEmpty()
        .hasSize(1);

    repos = repositoryDao.findByAccount("ymnder");
    assertThat(repos).isEmpty();
  }

  @Test
  public void deleteAndInsert() throws Exception {
    List<RoomRepositoryEntity> repositoriesSrym = Arrays.asList(
        new RoomRepositoryEntity(
            5681402,
            "dotfiles",
            "srym/dotfiles",
            "https://github.com/srym/dotfile",
            false,
            "this is description",
            1192694,
            ZonedDateTime.parse("2012-09-05T02:17:05Z"),
            ZonedDateTime.parse("2018-01-26T05:21:03Z"),
            ZonedDateTime.parse("2018-01-26T05:21:02Z")
        ),
        new RoomRepositoryEntity(
            64338388,
            "FirebaseRealTimeChat",
            "srym/FirebaseRealTimeChat",
            "https://github.com/srym",
            false,
            "Sample real-time chat application using Firebase",
            1192694,
            ZonedDateTime.parse("2016-07-27T20:08:52Z"),
            ZonedDateTime.parse("2018-01-14T02:27:54Z"),
            ZonedDateTime.parse("2017-02-15T23:52:13Z")
        )
    );
    List<RoomRepositoryEntity> repositoriesYmnder = Arrays.asList(
        new RoomRepositoryEntity(
            120437130,
            "conference-app-2018",
            "ymnder/conference-app-2018",
            "https://github.com/ymnder",
            false,
            "The Official Conference App for DroidKaigi 2018 Tokyo",
            7427558,
            ZonedDateTime.parse("2018-02-06T10:14:23Z"),
            ZonedDateTime.parse("2018-02-06T10:14:26Z"),
            ZonedDateTime.parse("2018-02-06T11:29:22Z")
        ),
        new RoomRepositoryEntity(
            120296209,
            "WidgetSample",
            "ymnder/WidgetSample",
            "https://github.com/ymnder",
            false,
            "DroidKaigi2018  https://droidkaigi.jp/2018/timetable?session=16949",
            7427558,
            ZonedDateTime.parse("2018-02-05T11:31:28Z"),
            ZonedDateTime.parse("2018-02-05T11:32:40Z"),
            ZonedDateTime.parse("2018-02-05T17:15:05Z")
        )
    );
    List<RoomAccountEntity> accountsSrym = Arrays.asList(
        new RoomAccountEntity(1192694, "srym", "https://avatars1.githubusercontent.com/u/1192694?v=4", "https://api.github.com/users/srym"),
        new RoomAccountEntity(1192694, "srym", "https://avatars1.githubusercontent.com/u/1192694?v=4", "https://api.github.com/users/srym"),
        new RoomAccountEntity(1192694, "srym", "https://avatars1.githubusercontent.com/u/1192694?v=4", "https://api.github.com/users/srym")
    );
    List<RoomAccountEntity> accountsYmnder = Arrays.asList(
        new RoomAccountEntity(7427558, "ymnder", "https://avatars0.githubusercontent.com/u/7427558?v=4", "https://api.github.com/users/ymnder"),
        new RoomAccountEntity(7427558, "ymnder", "https://avatars0.githubusercontent.com/u/7427558?v=4", "https://api.github.com/users/ymnder"),
        new RoomAccountEntity(7427558, "ymnder", "https://avatars0.githubusercontent.com/u/7427558?v=4", "https://api.github.com/users/ymnder")
    );

    RoomRepositoryDao repositoryDao = db.repositoryDao();
    // check insert replace
    for (int i = 0; i < 3; i++) {
      repositoryDao.insertRepositoriesAndAccounts(repositoriesSrym, Stream.of(accountsSrym).distinct().toList());
      repositoryDao.insertRepositoriesAndAccounts(repositoriesYmnder, Stream.of(accountsYmnder).distinct().toList());
    }

    List<RepoWithAccount> hoge = repositoryDao.findByAccount("hoge");
    assertThat(hoge).isEmpty();

    List<RepoWithAccount> srym = repositoryDao.findByAccount("srym");
    assertThat(srym)
        .isNotEmpty()
        .hasSize(2);

    List<RepoWithAccount> ymnder = repositoryDao.findByAccount("ymnder");
    assertThat(ymnder)
        .isNotEmpty()
        .hasSize(2);

    RoomAccountDao accountDao = db.accountDao();
    List<RoomAccountEntity> allAccounts = accountDao.getAll();
    assertThat(allAccounts)
        .isNotEmpty()
        .hasSize(2);
  }
}