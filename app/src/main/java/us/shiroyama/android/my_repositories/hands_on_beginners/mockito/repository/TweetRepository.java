package us.shiroyama.android.my_repositories.hands_on_beginners.mockito.repository;


import android.support.annotation.NonNull;

import java.util.List;

import us.shiroyama.android.my_repositories.hands_on_beginners.mockito.entity.Tweet;

/**
 * Tweet Repository
 *
 * @author Fumihiko Shiroyama
 */

public class TweetRepository {
  @NonNull
  private final LocalTweetDataSource localDataSource;

  public TweetRepository(@NonNull LocalTweetDataSource localDataSource) {
    this.localDataSource = localDataSource;
  }

  @NonNull
  public List<Tweet> getTimeline() {
    return localDataSource.getTimeline();
  }
}
