package us.shiroyama.android.my_repositories.infrastructure.entity;

import com.google.gson.annotations.SerializedName;

import org.threeten.bp.ZonedDateTime;

/**
 * Entity for GitHub Repository
 *
 * @author Fumihiko Shiroyama
 */

public class RepositoryEntity {
  @SerializedName("id")
  private long id;

  @SerializedName("name")
  private String name;

  @SerializedName("full_name")
  private String fullName;

  @SerializedName("html_url")
  private String htmlUrl;

  @SerializedName("private")
  private boolean isPrivate;

  @SerializedName("description")
  private String description;

  @SerializedName("owner")
  private AccountEntity owner;

  @SerializedName("created_at")
  private ZonedDateTime createdAt;

  @SerializedName("updated_at")
  private ZonedDateTime updatedAt;

  @SerializedName("pushed_at")
  private ZonedDateTime pushedAt;

  public RepositoryEntity() {
  }

  public RepositoryEntity(long id, String name, String fullName, String htmlUrl, boolean isPrivate, String description, AccountEntity owner, ZonedDateTime createdAt, ZonedDateTime updatedAt, ZonedDateTime pushedAt) {
    this.id = id;
    this.name = name;
    this.fullName = fullName;
    this.htmlUrl = htmlUrl;
    this.isPrivate = isPrivate;
    this.description = description;
    this.owner = owner;
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

  public AccountEntity getOwner() {
    return owner;
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
