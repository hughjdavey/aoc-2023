package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class Day1Test {

    private val dayOne = Day1()

    @Test
    fun testPartOne() {
        assertThat(dayOne.partOne(), `is`(142))
    }

    @Test
    fun testPartTwo() {
        // there is a different test input for part two so we can't call partTwo directly
        assertThat(dayOne.calibrationSum(listOf(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen",
        ), Day1.REGEX_NUMBER_WORDS), `is`(281))
    }

    @Test
    fun testLineToDigit() {
        assertThat(dayOne.calibrationValues("1abc2", Day1.REGEX_NUMBERS), contains(1, 2))
        assertThat(dayOne.calibrationValues("pqr3stu8vwx", Day1.REGEX_NUMBERS), contains(3, 8))
        assertThat(dayOne.calibrationValues("a1b2c3d4e5f", Day1.REGEX_NUMBERS), contains(1, 2, 3, 4, 5))
        assertThat(dayOne.calibrationValues("treb7uchet", Day1.REGEX_NUMBERS), contains(7))

        assertThat(dayOne.calibrationValues("two1nine", Day1.REGEX_NUMBER_WORDS), contains(2, 1, 9))
        assertThat(dayOne.calibrationValues("eightwothree", Day1.REGEX_NUMBER_WORDS), contains(8, 2, 3))
        assertThat(dayOne.calibrationValues("abcone2threexyz", Day1.REGEX_NUMBER_WORDS), contains(1, 2, 3))
        assertThat(dayOne.calibrationValues("xtwone3four", Day1.REGEX_NUMBER_WORDS), contains(2, 1, 3, 4))
        assertThat(dayOne.calibrationValues("4nineeightseven2", Day1.REGEX_NUMBER_WORDS), contains(4, 9, 8, 7, 2))
        assertThat(dayOne.calibrationValues("zoneight234", Day1.REGEX_NUMBER_WORDS), contains(1, 8, 2, 3, 4))
        assertThat(dayOne.calibrationValues("7pqrstsixteen", Day1.REGEX_NUMBER_WORDS), contains(7, 6))
    }
}
