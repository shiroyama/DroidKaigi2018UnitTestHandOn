package us.shiroyama.android.my_repositories.hands_on_beginners.mockito.repository;


import android.support.annotation.NonNull;

import java.util.List;

import us.shiroyama.android.my_repositories.hands_on_beginners.mockito.converter.TweetConverter;
import us.shiroyama.android.my_repositories.hands_on_beginners.mockito.entity.Tweet;

/**
 * Tweet Repository with {@link TweetConverter}
 *
 * @author Fumihiko Shiroyama
 */

public class TweetRepositoryWithConverter {
  @NonNull
  private final LocalTweetDataSource localDataSource;

  @NonNull
  private final TweetConverter converter;

  public TweetRepositoryWithConverter(@NonNull LocalTweetDataSource localDataSource, @NonNull TweetConverter converter) {
    this.localDataSource = localDataSource;
    this.converter = converter;
  }

  @NonNull
  public List<Tweet> getTimeline() {
    return localDataSource.getTimeline();
  }

  @NonNull
  public List<String> getTimelineBody() {
    return converter.convertList(localDataSource.getTimeline());
  }
}
