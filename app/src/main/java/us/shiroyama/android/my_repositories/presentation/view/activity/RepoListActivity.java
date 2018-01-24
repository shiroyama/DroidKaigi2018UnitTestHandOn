package us.shiroyama.android.my_repositories.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import us.shiroyama.android.my_repositories.R;
import us.shiroyama.android.my_repositories.presentation.view.fragment.RepoListFragment;


/**
 * Repository List Screen Activity
 *
 * @author Fumihiko Shiroyama
 */

public class RepoListActivity extends BaseActivity {
  public enum BundleKey {
    ACCOUNT
  }

  @NonNull
  public static Intent newIntent(@NonNull Context context, @NonNull String account) {
    Intent intent = new Intent(context, RepoListActivity.class);
    intent.putExtra(BundleKey.ACCOUNT.name(), account);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.container);

    String account = getIntent().getStringExtra(BundleKey.ACCOUNT.name());
    replaceFragment(RepoListFragment.newInstance(account), R.id.container);
  }

}

