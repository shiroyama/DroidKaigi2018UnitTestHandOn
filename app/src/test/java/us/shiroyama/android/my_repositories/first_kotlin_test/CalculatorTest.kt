@file:Suppress("FunctionName")

package us.shiroyama.android.my_repositories.first_kotlin_test

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by numa08 on 2018/02/08.
 */
class CalculatorTest {

    /**
     *
     * Sndbox でやったのと同じような内容のテスト。
     * テストメソッドを日本語にすることができるので、 @numa08 のチームでは
     * よく、そうしています。
     * */
    @Test
    fun 足し算をするテスト() {
        val a = 1
        val b = 2
        val sum = a + b
        // これは org.junit.Assert.assertThat を使っている. そのため is で
        // kotlin ではバッククォート(`)が必要になる。
        assertThat(sum, `is`(3))
    }

}