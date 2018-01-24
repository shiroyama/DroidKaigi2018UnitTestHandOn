package us.shiroyama.android.my_repositories.presentation.view.contract;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import us.shiroyama.android.my_repositories.presentation.presenter.AccountInputPresenter;
import us.shiroyama.android.my_repositories.presentation.view.BaseView;
import us.shiroyama.android.my_repositories.presentation.view.fragment.AccountInputFragment;

/**
 * Contract between {@link AccountInputFragment} and {@link AccountInputPresenter}
 *
 * @author Fumihiko Shiroyama
 */

public interface AccountInputContract {
  interface View extends BaseView {
    void showInputError(@StringRes int errorRes);
  }

  interface Interaction {
    void onClickViewRepositoryButton(@Nullable String input);
  }
}
