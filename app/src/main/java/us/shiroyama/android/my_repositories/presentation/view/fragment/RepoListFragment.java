package us.shiroyama.android.my_repositories.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;
import us.shiroyama.android.my_repositories.R;
import us.shiroyama.android.my_repositories.domain.usecase.GetRepositories;
import us.shiroyama.android.my_repositories.presentation.mapper.RepoViewModelMapper;
import us.shiroyama.android.my_repositories.presentation.presenter.RepoListPresenter;
import us.shiroyama.android.my_repositories.presentation.view.activity.RepoListActivity;
import us.shiroyama.android.my_repositories.presentation.view.adapter.RepoListAdapter;
import us.shiroyama.android.my_repositories.presentation.view.contract.RepoListContract;
import us.shiroyama.android.my_repositories.presentation.viewmodel.RepoViewModel;

import static android.text.TextUtils.isEmpty;
import static com.annimon.stream.Optional.ofNullable;

/**
 * Repository List Screen Fragment
 *
 * @author Fumihiko Shiroyama
 */

public class RepoListFragment extends BaseFragment<RepoListContract.View, RepoListPresenter> implements RepoListContract.View {
  @BindView(R.id.progress)
  View progressBar;

  @BindView(R.id.list)
  RecyclerView recyclerView;

  @BindView(R.id.swipe_refresh)
  SwipeRefreshLayout swipeRefreshLayout;

  private String account;

  private RepoListAdapter adapter;

  public static RepoListFragment newInstance(@NonNull String account) {
    Bundle args = new Bundle();
    args.putString(RepoListActivity.BundleKey.ACCOUNT.name(), account);
    RepoListFragment fragment = new RepoListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @NonNull
  @Override
  protected RepoListContract.View getViewContract() {
    return this;
  }

  @NonNull
  @Override
  protected RepoListPresenter getPresenter() {
    return new RepoListPresenter(GetRepositories.Factory.get(getContext()), RepoViewModelMapper.Factory.INSTANCE.get());
  }

  @Override
  protected int getLayoutRes() {
    return R.layout.fragment_repo_list;
  }

  @NonNull
  @Override
  protected Unbinder bindView(@NonNull View fragmentView) {
    return ButterKnife.bind(this, fragmentView);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ofNullable(getArguments()).ifPresent(bundle -> {
      account = bundle.getString(RepoListActivity.BundleKey.ACCOUNT.name());
      Timber.d("account: %s", account);
      initRecycler();
      initSwipe();
    });
  }

  private void initRecycler() {
    ofNullable(getActivity()).ifPresent(activity -> {
      adapter = new RepoListAdapter(activity);
      recyclerView.setLayoutManager(new LinearLayoutManager(activity));
      recyclerView.setAdapter(adapter);
      presenter.getRepositoryList(account);
    });
  }

  private void initSwipe() {
    swipeRefreshLayout.setOnRefreshListener(() -> presenter.refreshRepositoryList(account));
  }

  @Override
  public void showRepositoryList(@NonNull List<RepoViewModel> repositoryList) {
    if (swipeRefreshLayout.isRefreshing()) {
      swipeRefreshLayout.setRefreshing(false);
    }
    adapter.setViewModels(repositoryList);
  }

  @Override
  public void showProgressBar() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgressBar() {
    progressBar.setVisibility(View.GONE);
  }

  @Override
  public void showError(@NonNull String message) {
    message = isEmpty(message)
        ? getString(R.string.default_error_while_getting_repositories)
        : message;
    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
  }
}
