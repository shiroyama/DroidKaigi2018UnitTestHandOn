package us.shiroyama.android.my_repositories.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Base View
 *
 * @author Fumihiko Shiroyama
 */

public interface BaseView {
  void startActivity(@NonNull Intent intent);

  void startActivityForResult(@NonNull Intent intent, int requestCode);

  void finishActivity();

  @Nullable
  Context getContext();

  @Nullable
  FragmentActivity getActivity();
}
