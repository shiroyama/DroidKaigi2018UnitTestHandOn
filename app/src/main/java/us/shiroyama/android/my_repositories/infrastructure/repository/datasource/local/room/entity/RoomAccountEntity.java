package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Room Entity for GitHub Account
 *
 * @author Fumihiko Shiroyama
 */

@Entity(
    tableName = "account",
    indices = {@Index(value = {"login"}, unique = true)}
)
public class RoomAccountEntity {
  @PrimaryKey
  private final long id;

  @ColumnInfo(name = "login")
  private final String login;

  @ColumnInfo(name = "avatar_url")
  private final String avatarUrl;

  @ColumnInfo(name = "url")
  private final String url;

  public RoomAccountEntity(long id, String login, String avatarUrl, String url) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RoomAccountEntity that = (RoomAccountEntity) o;

    if (id != that.id) return false;
    if (login != null ? !login.equals(that.login) : that.login != null) return false;
    if (avatarUrl != null ? !avatarUrl.equals(that.avatarUrl) : that.avatarUrl != null)
      return false;
    return url != null ? url.equals(that.url) : that.url == null;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (login != null ? login.hashCode() : 0);
    result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
    result = 31 * result + (url != null ? url.hashCode() : 0);
    return result;
  }
}
