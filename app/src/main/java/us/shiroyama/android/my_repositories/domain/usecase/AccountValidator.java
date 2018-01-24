package us.shiroyama.android.my_repositories.domain.usecase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import us.shiroyama.android.my_repositories.R;
import us.shiroyama.android.my_repositories.common.Singleton;

/**
 * Account Validator
 *
 * @author Fumihiko Shiroyama
 */

public class AccountValidator {
  public Result validate(@Nullable String account) {
    if (TextUtils.isEmpty(account)) {
      return new Result(false, R.string.error_field_required);
    }
    if (!isValid(account)) {
      return new Result(false, R.string.error_invalid_account);
    }
    return new Result(true, 0);
  }

  private boolean isValid(@NonNull String account) {
    return account.length() > 0 && account.matches("[a-zA-Z0-9]+");
  }

  public enum Factory implements Singleton<AccountValidator> {
    INSTANCE;

    private final AccountValidator instance = new AccountValidator();

    @NonNull
    @Override
    public AccountValidator get() {
      return instance;
    }
  }

  public static class Result {
    private final boolean isValid;

    @StringRes
    private final int reasonRes;

    public Result(boolean isValid, int reasonRes) {
      this.isValid = isValid;
      this.reasonRes = reasonRes;
    }

    public boolean isValid() {
      return isValid;
    }

    public int getReasonRes() {
      return reasonRes;
    }
  }
}
