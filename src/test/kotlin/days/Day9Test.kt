package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class Day9Test {

    private val day9 = Day9()

    @Test
    fun testPartOne() {
        assertThat(day9.partOne(), `is`(114))
    }

    @Test
    fun testPartTwo() {
        assertThat(day9.partTwo(), `is`(2))
    }

    @Test
    fun testExtrapolateNext() {
        assertThat(day9.extrapolateNext(listOf(0, 3, 6, 9, 12, 15)), `is`(18))
        assertThat(day9.extrapolateNext(listOf(1, 3, 6, 10, 15, 21)), `is`(28))
        assertThat(day9.extrapolateNext(listOf(10, 13, 16, 21, 30, 45)), `is`(68))
    }

    @Test
    fun testExtrapolatePrevious() {
        assertThat(day9.extrapolatePrevious(listOf(0, 3, 6, 9, 12, 15)), `is`(-3))
        assertThat(day9.extrapolatePrevious(listOf(1, 3, 6, 10, 15, 21)), `is`(0))
        assertThat(day9.extrapolatePrevious(listOf(10, 13, 16, 21, 30, 45)), `is`(5))
    }
}
