package us.shiroyama.android.my_repositories.domain.model;

import android.support.annotation.NonNull;

/**
 * Domain Model for GitHub Account
 *
 * @author Fumihiko Shiroyama
 */

public class Account {
  private final long id;
  @NonNull
  private final String loginId;
  @NonNull
  private final String avatarUrl;
  @NonNull
  private final String url;

  public Account(long id, @NonNull String loginId, @NonNull String avatarUrl, @NonNull String url) {
    this.id = id;
    this.loginId = loginId;
    this.avatarUrl = avatarUrl;
    this.url = url;
  }

  public long getId() {
    return id;
  }

  @NonNull
  public String getLoginId() {
    return loginId;
  }

  @NonNull
  public String getAvatarUrl() {
    return avatarUrl;
  }

  @NonNull
  public String getUrl() {
    return url;
  }
}
