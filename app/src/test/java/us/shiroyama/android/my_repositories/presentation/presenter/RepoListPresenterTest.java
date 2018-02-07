package us.shiroyama.android.my_repositories.presentation.presenter;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import us.shiroyama.android.my_repositories.domain.usecase.GetRepositories;
import us.shiroyama.android.my_repositories.domain.usecase.executor.TaskTicket;
import us.shiroyama.android.my_repositories.presentation.mapper.RepoViewModelMapper;
import us.shiroyama.android.my_repositories.presentation.view.contract.RepoListContract;
import us.shiroyama.android.my_repositories.presentation.view.fragment.RepoListFragment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Local Unit Test for {@link RepoListPresenter}
 *
 * @author Fumihiko Shiroyama
 */

public class RepoListPresenterTest {
  private RepoListPresenter presenter;
  private RepoListContract.View view;
  private GetRepositories getRepositories;
  private RepoViewModelMapper mapper;
  private TaskTicket ticket;

  /**
   * レポジトリ一覧のPresenterのテスト。
   * {@link RepoListContract.View} は実際には {@link RepoListFragment} が実装するが、これはインタフェースを切ってあるのでモックすることができる。
   * Presenterのメソッドが呼ばれる度に {@link RepoListContract.View} の状態変化を <code>verify()</code> することでテストが可能になる。
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * {@link RepoListPresenter#getRepositoryList(String)} のテスト。
   * Viewがプログレスバーを表示することを検証しよう。
   * またキューに積んだタスクをキャンセルするための {@link TaskTicket} の状態変化も検証してみよう。
   */
  @Test
  public void getRepositoryList() throws Exception {
  }

  /**
   * {@link RepoListPresenter#refreshRepositoryList(String)} のテスト。
   * リフレッシュ時にはプログレスバーが表示されないことを検証しよう。
   * またキューに積んだタスクをキャンセルするための {@link TaskTicket} の状態変化も検証してみよう。
   */
  @Test
  public void refreshRepositoryList() throws Exception {
  }

  /**
   * {@link RepoListPresenter#onDestroyView()} のテスト。
   * ライフサイクルの変化に合わせて {@link TaskTicket#cancel(boolean)} が呼ばれることを検証しよう。
   */
  @Test
  public void onDestroyView() throws Exception {
  }

  /**
   * {@link RepoListPresenter#onSuccess(GetRepositories.OnSuccessGetRepositories)} のテスト。
   * キューイングした処理の完了が通知されたことをうけ、プログレスバーを非表示にしたり画面に結果を表示したりするような諸々のインタラクションを検証してみよう。
   */
  @Test
  public void onSuccess() throws Exception {
  }

  /**
   * {@link RepoListPresenter#onError(GetRepositories.OnFailureGetRepositories)} のテスト。
   * キューイングした処理がエラー終了したことをうけ、プログレスバーを非表示にしたり、アカウント入力画面に戻したりするようなインタラクションが意図通り発生することを検証しよう。
   */
  @Test
  public void onError() throws Exception {
  }

}