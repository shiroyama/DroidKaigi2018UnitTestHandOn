package us.shiroyama.android.my_repositories.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import us.shiroyama.android.my_repositories.domain.usecase.AccountValidator;
import us.shiroyama.android.my_repositories.presentation.view.activity.RepoListActivity;
import us.shiroyama.android.my_repositories.presentation.view.contract.AccountInputContract;

import static com.annimon.stream.Optional.ofNullable;

/**
 * Presenter for Account Input Screen
 *
 * @author Fumihiko Shiroyama
 */

public class AccountInputPresenter extends BasePresenter<AccountInputContract.View> implements AccountInputContract.Interaction {
  @NonNull
  private final AccountValidator accountValidator;

  public AccountInputPresenter(@NonNull AccountValidator accountValidator) {
    this.accountValidator = accountValidator;
  }

  @Override
  public void onClickViewRepositoryButton(@Nullable String input) {
    AccountValidator.Result result = accountValidator.validate(input);
    if (!result.isValid()) {
      view.showInputError(result.getReasonRes());
      return;
    }
    assert input != null;
    startRepoListScreen(input);
  }

  void startRepoListScreen(@NonNull String account) {
    ofNullable(getActivity()).ifPresent(context -> startActivity(RepoListActivity.newIntent(context, account)));
  }
}
