package days

import days.Day14.Direction
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.`in`
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class Day18Test {

    private val day18 = Day18()

    @Test
    fun testPartOne() {
        assertThat(day18.partOne(), `is`(62L))
    }

    @Test
    fun testPartTwo() {
        assertThat(day18.partTwo(), `is`(952408144115L))
    }

    @Test
    fun testParseInstructions() {
        val instructions = day18.parseInstructions()
        assertThat(instructions, hasSize(14))
        assertThat(instructions[0], `is`(Day18.DigInstruction(Direction.EAST, 6, "#70c710")))
        assertThat(instructions[1], `is`(Day18.DigInstruction(Direction.SOUTH, 5, "#0dc571")))
        assertThat(instructions[2], `is`(Day18.DigInstruction(Direction.WEST, 2, "#5713f0")))
        assertThat(instructions[11], `is`(Day18.DigInstruction(Direction.NORTH, 3, "#a77fa3")))
        assertThat(instructions[12], `is`(Day18.DigInstruction(Direction.WEST, 2, "#015232")))
        assertThat(instructions[13], `is`(Day18.DigInstruction(Direction.NORTH, 2, "#7a21e3")))
    }

    @Test
    fun testParseInstructionsFromColours() {
        val instructions = day18.parseInstructionsFromColours()
        assertThat(instructions, hasSize(14))
        assertThat(instructions[0], `is`(Day18.DigInstruction(Direction.EAST, 461937, "#70c710")))
        assertThat(instructions[1], `is`(Day18.DigInstruction(Direction.SOUTH, 56407, "#0dc571")))
        assertThat(instructions[2], `is`(Day18.DigInstruction(Direction.EAST, 356671, "#5713f0")))
        assertThat(instructions[11], `is`(Day18.DigInstruction(Direction.NORTH, 686074, "#a77fa3")))
        assertThat(instructions[12], `is`(Day18.DigInstruction(Direction.WEST, 5411, "#015232")))
        assertThat(instructions[13], `is`(Day18.DigInstruction(Direction.NORTH, 500254, "#7a21e3")))
    }
}
