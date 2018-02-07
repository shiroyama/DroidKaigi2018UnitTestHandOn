package us.shiroyama.android.my_repositories.hands_on_beginners.async;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

/**
 * Local Unit Test for {@link AsyncFetcher}
 *
 * @author Fumihiko Shiroyama
 */

public class AsyncFetcherTest {
  private Fetcher<String> fetcher;
  private AsyncFetcher<String> asyncFetcher;
  private CountDownLatch latch;

  /**
   * 非同期処理が別スレッドで実行されている間にテストケースを抜けてしまうと、そのテストケースは意図せず「成功」とみなされてしまう。
   * 非同期処理が完了してコールバックをもらうまでスレッドを待機させる処理を書いてみよう。
   * <p>
   * ヒント: {@link CountDownLatch} を使うことで簡単に待ち合わせができる。
   */
  @Before
  public void setUp() throws Exception {
    fetcher = spy(new Fetcher.StringFetcher());
    asyncFetcher = new AsyncFetcher<>(fetcher);
    latch = new CountDownLatch(1);
  }

  /**
   * 非同期処理が成功で終わる場合を検証してみよう。
   * <p>
   * ヒント: {@link CountDownLatch#await()} で待たせた処理は {@link CountDownLatch#countDown()} の結果がゼロになることで再開することができる。
   */
  @Test
  public void fetch_success() throws Exception {
  }

  /**
   * 非同期処理が失敗で終わる場合を検証してみよう。
   * {@link CountDownLatch} を使う部分は成功パターンと同じだが、{@link Fetcher#fetch()} が失敗になるように工夫が必用だ。
   * <p>
   * ヒント: {@link Mockito#doThrow(Throwable...)} を使うと戻り値がvoidのメソッドの結果を変えることができる。
   */
  @Test
  public void fetch_failure() throws Exception {
  }

  private void log(@NonNull String msg) {
    System.out.println(msg);
  }

}