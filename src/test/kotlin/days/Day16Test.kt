package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class Day16Test {

    private val day16 = Day16()

    @Test
    fun testPartOne() {
        assertThat(day16.partOne(), `is`(46))
    }

    @Test
    fun testPartTwo() {
        assertThat(day16.partTwo(), `is`(51))
    }
}
