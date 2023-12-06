package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.hasItems
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class Day6Test {

    private val day6 = Day6()

    @Test
    fun testPartOne() {
        assertThat(day6.partOne(), `is`(288))
    }

    @Test
    fun testPartTwo() {
        assertThat(day6.partTwo(), `is`(71503))
    }

    @Test
    fun testWinningHoldTimes() {
        assertThat(Day6.Race(7, 9).winningHoldTimes(), contains(2, 3, 4, 5))
        assertThat(Day6.Race(15, 40).winningHoldTimes(), contains(4, 5, 6, 7, 8, 9, 10, 11))
        assertThat(Day6.Race(30, 200).winningHoldTimes(), contains(11, 12, 13, 14, 15, 16, 17, 18, 19))

        assertThat(Day6.Race(71530, 940200).winningHoldTimes(), hasSize(71503))
        assertThat(Day6.Race(71530, 940200).winningHoldTimes(), hasItems(14, 71516))
    }
}
