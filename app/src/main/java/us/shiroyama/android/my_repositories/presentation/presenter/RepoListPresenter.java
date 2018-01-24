package us.shiroyama.android.my_repositories.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.otto.Subscribe;

import java.util.List;

import us.shiroyama.android.my_repositories.domain.model.Repository;
import us.shiroyama.android.my_repositories.domain.usecase.GetRepositories;
import us.shiroyama.android.my_repositories.domain.usecase.executor.TaskTicket;
import us.shiroyama.android.my_repositories.presentation.mapper.RepoViewModelMapper;
import us.shiroyama.android.my_repositories.presentation.view.contract.RepoListContract;

import static com.annimon.stream.Optional.ofNullable;

/**
 * Presenter for Repository List Screen
 *
 * @author Fumihiko Shiroyama
 */

public class RepoListPresenter extends BasePresenter<RepoListContract.View> implements RepoListContract.Interaction {
  @NonNull
  private final GetRepositories getRepositories;

  @NonNull
  private final RepoViewModelMapper mapper;

  @Nullable
  TaskTicket ticket;

  public RepoListPresenter(@NonNull GetRepositories getRepositories, @NonNull RepoViewModelMapper mapper) {
    this.getRepositories = getRepositories;
    this.mapper = mapper;
  }

  @Override
  public void getRepositoryList(@NonNull String account) {
    view.showProgressBar();
    ticket = getRepositories.enqueue(GetRepositories.Param.newInstance(account));
  }

  @Override
  public void refreshRepositoryList(@NonNull String account) {
    ticket = getRepositories.enqueue(GetRepositories.Param.newInstance(account, true));
  }

  @Override
  public void onDestroyView() {
    ofNullable(ticket).ifPresent(ticket -> ticket.cancel(true));
  }

  @Subscribe
  public void onSuccess(GetRepositories.OnSuccessGetRepositories success) {
    view.hideProgressBar();
    List<Repository> repositories = success.getItem();
    view.showRepositoryList(mapper.convertList(repositories));
  }

  @Subscribe
  public void onError(GetRepositories.OnFailureGetRepositories failure) {
    view.hideProgressBar();
    view.showError(failure.getItem().getMessage());
    finishActivity();
  }
}
