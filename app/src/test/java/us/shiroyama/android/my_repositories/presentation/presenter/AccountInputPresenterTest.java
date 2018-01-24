package us.shiroyama.android.my_repositories.presentation.presenter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import us.shiroyama.android.my_repositories.R;
import us.shiroyama.android.my_repositories.domain.usecase.AccountValidator;
import us.shiroyama.android.my_repositories.presentation.view.contract.AccountInputContract;
import us.shiroyama.android.my_repositories.presentation.view.fragment.AccountInputFragment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Local Unit Test for {@link AccountInputPresenter}
 *
 * @author Fumihiko Shiroyama
 */

@RunWith(RobolectricTestRunner.class)
public class AccountInputPresenterTest {
  private AccountInputPresenter presenter;
  private AccountValidator validator;
  private AccountInputContract.View view;

  /**
   * アカウント入力画面のPresenterのテスト。
   * {@link AccountInputContract.View} は実際には {@link AccountInputFragment} が実装するが、これはインタフェースを切ってあるのでモックすることができる。
   * Presenterのメソッドが呼ばれる度に {@link AccountInputContract.View} の状態変化を <code>verify()</code> することでテストが可能になる。
   */
  @Before
  public void setUp() throws Exception {
    validator = mock(AccountValidator.class);
    view = mock(AccountInputContract.View.class);
    when(view.getActivity()).thenReturn(mock(FragmentActivity.class));

    presenter = new AccountInputPresenter(validator);
    presenter.setView(view);
  }

  /**
   * {@link AccountInputPresenter#onClickViewRepositoryButton(String)} のテスト
   * 入力欄に空文字列が与えられた場合等にViewにその旨エラー表示するようにインタラクションするようなテストを書いてみよう。
   */
  @Test
  public void onClickViewRepositoryButton_error_field_required() throws Exception {
    when(validator.validate(any())).thenReturn(new AccountValidator.Result(false, R.string.error_field_required));
    presenter.onClickViewRepositoryButton(null);
    verify(view, times(1)).showInputError(eq(R.string.error_field_required));
  }

  /**
   * {@link AccountInputPresenter#onClickViewRepositoryButton(String)} のテスト
   * 入力欄に不正な文字列が与えられた場合等にViewにその旨エラー表示するようにインタラクションするようなテストを書いてみよう。
   */
  @Test
  public void onClickViewRepositoryButton_error_invalid_account() throws Exception {
    when(validator.validate(any())).thenReturn(new AccountValidator.Result(false, R.string.error_invalid_account));
    presenter.onClickViewRepositoryButton("***");
    verify(view, times(1)).showInputError(eq(R.string.error_invalid_account));
  }

  /**
   * {@link AccountInputPresenter#onClickViewRepositoryButton(String)} のテスト
   * 入力欄に正しい文字列が与えられた場合等に次の画面に遷移するようなインタラクションを検証するテストを書いてみよう。
   */
  @Test
  public void onClickViewRepositoryButton() throws Exception {
    when(validator.validate(any())).thenReturn(new AccountValidator.Result(true, 0));
    presenter.onClickViewRepositoryButton("srym");
    verify(view, times(1)).getActivity();
    verify(view, times(1)).startActivity(any(Intent.class));
  }
}