package us.shiroyama.android.my_repositories.hands_on_beginners;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Local Unit Test for {@link InputChecker}
 *
 * @author Fumihiko Shiroyama
 */

public class InputCheckerTest {
  private InputChecker inputChecker;

  /**
   * ここに {@link InputChecker} の初期化処理を書いて各テストケースで共用しよう。
   */
  @Before
  public void setUp() throws Exception {
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
   * 不正な入力（空文字列）で<code>false</code>を受け取るテストケースを書いてみよう。
   * <p>
   * 例: <code>String input = "";</code>
   */
  @Test
  public void isValid_inputBlank_resultsFalse() throws Exception {
    String input = "";
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
   * 不正な入力（null）で<code>NullPointerException</code>が上がることを確認するテストケースを書いてみよう。
   * <p>
   * 例: <code>String input = null;</code>
   * <p>
   * ヒント: <code>@Test(expected = 例外.class)</code>
   */
  @Test
  public void isValid_inputNull_resultsNPE() throws Exception {
    String input = null;
  }

}