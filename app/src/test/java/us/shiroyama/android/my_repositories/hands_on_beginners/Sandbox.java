package us.shiroyama.android.my_repositories.hands_on_beginners;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

/**
 * Sandbox for Unit Test
 *
 * @author Fumihiko Shiroyama
 */

public class Sandbox {
  @Before
  public void setUp() throws Exception {
  }

  /**
   * 数字のアサーションに挑戦してみましょう！
   * <p>
   * 1) 1 + 1 = 2
   * 2) 123 > 100
   * <p>
   * これらを検証するようなテストケースを自由に書いてみてください。
   * <p>
   * ヒント: <code>Assertions#assertThat( テスト対象 ).isEqualTo( 期待値 )</code> のように書きます。
   * ヒント: <code>isGreaterThan</code> や <code>isLessThan</code> など、数字に関する様々なアサーションを試してみましょう。
   */
  @Test
  public void simple_assertion_for_numbers() throws Exception {
    Assertions.assertThat(123).isEqualTo(123);

    Assertions.assertThat(1 + 1).isEqualTo(2);

    Assertions.assertThat(123)
        .isNotZero()
        .isGreaterThan(100)
        .isLessThan(234);
  }

  /**
   * 文字列のアサーションに挑戦してみましょう！
   * <p>
   * 1) "Alice" は "Bob" ではない
   * 2) "Alice" は "Al" から始まる
   * 3) "Alice" は大文字小文字を区別しなければ "alice" と同じ
   * <p>
   * これらを検証するようなテストケースを自由に書いてみてください。
   * <p>
   * ヒント: <code>isNotEmpty</code> や <code>startsWith</code> など、文字列に関する様々なアサーションを試してみましょう。
   */
  @Test
  public void simple_assertion_for_strings() throws Exception {
    Assertions.assertThat("Alice").isEqualTo("Alice");

    Assertions.assertThat("Alice")
        .isNotEmpty()
        .isNotEqualTo("Bob");

    Assertions.assertThat("Alice")
        .startsWith("Al")
        .isEqualToIgnoringCase("alice");
  }
}
