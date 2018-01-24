package us.shiroyama.android.my_repositories.infrastructure.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Entity for GitHub Account
 *
 * @author Fumihiko Shiroyama
 */

public class AccountEntity {
  @SerializedName("id")
  private long id;

  @SerializedName("login")
  private String login;

  @SerializedName("avatar_url")
  private String avatarUrl;

  @SerializedName("url")
  private String url;

  public AccountEntity() {
  }

  public AccountEntity(long id, String login, String avatarUrl, String url) {
    this.id = id;
    this.login = login;
    this.avatarUrl = avatarUrl;
    this.url = url;
  }

  public long getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public String getUrl() {
    return url;
  }
}
