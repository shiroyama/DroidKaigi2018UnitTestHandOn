package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import org.threeten.bp.ZonedDateTime;

/**
 * Room Entity for GitHub Repository
 *
 * @author Fumihiko Shiroyama
 */

@Entity(
    tableName = "repository",
    indices = {@Index(value = {"account_id"})}
)
public class RoomRepositoryEntity {
  @PrimaryKey
  private final long id;

  @ColumnInfo(name = "name")
  private final String name;

  @ColumnInfo(name = "full_name")
  private final String fullName;

  @ColumnInfo(name = "html_url")
  private final String htmlUrl;

  @ColumnInfo(name = "is_private")
  private final boolean isPrivate;

  @ColumnInfo(name = "description")
  private final String description;

  @ColumnInfo(name = "account_id")
  private final long accountId;

  @ColumnInfo(name = "created_at")
  private final ZonedDateTime createdAt;

  @ColumnInfo(name = "updated_at")
  private final ZonedDateTime updatedAt;

  @ColumnInfo(name = "pushed_at")
  private final ZonedDateTime pushedAt;

  public RoomRepositoryEntity(long id, String name, String fullName, String htmlUrl, boolean isPrivate, String description, long accountId, ZonedDateTime createdAt, ZonedDateTime updatedAt, ZonedDateTime pushedAt) {
    this.id = id;
    this.name = name;
    this.fullName = fullName;
    this.htmlUrl = htmlUrl;
    this.isPrivate = isPrivate;
    this.description = description;
    this.accountId = accountId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.pushedAt = pushedAt;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getFullName() {
    return fullName;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public String getDescription() {
    return description;
  }

  public long getAccountId() {
    return accountId;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public ZonedDateTime getUpdatedAt() {
    return updatedAt;
  }

  public ZonedDateTime getPushedAt() {
    return pushedAt;
  }
}
