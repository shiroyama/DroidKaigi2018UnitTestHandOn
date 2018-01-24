package us.shiroyama.android.my_repositories.presentation.view.activity;

import android.os.Bundle;

import us.shiroyama.android.my_repositories.R;
import us.shiroyama.android.my_repositories.presentation.view.fragment.AccountInputFragment;


/**
 * Account Input Screen Activity
 *
 * @author Fumihiko Shiroyama
 */

public class AccountInputActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.container);
    replaceFragment(AccountInputFragment.newInstance(), R.id.container);
  }

}

