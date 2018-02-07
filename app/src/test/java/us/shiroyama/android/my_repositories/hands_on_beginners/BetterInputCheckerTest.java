package us.shiroyama.android.my_repositories.hands_on_beginners;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Local Unit Test for {@link BetterInputChecker}
 *
 * @author Fumihiko Shiroyama
 */

/**
 * Androidフレームワークのコードを使ったクラスのテストに挑戦してみましょう。
 * <p>
 * ヒント: {@link Robolectric} を使うとAndroidフレームワークのコードをLocal Unit Testで模すことができます。
 * ヒント: クラスを<code>@RunWith(RobolectricTestRunner.class)</code>アノテーションで修飾すると {@link Robolectric} を使うことができます。
 */
public class BetterInputCheckerTest {
  private BetterInputChecker inputChecker;

  @Before
  public void setUp() throws Exception {
    inputChecker = new BetterInputChecker();
  }

  /**
   * 正しい入力で<code>true</code>を受け取るテストケースを書いてみよう。
   * <p>
   * 例: <code>String input = "srym";</code>
   */
  @Test
  public void isValid() throws Exception {
    String input = "srym";
  }

  /**
   * 不正な入力（記号や半角スペースが含まれた文字列）で<code>false</code>を受け取るテストケースを書いてみよう。
   * <p>
   * 例: <code>String input = "abc$";</code>
   */
  @Test
  public void isValid_inputIllegalCharacters_resultsFalse() throws Exception {
    String input = "abc$";
  }

  /**
   * 不正な入力（規定文字数より少ない文字列）で<code>false</code>を受け取るテストケースを書いてみよう。
   * <p>
   * 例: <code>String input = "abc";</code>
   */
  @Test
  public void isValid_inputLessThan4_resultsFalse() throws Exception {
    String input = "abc";
  }

  /**
   * 不正な入力（null）で<code>IllegalArgumentException</code>が上がることを確認するテストケースを書いてみよう。
   * <p>
   * 例: <code>String input = null;</code>
   * <p>
   * ヒント: <code>@Test(expected = 例外.class)</code>
   */
  @Test
  public void isValid_inputNull_resultsIllegalArgumentException() throws Exception {
    String input = null;
  }

  /**
   * 不正な入力（空文字列）で<code>IllegalArgumentException</code>が上がることを確認するテストケースを書いてみよう。
   * <p>
   * 例: <code>String input = null;</code>
   * <p>
   * ヒント: <code>@Test(expected = 例外.class)</code>
   */
  @Test
  public void isValid_inputBlank_resultsIllegalArgumentException() throws Exception {
    String input = "";
  }

}