package us.shiroyama.android.my_repositories.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Unbinder;
import us.shiroyama.android.my_repositories.common.BusProvider;
import us.shiroyama.android.my_repositories.presentation.presenter.BasePresenter;
import us.shiroyama.android.my_repositories.presentation.view.BaseView;

import static com.annimon.stream.Optional.ofNullable;

/**
 * Base Fragment
 *
 * @author Fumihiko Shiroyama
 */

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends Fragment {
  protected P presenter;

  private Unbinder unbinder;

  @NonNull
  protected abstract V getViewContract();

  @NonNull
  protected abstract P getPresenter();

  @LayoutRes
  protected abstract int getLayoutRes();

  @NonNull
  protected abstract Unbinder bindView(@NonNull View fragmentView);

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    presenter = getPresenter();
    presenter.onAttach();
    presenter.setView(getViewContract());
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(getLayoutRes(), container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = bindView(view);
    presenter.onViewCreated();
  }

  @Override
  public void onResume() {
    super.onResume();
    BusProvider.INSTANCE.get().register(presenter);
    presenter.onResume();
  }

  @Override
  public void onPause() {
    presenter.onPause();
    BusProvider.INSTANCE.get().unregister(presenter);
    super.onPause();
  }

  @Override
  public void onDestroyView() {
    presenter.onDestroyView();
    unbinder.unbind();
    super.onDestroyView();
  }

  @Override
  public void onDetach() {
    presenter.onDetach();
    super.onDetach();
  }

  public void finishActivity() {
    ofNullable(getActivity()).ifPresent(Activity::finish);
  }
}
