package us.shiroyama.android.my_repositories.infrastructure.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Entity for GitHub Error
 *
 * @author Fumihiko Shiroyama
 */

public class GitHubErrorEntity {
  @SerializedName("message")
  private String message;

  @SerializedName("documentation_url")
  private String documentationUrl;

  public String getMessage() {
    return message;
  }

  public String getDocumentationUrl() {
    return documentationUrl;
  }
}
