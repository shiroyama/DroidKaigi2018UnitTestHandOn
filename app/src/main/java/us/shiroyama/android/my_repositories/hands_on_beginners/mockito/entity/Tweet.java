package us.shiroyama.android.my_repositories.hands_on_beginners.mockito.entity;

import android.support.annotation.NonNull;

/**
 * Tweet Entity
 *
 * @author Fumihiko Shiroyama
 */

public class Tweet {
  @NonNull
  public final String body;

  private Tweet(@NonNull String body) {
    this.body = body;
  }

  @NonNull
  public static Tweet bodyOf(@NonNull String body) {
    return new Tweet(body);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tweet tweet = (Tweet) o;

    return body.equals(tweet.body);
  }

  @Override
  public int hashCode() {
    return body.hashCode();
  }
}
