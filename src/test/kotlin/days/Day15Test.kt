package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class Day15Test {

    private val day15 = Day15()

    @Test
    fun testPartOne() {
        assertThat(day15.partOne(), `is`(1320))
    }

    @Test
    fun testPartTwo() {
        assertThat(day15.partTwo(), `is`(145))
    }

    @Test
    fun testHashAlgorithm() {
        assertThat(day15.hash("HASH"), `is`(52))
    }
}
