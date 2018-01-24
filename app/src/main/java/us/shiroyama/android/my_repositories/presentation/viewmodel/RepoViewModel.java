package us.shiroyama.android.my_repositories.presentation.viewmodel;

import android.support.annotation.NonNull;

import org.threeten.bp.ZonedDateTime;

/**
 * A View Model for GitHub Repository
 *
 * @author Fumihiko Shiroyama
 */

public class RepoViewModel {
  @NonNull
  private final String repoName;

  @NonNull
  private final String htmlUrl;

  @NonNull
  private final String description;

  @NonNull
  private final String ownerName;

  @NonNull
  private final String ownerAvatarUrl;

  @NonNull
  private final ZonedDateTime updatedAt;

  public RepoViewModel(@NonNull String repoName, @NonNull String htmlUrl, @NonNull String description, @NonNull String ownerName, @NonNull String ownerAvatarUrl, @NonNull ZonedDateTime updatedAt) {
    this.repoName = repoName;
    this.htmlUrl = htmlUrl;
    this.description = description;
    this.ownerName = ownerName;
    this.ownerAvatarUrl = ownerAvatarUrl;
    this.updatedAt = updatedAt;
  }

  @NonNull
  public String getRepoName() {
    return repoName;
  }

  @NonNull
  public String getHtmlUrl() {
    return htmlUrl;
  }

  @NonNull
  public String getDescription() {
    return description;
  }

  @NonNull
  public String getOwnerName() {
    return ownerName;
  }

  @NonNull
  public String getOwnerAvatarUrl() {
    return ownerAvatarUrl;
  }

  @NonNull
  public ZonedDateTime getUpdatedAt() {
    return updatedAt;
  }
}
