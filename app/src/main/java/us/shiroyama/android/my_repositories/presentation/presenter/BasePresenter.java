package us.shiroyama.android.my_repositories.presentation.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import us.shiroyama.android.my_repositories.presentation.view.BaseView;

/**
 * Base Presenter
 *
 * @author Fumihiko Shiroyama
 */

public abstract class BasePresenter<V extends BaseView> {
  protected V view;

  public void setView(V view) {
    this.view = view;
  }

  public void onAttach() {
    // NOP
  }

  public void onViewCreated() {
    // NOP
  }

  public void onResume() {
    // NOP
  }

  public void onPause() {
    // NOP
  }

  public void onDestroyView() {
    // NOP
  }

  public void onDetach() {
    // NOP
  }

  public void startActivity(@NonNull Intent intent) {
    view.startActivity(intent);
  }

  public void startActivityForResult(@NonNull Intent intent, int requestCode) {
    view.startActivityForResult(intent, requestCode);
  }

  public void finishActivity() {
    view.finishActivity();
  }

  @Nullable
  public Context getContext() {
    return view.getContext();
  }

  @Nullable
  public FragmentActivity getActivity() {
    return view.getActivity();
  }
}