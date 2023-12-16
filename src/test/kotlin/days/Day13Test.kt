package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class Day13Test {

    private val day13 = Day13()

    @Test
    fun testPartOne() {
        assertThat(day13.partOne(), `is`(405))
    }

    @Test
    fun testPartTwo() {
        assertThat(day13.partTwo(), `is`(400))
    }

    @Test
    fun testFindReflection() {
        assertThat(day13.findReflection(listOf(
            "#.##..##.",
            "..#.##.#.",
            "##......#",
            "##......#",
            "..#.##.#.",
            "..##..##.",
            "#.#.##.#.",
        )), `is`(Day13.Reflection(x = 5)))

        assertThat(day13.findReflection(listOf(
            "#...##..#",
            "#....#..#",
            "..##..###",
            "#####.##.",
            "#####.##.",
            "..##..###",
            "#....#..#",
        )), `is`(Day13.Reflection(y = 4)))

        assertThat(day13.findReflection(listOf(
            "#.#.....#.##.##.#",
            "#.#.....#.##.##.#",
            ".....#...#####...",
            "...#.###....#...#",
            ".###..####.#..#.#",
            "#.###.#.#..###..#",
            "..####...##.#.##.",
            "..####...##.#.##.",
            "#.#.#.#.#..###..#",
            ".###..####.#..#.#",
            "...#.###....#...#",
            ".....#...#####...",
            "#.#.....#.##.##.#",
        )), `is`(Day13.Reflection(y = 1)))
    }

    @Test
    fun testFindSmudgeReflection() {
        assertThat(day13.findSmudgeReflection(listOf(
            "#.##..##.",
            "..#.##.#.",
            "##......#",
            "##......#",
            "..#.##.#.",
            "..##..##.",
            "#.#.##.#.",
        )), `is`(Day13.Reflection(y = 3)))

        assertThat(day13.findSmudgeReflection(listOf(
            "#...##..#",
            "#....#..#",
            "..##..###",
            "#####.##.",
            "#####.##.",
            "..##..###",
            "#....#..#",
        )), `is`(Day13.Reflection(y = 1)))

        assertThat(day13.findSmudgeReflection(listOf(
            "#.#.....#.##.##.#",
            "#.#.....#.##.##.#",
            ".....#...#####...",
            "...#.###....#...#",
            ".###..####.#..#.#",
            "#.###.#.#..###..#",
            "..####...##.#.##.",
            "..####...##.#.##.",
            "#.#.#.#.#..###..#",
            ".###..####.#..#.#",
            "...#.###....#...#",
            ".....#...#####...",
            "#.#.....#.##.##.#",
        )), `is`(Day13.Reflection(y = 7)))
    }
}
