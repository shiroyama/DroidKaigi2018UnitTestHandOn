package us.shiroyama.android.my_repositories.presentation.view.contract;

import android.support.annotation.NonNull;

import java.util.List;

import us.shiroyama.android.my_repositories.presentation.presenter.RepoListPresenter;
import us.shiroyama.android.my_repositories.presentation.view.BaseView;
import us.shiroyama.android.my_repositories.presentation.view.fragment.RepoListFragment;
import us.shiroyama.android.my_repositories.presentation.viewmodel.RepoViewModel;

/**
 * Contract between {@link RepoListFragment} and {@link RepoListPresenter}
 *
 * @author Fumihiko Shiroyama
 */

public interface RepoListContract {
  interface View extends BaseView {
    void showRepositoryList(@NonNull List<RepoViewModel> repositoryList);

    void showProgressBar();

    void hideProgressBar();

    void showError(@NonNull String message);
  }

  interface Interaction {
    void getRepositoryList(@NonNull String account);

    void refreshRepositoryList(@NonNull String account);
  }
}
