package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class Day2Test {

    private val dayTwo = Day2()

    @Test
    fun testPartOne() {
        assertThat(dayTwo.partOne(), `is`(8))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwo.partTwo(), `is`(2286))
    }

    @Test
    fun testParseGame() {
        assertThat(dayTwo.parseGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"), `is`(
            Day2.Game(1, listOf(Day2.CubeSet(4, 0, 3), Day2.CubeSet(1, 2, 6), Day2.CubeSet(0, 2, 0)))
        ))
        assertThat(dayTwo.parseGame("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"), `is`(
            Day2.Game(2, listOf(Day2.CubeSet(0, 2, 1), Day2.CubeSet(1, 3, 4), Day2.CubeSet(0, 1, 1)))
        ))
        assertThat(dayTwo.parseGame("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"), `is`(
            Day2.Game(3, listOf(Day2.CubeSet(20, 8, 6), Day2.CubeSet(4, 13, 5), Day2.CubeSet(1, 5, 0)))
        ))
        assertThat(dayTwo.parseGame("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"), `is`(
            Day2.Game(4, listOf(Day2.CubeSet(3, 1, 6), Day2.CubeSet(6, 3, 0), Day2.CubeSet(14, 3, 15)))
        ))
        assertThat(dayTwo.parseGame("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"), `is`(
            Day2.Game(5, listOf(Day2.CubeSet(6, 3, 1), Day2.CubeSet(1, 2, 2)))
        ))
    }

    @Test
    fun testMinimumCubeSet() {
        assertThat(dayTwo.games[0].minimumCubeSet(), `is`(Day2.CubeSet(4, 2, 6)))
        assertThat(dayTwo.games[1].minimumCubeSet(), `is`(Day2.CubeSet(1, 3, 4)))
        assertThat(dayTwo.games[2].minimumCubeSet(), `is`(Day2.CubeSet(20, 13, 6)))
        assertThat(dayTwo.games[3].minimumCubeSet(), `is`(Day2.CubeSet(14, 3, 15)))
        assertThat(dayTwo.games[4].minimumCubeSet(), `is`(Day2.CubeSet(6, 3, 2)))
    }

    @Test
    fun testMinimumCubeSetPower() {
        assertThat(dayTwo.games[0].minimumCubeSet().power(), `is`(48))
        assertThat(dayTwo.games[1].minimumCubeSet().power(), `is`(12))
        assertThat(dayTwo.games[2].minimumCubeSet().power(), `is`(1560))
        assertThat(dayTwo.games[3].minimumCubeSet().power(), `is`(630))
        assertThat(dayTwo.games[4].minimumCubeSet().power(), `is`(36))
    }
}
