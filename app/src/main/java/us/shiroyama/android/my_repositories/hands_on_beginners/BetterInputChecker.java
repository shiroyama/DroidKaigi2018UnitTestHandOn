package us.shiroyama.android.my_repositories.hands_on_beginners;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Simple Input Checker with {@link TextUtils}
 *
 * @author Fumihiko Shiroyama
 */

class BetterInputChecker {
  boolean isValid(@NonNull String text) {
    if (TextUtils.isEmpty(text))
      throw new IllegalArgumentException("cannot be blank");
    if (text.length() < 4) return false;
    if (!text.matches("[a-zA-z0-4]+")) return false;
    return true;
  }
}
