package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.dao;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.threeten.bp.ZonedDateTime;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.db.AppDatabase;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.db.DatabaseProvider;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomAccountEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity.RoomRepositoryEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Local Unit Test for {@link RoomRepositoryDaoTest}
 *
 * @author Fumihiko Shiroyama
 */

@RunWith(RobolectricTestRunner.class)
public class RoomRepositoryDaoTest {
  private AppDatabase db;

  /**
   * 読み書きに利用できるデータを予め用意したので適宜利用しよう。
   */
  private RoomAccountEntity srym = new RoomAccountEntity(1192694, "srym", "https://avatars1.githubusercontent.com/u/1192694?v=4", "https://api.github.com/users/srym");
  private RoomAccountEntity ymnder = new RoomAccountEntity(7427558, "ymnder", "https://avatars0.githubusercontent.com/u/7427558?v=4", "https://api.github.com/users/ymnder");
  private List<RoomRepositoryEntity> repositoriesSrym = Arrays.asList(
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
  private List<RoomRepositoryEntity> repositoriesYmnder = Arrays.asList(
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

  /**
   * {@link Robolectric} を利用すれば、特に何か別途設定しなくても普通にDBに対して読み書きするテストを書くことができる。
   * SQLiteへの読み書きには {@link Context} が求められるが、{@link Robolectric} では<code>RuntimeEnvironment.application.getApplicationContext()</code>のようにすると {@link Context} を取得できる。
   */
  @Before
  public void setUp() throws Exception {
    Context context = RuntimeEnvironment.application.getApplicationContext();
    db = new DatabaseProvider().createDatabase(context);
  }

  /**
   * DBに保存された全レポジトリデータを取得する {@link RoomRepositoryDao#getAll()} を検証しよう。
   */
  @Test
  public void getAll() throws Exception {
  }

  /**
   * {@link RoomAccountEntity#id} ユーザのレポジトリデータを取得する {@link RoomRepositoryDao#findByAccountId(long)} を検証しよう。
   */
  @Test
  public void findByAccountId() throws Exception {
  }

  /**
   * {@link RoomAccountEntity#login} のIDのユーザのレポジトリデータを取得する {@link RoomRepositoryDao#findByAccount(String)} を検証しよう。
   */
  @Test
  public void findByAccount() throws Exception {
  }

  /**
   * 指定したレポジトリデータをすべて挿入する {@link RoomRepositoryDao#insertAllRepositories(RoomRepositoryEntity...)} を検証しよう。
   */
  @Test
  public void insertAllRepositories() throws Exception {
  }

  /**
   * 指定したレポジトリデータをすべて削除する {@link RoomRepositoryDao#deleteAllRepositories(RoomRepositoryEntity...)} を検証しよう。
   */
  @Test
  public void deleteAllRepositories() throws Exception {
  }

  /**
   * 指定したユーザ情報をすべて挿入する {@link RoomRepositoryDao#insertAllAccounts(RoomAccountEntity...)} を検証しよう。
   */
  @Test
  public void insertAllAccounts() throws Exception {
  }

  /**
   * 指定したレポジトリとユーザ情報をすべて挿入する {@link RoomRepositoryDao#insertRepositoriesAndAccounts(List, List)} を検証しよう。
   */
  @Test
  public void insertRepositoriesAndAccounts() throws Exception {
  }

  /**
   * 指定したアカウントが持つレポジトリ情報をすべて消して後から追加する {@link RoomRepositoryDao#deleteAndInsertRepositoriesAndAccounts(String, List, List)} を検証しよう。
   */
  @Test
  public void deleteAndInsertRepositoriesAndAccounts() throws Exception {
  }

}