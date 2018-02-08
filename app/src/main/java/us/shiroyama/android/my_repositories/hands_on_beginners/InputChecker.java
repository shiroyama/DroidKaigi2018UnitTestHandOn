package us.shiroyama.android.my_repositories.hands_on_beginners;

import android.support.annotation.NonNull;

/**
 * Simple Input Checker
 *
 * @author Fumihiko Shiroyama
 */

class InputChecker {
  boolean isValid(@NonNull String text) {
    if (text.length() < 4) return false;
    if (!text.matches("[a-zA-Z0-9]+")) return false;
    return true;
  }
}
