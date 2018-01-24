package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.remote;

import android.support.annotation.NonNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import us.shiroyama.android.my_repositories.common.GsonProvider;
import us.shiroyama.android.my_repositories.common.OkHttpProvider;
import us.shiroyama.android.my_repositories.infrastructure.entity.RepositoryEntity;
import us.shiroyama.android.my_repositories.infrastructure.exception.ApiException;
import us.shiroyama.android.my_repositories.infrastructure.repository.datasource.remote.service.GitHubService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

/**
 * Local Unit Test for {@link GitHubRestDataSource}
 *
 * @author Fumihiko Shiroyama
 */

public class GitHubRestDataSourceTest {
  private final MockWebServer mockWebServer = new MockWebServer();

  private GitHubRestDataSource gitHubRestDataSource;

  /**
   * {@link MockWebServer} を使うとリクエストに応じてレスポンスを返し分けるというようなことがUnit Testでも可能になる。
   * 実際のAPI通信の結果を `test/resources` 等にJSONで保存しておいて、リクエストに合わせて読み出して返すことで、本番さながらのテストが可能になる。
   */
  @Before
  public void setUp() throws Exception {
    Dispatcher dispatcher = new Dispatcher() {
      @Override
      public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
        if (request == null || request.getPath() == null) {
          return new MockResponse().setResponseCode(400);
        }
        if (request.getPath().matches("/users/srym/repos/?.*")) {
          return new MockResponse().setBody(readJsonFromResources("users_srym_repos.json")).setResponseCode(200);
        }
        if (request.getPath().matches("/users/ymnder/repos/?.*")) {
          return new MockResponse().setBody(readJsonFromResources("users_ymnder_repos.json")).setResponseCode(200);
        }
        return new MockResponse().setResponseCode(404);
      }
    };
    mockWebServer.setDispatcher(dispatcher);
    mockWebServer.start();

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(mockWebServer.url(""))
        .addConverterFactory(GsonConverterFactory.create(GsonProvider.INSTANCE.get()))
        .client(OkHttpProvider.INSTANCE.get())
        .build();

    GitHubService gitHubService = retrofit.create(GitHubService.class);
    gitHubRestDataSource = new GitHubRestDataSource(GsonProvider.INSTANCE.get(), gitHubService);
  }

  @After
  public void tearDown() throws Exception {
    mockWebServer.shutdown();
  }

  /**
   * "srym" ユーザでリクエストした場合の結果を検証してみよう。
   * <p>
   * ヒント: users_srym_repos.json の中身が返される。
   */
  @Test
  public void getUserRepositories_inputSrym_returnsSuccessfulResults() throws Exception {
    List<RepositoryEntity> repositories = gitHubRestDataSource.getUserRepositories("srym");
    assertThat(repositories)
        .isNotEmpty()
        .hasSize(30);
    assertThat(repositories.get(0).getName()).isEqualTo("dotfiles");
  }

  /**
   * "ymnder" ユーザでリクエストした場合の結果を検証してみよう。
   * <p>
   * ヒント: users_ymnder_repos.json の中身が返される。
   */
  @Test
  public void getUserRepositories_inputYmnder_returnsSuccessfulResults() throws Exception {
    List<RepositoryEntity> repositories = gitHubRestDataSource.getUserRepositories("ymnder");
    assertThat(repositories)
        .isNotEmpty()
        .hasSize(23);
    assertThat(repositories.get(0).getName()).isEqualTo("conference-app-2018");
  }

  /**
   * 未定義のユーザでリクエストした場合に {@link ApiException} が上がることを検証しよう。
   */
  @Test(expected = ApiException.class)
  public void getUserRepositories_inputInvalidAccount_causedApiException() throws Exception {
    gitHubRestDataSource.getUserRepositories("hoge");
  }

  /**
   * ファイル名からJSONを読み出して返してくれるヘルパメソッド
   */
  private String readJsonFromResources(@NonNull String fileName) {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    StringBuilder stringBuilder = new StringBuilder();
    try {
      String buffer;
      while ((buffer = bufferedReader.readLine()) != null) {
        stringBuilder.append(buffer);
      }
    } catch (IOException e) {
      fail(e.getMessage(), e);
    }
    return stringBuilder.toString();
  }

}