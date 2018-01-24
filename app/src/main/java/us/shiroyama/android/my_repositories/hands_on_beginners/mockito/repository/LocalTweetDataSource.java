package us.shiroyama.android.my_repositories.hands_on_beginners.mockito.repository;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import us.shiroyama.android.my_repositories.hands_on_beginners.mockito.entity.Tweet;


/**
 * Local Data Source Interface
 *
 * @author Fumihiko Shiroyama
 */

public interface LocalTweetDataSource {
  @NonNull
  List<Tweet> getTimeline();

  /**
   * Local Data Source using SQLite
   */
  class SQLiteTweetDataSource implements LocalTweetDataSource {
    @NonNull
    @Override
    public List<Tweet> getTimeline() {
      List<Tweet> tweets = new ArrayList<>();

      int max = new Random().nextInt(10) + 1;
      for (int i = 0; i < max; i++) {
        tweets.add(Tweet.bodyOf(UUID.randomUUID().toString()));
      }

      return tweets;
    }
  }

  /**
   * Local Data Source that returns fixed mock data
   */
  class MockTweetDataSource implements LocalTweetDataSource {
    @NonNull
    @Override
    public List<Tweet> getTimeline() {
      return Arrays.asList(
          Tweet.bodyOf("foo"),
          Tweet.bodyOf("bar"),
          Tweet.bodyOf("baz")
      );
    }
  }
}
