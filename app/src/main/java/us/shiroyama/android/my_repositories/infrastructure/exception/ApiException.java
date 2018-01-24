package us.shiroyama.android.my_repositories.infrastructure.exception;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import us.shiroyama.android.my_repositories.infrastructure.entity.GitHubErrorEntity;

/**
 * API Exception
 *
 * @author Fumihiko Shiroyama
 */

public class ApiException extends Exception {
  @Nullable
  private GitHubErrorEntity gitHubErrorEntity;

  public ApiException() {
    this(null, null, null);
  }

  public ApiException(@NonNull GitHubErrorEntity gitHubErrorEntity) {
    this(null, null, gitHubErrorEntity);
  }

  public ApiException(String message, Throwable cause, @Nullable GitHubErrorEntity gitHubErrorEntity) {
    super(message, cause);
    this.gitHubErrorEntity = gitHubErrorEntity;
  }

  @Nullable
  public GitHubErrorEntity getGitHubErrorEntity() {
    return gitHubErrorEntity;
  }
}
