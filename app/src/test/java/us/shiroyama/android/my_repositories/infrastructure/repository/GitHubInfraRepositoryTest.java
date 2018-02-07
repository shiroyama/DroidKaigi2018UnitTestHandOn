package us.shiroyama.android.my_repositories.infrastructure.repository;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import us.shiroyama.android.my_repositories.infrastructure.entity.AccountEntity;
import us.shiroyama.android.my_repositories.infrastructure.entity.RepositoryEntity;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.GitHubLocalDataSource;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.remote.GitHubRemoteDataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Local Unit Test for {@link GitHubInfraRepository}
 *
 * @author Fumihiko Shiroyama
 */

public class GitHubInfraRepositoryTest {
  private GitHubRemoteDataSource remoteDataSource;
  private GitHubLocalDataSource localDataSource;
  private GitHubInfraRepository repository;

  /**
   * 各データソースを束ねたレポジトリのテストをする。
   * ここまでくれば、各データソースはすべてモックで良い。
   * <p>
   * レポジトリ層では、各データソースのどこでデータにヒットするかによって呼ばれるメソッドが変わるので、確実に<code>verify()</code>で確かめる。
   * その際、呼ばれないものはきちんと<code>never()</code>しておくとなお良いだろう。
   */
  @Before
  public void setUp() throws Exception {
    remoteDataSource = mock(GitHubRemoteDataSource.class);
    localDataSource = mock(GitHubLocalDataSource.class);
    repository = new GitHubInfraRepository(remoteDataSource, localDataSource);
  }

  /**
   * {@link GitHubInfraRepository#getUserRepositories(String)}のテスト
   * <p>
   * ローカルデータソースにデータがあった場合のテストを書こう。
   * その際、リモートデータソースの呼び出しは行われないことも同時に検証しよう。
   */
  @Test
  public void getUserRepositories_localDataSource_hit() throws Exception {
  }

  /**
   * {@link GitHubInfraRepository#getUserRepositories(String)}のテスト
   * <p>
   * ローカルデータソースも空っぽで、リモートデータソースの結果も空っぽの場合のテストを書こう。
   * 同じく、インタラクションが行われないメソッドはそれも検証しよう。
   */
  @Test
  public void getUserRepositories_localDataSource_not_hit_and_remoteDataSource_empty() throws Exception {
  }

  /**
   * {@link GitHubInfraRepository#getUserRepositories(String)}のテスト
   * <p>
   * ローカルデータソースは空っぽだが、リモートデータソースが結果を返してくれた場合のテストを書こう。
   * リモートデータソースが取得できた場合はローカルデータソースへの書き込みがあるはずなのでそれも検証しよう。
   */
  @Test
  public void getUserRepositories_localDataSource_not_hit_and_remoteDataSource_hit() throws Exception {
  }

  /**
   * {@link GitHubInfraRepository#refreshUserRepositories(String)}のテスト
   * <p>
   * リモートデータソースが空を返した場合のテストを書こう。
   */
  @Test
  public void refreshUserRepositories_remoteDataSource_no_hit() throws Exception {
  }

  /**
   * {@link GitHubInfraRepository#refreshUserRepositories(String)}のテスト
   * <p>
   * リモートデータソースが値を返した場合のテストを書こう。
   */
  @Test
  public void refreshUserRepositories_remoteDataSource_hit() throws Exception {
  }

}