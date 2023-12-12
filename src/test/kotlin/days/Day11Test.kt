package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import xyz.hughjd.aocutils.Coords.Coord

class Day11Test {

    private val day11 = Day11()

    @Test
    fun testPartOne() {
        assertThat(day11.partOne(), `is`(374))
    }

    @Test
    fun testPartTwo() {
        assertThat(day11.partTwo(), `is`(82000210L))
    }

    @Test
    fun testSpaceExpansion() {
        assertThat(day11.spaceExpansion(), `is`(listOf(
            "....#........",
            ".........#...",
            "#............",
            ".............",
            ".............",
            "........#....",
            ".#...........",
            "............#",
            ".............",
            ".............",
            ".........#...",
            "#....#.......",
        )))
    }

    @Test
    fun testGetAllPairs() {
        val galaxyPairs = day11.getAllPairs(day11.spaceExpansion())
        assertThat(galaxyPairs, hasSize(36))
    }

    @Test
    fun testShortestPaths() {
        assertThat(day11.shortestPath(Coord(1, 6), Coord(5, 11)), `is`(9))
        assertThat(day11.shortestPath(Coord(4, 0), Coord(9, 10)), `is`(15))
        assertThat(day11.shortestPath(Coord(0, 2), Coord(12, 7)), `is`(17))
        assertThat(day11.shortestPath(Coord(0, 11), Coord(5, 11)), `is`(5))
    }

    @Test
    fun testSpaceExpansionFactors() {
        assertThat(day11.sumOfShortestPaths(1), `is`(292))
        assertThat(day11.sumOfShortestPaths(2), `is`(374))
        assertThat(day11.sumOfShortestPaths(10), `is`(1030))
        assertThat(day11.sumOfShortestPaths(100), `is`(8410))
        assertThat(day11.sumOfShortestPaths(1000), `is`(82210))
    }
}
