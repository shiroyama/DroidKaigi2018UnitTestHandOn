package us.shiroyama.android.my_repositories.domain.usecase;

import com.squareup.otto.Bus;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;

import us.shiroyama.android.my_repositories.domain.mapper.RepositoryMapper;
import us.shiroyama.android.my_repositories.domain.usecase.executor.Task;
import us.shiroyama.android.my_repositories.domain.usecase.executor.TaskQueue;
import us.shiroyama.android.my_repositories.infrastructure.exception.ApiException;
import us.shiroyama.android.my_repositories.infrastructure.repository.GitHubRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Local Unit Test for {@link GetRepositories}
 *
 * @author Fumihiko Shiroyama
 */

public class GetRepositoriesTest {
  private TaskQueue taskQueue;
  private Bus bus;
  private GitHubRepository repository;
  private RepositoryMapper mapper;
  private GetRepositories getRepositories;

  /**
   * レポジトリ情報を取得するユースケースである {@link GetRepositories} のテスト。
   * 依存関係はすべてモックで良いだろう。
   * プロダクションコードでは {@link UseCase#enqueue(Object)} のように呼ばれ、{@link ExecutorService} のキューに積まれるため、少しテストが書きにくい。
   * <p>
   * ヒント: 実際にキューに積まれるのは {@link Task} であり、これは {@link Runnable} のサブクラスなので {@link Task#run()} を呼んでやることで実際に処理された状態を再現できる。
   */
  @Before
  public void setUp() throws Exception {
    taskQueue = mock(TaskQueue.class);
    bus = mock(Bus.class);
    repository = mock(GitHubRepository.class);
    mapper = mock(RepositoryMapper.class);
    getRepositories = new GetRepositories(taskQueue, bus, repository, mapper);
  }

  /**
   * {@link GetRepositories#enqueue(Object)} のテスト
   * 実際に処理が行われるまで確認するのは困難なので、{@link TaskQueue#enqueue(Task)} を<code>verify()</code>するぐらいしかできることがなさそうだ。
   */
  @Test
  public void enqueue() throws Exception {
    GetRepositories.Param param = GetRepositories.Param.newInstance("srym");
    getRepositories.enqueue(param);
    verify(taskQueue, times(1)).enqueue(any(Task.class));
  }

  /**
   * {@link GetRepositories#buildTask(Object)} のテスト
   * これで組み立てた {@link Task#run()} を呼んでやることで、依存関係のライブラリが内部的に呼ばれたかどうかを<code>verify()</code>できる。
   * <p>
   * ここではパラメータが<code>GetRepositories.Param.newInstance("srym")</code>の場合のテストを書いてみよう。
   */
  @Test
  public void buildTask_noRefresh_success() throws Exception {
    GetRepositories.Param param = GetRepositories.Param.newInstance("srym");
    Task task = getRepositories.buildTask(param);
    task.run();
    verify(repository, times(1)).getUserRepositories(eq("srym"));
    verify(repository, never()).refreshUserRepositories(eq("srym"));
    verify(mapper, never()).convert(any());
    verify(mapper, times(1)).convertList(anyList());
    verify(bus, times(1)).post(any(GetRepositories.OnSuccessGetRepositories.class));
  }

  /**
   * {@link GetRepositories#buildTask(Object)} のテスト
   * これで組み立てた {@link Task#run()} を呼んでやることで、依存関係のライブラリが内部的に呼ばれたかどうかを<code>verify()</code>できる。
   * <p>
   * ここではパラメータが<code>GetRepositories.Param.newInstance("srym")</code>の場合で {@link GitHubRepository#getUserRepositories(String)} でエラーがおきた場合のテストを書いてみよう。
   */
  @Test
  public void buildTask_noRefresh_error() throws Exception {
    doThrow(new ApiException()).when(repository).getUserRepositories(eq("srym"));
    GetRepositories.Param param = GetRepositories.Param.newInstance("srym");
    Task task = getRepositories.buildTask(param);
    task.run();
    verify(repository, times(1)).getUserRepositories(eq("srym"));
    verify(repository, never()).refreshUserRepositories(eq("srym"));
    verify(mapper, never()).convert(any());
    verify(mapper, never()).convertList(anyList());
    verify(bus, times(1)).post(any(GetRepositories.OnFailureGetRepositories.class));
  }

  /**
   * {@link GetRepositories#buildTask(Object)} のテスト
   * これで組み立てた {@link Task#run()} を呼んでやることで、依存関係のライブラリが内部的に呼ばれたかどうかを<code>verify()</code>できる。
   * <p>
   * ここではパラメータが<code>GetRepositories.Param.newInstance("srym")</code>の場合のテストを書いてみよう。
   */
  @Test
  public void buildTask_refresh_success() throws Exception {
    GetRepositories.Param param = GetRepositories.Param.newInstance("srym", true);
    Task task = getRepositories.buildTask(param);
    task.run();
    verify(repository, times(1)).refreshUserRepositories(eq("srym"));
    verify(repository, never()).getUserRepositories(eq("srym"));
    verify(mapper, never()).convert(any());
    verify(mapper, times(1)).convertList(anyList());
    verify(bus, times(1)).post(any(GetRepositories.OnSuccessGetRepositories.class));
  }

  /**
   * {@link GetRepositories#buildTask(Object)} のテスト
   * これで組み立てた {@link Task#run()} を呼んでやることで、依存関係のライブラリが内部的に呼ばれたかどうかを<code>verify()</code>できる。
   * <p>
   * ここではパラメータが<code>GetRepositories.Param.newInstance("srym")</code>の場合で {@link GitHubRepository#refreshUserRepositories(String)} でエラーがおきた場合のテストを書いてみよう。
   */
  @Test
  public void buildTask_refresh_error() throws Exception {
    doThrow(new ApiException()).when(repository).refreshUserRepositories(eq("srym"));
    GetRepositories.Param param = GetRepositories.Param.newInstance("srym", true);
    Task task = getRepositories.buildTask(param);
    task.run();
    verify(repository, times(1)).refreshUserRepositories(eq("srym"));
    verify(repository, never()).getUserRepositories(eq("srym"));
    verify(mapper, never()).convert(any());
    verify(mapper, never()).convertList(anyList());
    verify(bus, times(1)).post(any(GetRepositories.OnFailureGetRepositories.class));
  }

}