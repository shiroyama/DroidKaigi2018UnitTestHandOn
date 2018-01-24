package us.shiroyama.android.my_repositories.hands_on_beginners.mockito.converter;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;

import java.util.List;

import us.shiroyama.android.my_repositories.hands_on_beginners.mockito.entity.Tweet;

/**
 * Tweet Converter
 *
 * @author Fumihiko Shiroyama
 */

public class TweetConverter {
  @NonNull
  public String convert(@NonNull Tweet tweet) {
    return tweet.body;
  }

  @NonNull
  public List<String> convertList(@NonNull List<Tweet> tweets) {
    return Stream.of(tweets).map(tweet -> tweet.body).toList();
  }
}
