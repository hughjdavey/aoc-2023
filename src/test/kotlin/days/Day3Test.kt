package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import xyz.hughjd.aocutils.Coords.Coord

class Day3Test {

    private val day3 = Day3()

    @Test
    fun testPartOne() {
        assertThat(day3.partOne(), `is`(4361))
    }

    @Test
    fun testPartTwo() {
        assertThat(day3.partTwo(), `is`(467835))
    }

    @Test
    fun testGetValidAdjacents() {
        val slice617 = Day3.SchematicMatch("617", listOf(Coord(0, 4), Coord(1, 4), Coord(2, 4)))
        assertThat(slice617.getValidAdjacents(day3.bounds), hasSize(9))

        val slice592 = Day3.SchematicMatch("592", listOf(Coord(2, 6), Coord(3, 6), Coord(4, 6)))
        assertThat(slice592.getValidAdjacents(day3.bounds), hasSize(12))

        val slice664 = Day3.SchematicMatch("664", listOf(Coord(1, 9), Coord(2, 9), Coord(3, 9)))
        assertThat(slice664.getValidAdjacents(day3.bounds), hasSize(7))
    }

    @Test
    fun testGetSchematicMatches() {
        val numbers = day3.schematicMatches(Regex("\\d+"))
        assertThat(numbers, hasSize(10))
        assertThat(numbers.map { it.value }, containsInAnyOrder(
            "467", "114", "35", "633", "617", "58", "592", "755", "664", "598"
        ))

        val stars = day3.schematicMatches(Regex("\\*"))
        assertThat(stars, hasSize(3))
        assertThat(stars, containsInAnyOrder(
            Day3.SchematicMatch("*", listOf(Coord(3, 1))),
            Day3.SchematicMatch("*", listOf(Coord(3, 4))),
            Day3.SchematicMatch("*", listOf(Coord(5, 8))),
        ))
    }
}
