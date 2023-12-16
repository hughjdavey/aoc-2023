package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class Day14Test {

    private val day14 = Day14()

    @Test
    fun testPartOne() {
        assertThat(day14.partOne(), `is`(136))
    }

    @Test
    fun testPartTwo() {
        assertThat(day14.partTwo(), `is`(64))
    }

    @Test
    fun testTilt() {
        assertThat(day14.tilt(listOf(
            "O....#....",
            "O.OO#....#",
            ".....##...",
            "OO.#O....O",
            ".O.....O#.",
            "O.#..O.#.#",
            "..O..#O..O",
            ".......O..",
            "#....###..",
            "#OO..#....",
        ), Day14.Direction.NORTH), `is`(listOf(
            "OOOO.#.O..",
            "OO..#....#",
            "OO..O##..O",
            "O..#.OO...",
            "........#.",
            "..#....#.#",
            "..O..#.O.O",
            "..O.......",
            "#....###..",
            "#....#....",
        )))

        assertThat(day14.tilt(listOf(
            "O....#....",
            "O.OO#....#",
            ".....##...",
            "OO.#O....O",
            ".O.....O#.",
            "O.#..O.#.#",
            "..O..#O..O",
            ".......O..",
            "#....###..",
            "#OO..#....",
        ), Day14.Direction.SOUTH), `is`(listOf(
            ".....#....",
            "....#....#",
            "...O.##...",
            "...#......",
            "O.O....O#O",
            "O.#..O.#.#",
            "O....#....",
            "OO....OO..",
            "#OO..###..",
            "#OO.O#...O",
        )))

        assertThat(day14.tilt(listOf(
            "O....#....",
            "O.OO#....#",
            ".....##...",
            "OO.#O....O",
            ".O.....O#.",
            "O.#..O.#.#",
            "..O..#O..O",
            ".......O..",
            "#....###..",
            "#OO..#....",
        ), Day14.Direction.EAST), `is`(listOf(
            "....O#....",
            ".OOO#....#",
            ".....##...",
            ".OO#....OO",
            "......OO#.",
            ".O#...O#.#",
            "....O#..OO",
            ".........O",
            "#....###..",
            "#..OO#....",
        )))

        assertThat(day14.tilt(listOf(
            "O....#....",
            "O.OO#....#",
            ".....##...",
            "OO.#O....O",
            ".O.....O#.",
            "O.#..O.#.#",
            "..O..#O..O",
            ".......O..",
            "#....###..",
            "#OO..#....",
        ), Day14.Direction.WEST), `is`(listOf(
            "O....#....",
            "OOO.#....#",
            ".....##...",
            "OO.#OO....",
            "OO......#.",
            "O.#O...#.#",
            "O....#OO..",
            "O.........",
            "#....###..",
            "#OO..#....",
        )))
    }

    @Test
    fun testSpinCycle() {
        assertThat(day14.spinCycle(listOf(
            "O....#....",
            "O.OO#....#",
            ".....##...",
            "OO.#O....O",
            ".O.....O#.",
            "O.#..O.#.#",
            "..O..#O..O",
            ".......O..",
            "#....###..",
            "#OO..#....",
        )), `is`(listOf(
            ".....#....",
            "....#...O#",
            "...OO##...",
            ".OO#......",
            ".....OOO#.",
            ".O#...O#.#",
            "....O#....",
            "......OOOO",
            "#...O###..",
            "#..OO#....",
        )))
    }
}
