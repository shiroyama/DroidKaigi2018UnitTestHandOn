package us.shiroyama.android.my_repositories.presentation.view.activity;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Base Activity
 *
 * @author Fumihiko Shiroyama
 */

public abstract class BaseActivity extends AppCompatActivity {

  protected final void replaceFragment(@NonNull Fragment fragment, @IdRes int containerId) {
    final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(containerId, fragment, fragment.getClass().getSimpleName());
    ft.commit();
  }

}
