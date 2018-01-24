package us.shiroyama.android.my_repositories.domain.model;

import android.support.annotation.NonNull;

import org.threeten.bp.ZonedDateTime;

/**
 * Domain Model for GitHub Repository
 *
 * @author Fumihiko Shiroyama
 */

public class Repository {
  private final long id;
  @NonNull
  private final String name;
  @NonNull
  private final String fullName;
  @NonNull
  private final String htmlUrl;
  private final boolean isPrivate;
  @NonNull
  private final String description;
  @NonNull
  private final Account owner;
  @NonNull
  private final ZonedDateTime createdAt;
  @NonNull
  private final ZonedDateTime updatedAt;
  @NonNull
  private final ZonedDateTime pushedAt;

  public Repository(long id, @NonNull String name, @NonNull String fullName, @NonNull String htmlUrl, boolean isPrivate, @NonNull String description, @NonNull Account owner, @NonNull ZonedDateTime createdAt, @NonNull ZonedDateTime updatedAt, @NonNull ZonedDateTime pushedAt) {
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

  @NonNull
  public String getName() {
    return name;
  }

  @NonNull
  public String getFullName() {
    return fullName;
  }

  @NonNull
  public String getHtmlUrl() {
    return htmlUrl;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  @NonNull
  public String getDescription() {
    return description;
  }

  @NonNull
  public Account getOwner() {
    return owner;
  }

  @NonNull
  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  @NonNull
  public ZonedDateTime getUpdatedAt() {
    return updatedAt;
  }

  @NonNull
  public ZonedDateTime getPushedAt() {
    return pushedAt;
  }
}
