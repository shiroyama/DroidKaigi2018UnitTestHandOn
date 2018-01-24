package us.shiroyama.android.my_repositories.presentation.view.fragment;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import us.shiroyama.android.my_repositories.R;
import us.shiroyama.android.my_repositories.domain.usecase.AccountValidator;
import us.shiroyama.android.my_repositories.presentation.presenter.AccountInputPresenter;
import us.shiroyama.android.my_repositories.presentation.view.contract.AccountInputContract;

/**
 * Account Input Screen Fragment
 *
 * @author Fumihiko Shiroyama
 */

public class AccountInputFragment extends BaseFragment<AccountInputContract.View, AccountInputPresenter> implements AccountInputContract.View {
  @BindView(R.id.account)
  EditText accountView;

  @OnClick(R.id.view_repository_button)
  public void onClickViewRepositoryButton(Button button) {
    presenter.onClickViewRepositoryButton(accountView.getText().toString());
  }

  public static AccountInputFragment newInstance() {
    return new AccountInputFragment();
  }

  @NonNull
  @Override
  protected AccountInputContract.View getViewContract() {
    return this;
  }

  @NonNull
  @Override
  protected AccountInputPresenter getPresenter() {
    return new AccountInputPresenter(AccountValidator.Factory.INSTANCE.get());
  }

  @Override
  protected int getLayoutRes() {
    return R.layout.fragment_account_input;
  }

  @NonNull
  @Override
  protected Unbinder bindView(@NonNull View fragmentView) {
    return ButterKnife.bind(this, fragmentView);
  }

  @Override
  public void showInputError(int errorRes) {
    accountView.setError(getString(errorRes));
    accountView.requestFocus();
  }
}
