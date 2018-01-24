package us.shiroyama.android.my_repositories.hands_on_beginners.mockito.repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import us.shiroyama.android.my_repositories.hands_on_beginners.mockito.entity.Tweet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Local Unit Test for {@link TweetRepository}
 *
 * @author Fumihiko Shiroyama
 */

public class TweetRepositoryTest {
  private TweetRepository tweetRepository;

  /**
   * {@link TweetRepository} は {@link LocalTweetDataSource} に依存している。
   * テストのやり方は色々あるが、ここでは {@link Mockito#mock(Class)} を利用してみよう。
   * <p>
   * ヒント: {@link Mockito#when(Object)} と <code>thenReturn()</code> を組み合わせると、メソッドのスタブを作ることができる。
   */
  @Before
  public void setUp() throws Exception {
    LocalTweetDataSource dataSource = mock(LocalTweetDataSource.class);
    when(dataSource.getTimeline()).thenReturn(
        Arrays.asList(Tweet.bodyOf("foo"), Tweet.bodyOf("bar"), Tweet.bodyOf("baz"))
    );
    tweetRepository = new TweetRepository(dataSource);
  }

  /**
   * スタブで定義した返り値を検証するテストケースを書いてみよう。
   * Listの検証で学んだアサーションをここでも有効活用しよう。
   */
  @Test
  public void getTimeline() throws Exception {
    List<Tweet> tweets = tweetRepository.getTimeline();
    assertThat(tweets)
        .isNotEmpty()
        .hasSize(3)
        .containsExactly(Tweet.bodyOf("foo"), Tweet.bodyOf("bar"), Tweet.bodyOf("baz"));
  }

}