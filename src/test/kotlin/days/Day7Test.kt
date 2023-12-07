package days

import days.Day7.Hand
import days.Day7.HandType
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class Day7Test {

    private val day7 = Day7()

    @Test
    fun testPartOne() {
        assertThat(day7.partOne(), `is`(6440))
    }

    @Test
    fun testPartTwo() {
        assertThat(day7.partTwo(), `is`(5905))
    }

    @Test
    fun testHandTypes() {
        assertThat(Hand("AAAAA").getType(false), `is`(HandType.FIVE_KIND))
        assertThat(Hand("AA8AA").getType(false), `is`(HandType.FOUR_KIND))
        assertThat(Hand("23332").getType(false), `is`(HandType.FULL_HOUSE))
        assertThat(Hand("TTT98").getType(false), `is`(HandType.THREE_KIND))
        assertThat(Hand("23432").getType(false), `is`(HandType.TWO_PAIR))
        assertThat(Hand("A23A4").getType(false), `is`(HandType.ONE_PAIR))
        assertThat(Hand("23456").getType(false), `is`(HandType.HIGH_CARD))
    }

    @Test
    fun testHandTypesWithJokers() {
        assertThat(Hand("QJJQ2").getType(false), `is`(HandType.TWO_PAIR))
        assertThat(Hand("QJJQ2").getType(true), `is`(HandType.FOUR_KIND))
    }

    @Test
    fun testRankCards() {
        val example1 = listOf(Hand("33332"), Hand("2AAAA"))
        assertThat(example1[0].getType(false), `is`(HandType.FOUR_KIND))
        assertThat(example1[1].getType(false), `is`(HandType.FOUR_KIND))
        assertThat(day7.rank(example1, false).map(Hand::cards), contains("2AAAA", "33332"))

        val example2 = listOf(Hand("77888"), Hand("77788"))
        assertThat(example2[0].getType(false), `is`(HandType.FULL_HOUSE))
        assertThat(example2[1].getType(false), `is`(HandType.FULL_HOUSE))
        assertThat(day7.rank(example2, false).map(Hand::cards), contains("77788", "77888"))

        val example3 = listOf(Hand("32T3K"), Hand("T55J5"), Hand("KK677"), Hand("KTJJT"), Hand("QQQJA"))
        assertThat(example3[0].getType(false), `is`(HandType.ONE_PAIR))
        assertThat(example3[1].getType(false), `is`(HandType.THREE_KIND))
        assertThat(example3[2].getType(false), `is`(HandType.TWO_PAIR))
        assertThat(example3[3].getType(false), `is`(HandType.TWO_PAIR))
        assertThat(example3[4].getType(false), `is`(HandType.THREE_KIND))
        assertThat(day7.rank(example3, false).map(Hand::cards), contains("32T3K", "KTJJT", "KK677", "T55J5", "QQQJA"))

        val example4 = listOf(Hand("JKKK2"), Hand("QQQQ2"))
        assertThat(example4[0].getType(true), `is`(HandType.FOUR_KIND))
        assertThat(example4[1].getType(true), `is`(HandType.FOUR_KIND))
        assertThat(day7.rank(example4, true).map(Hand::cards), contains("JKKK2", "QQQQ2"))

        // example 3 but with jokers
        val example5 = listOf(Hand("32T3K"), Hand("T55J5"), Hand("KK677"), Hand("KTJJT"), Hand("QQQJA"))
        assertThat(example5[0].getType(true), `is`(HandType.ONE_PAIR))
        assertThat(example5[1].getType(true), `is`(HandType.FOUR_KIND))
        assertThat(example5[2].getType(true), `is`(HandType.TWO_PAIR))
        assertThat(example5[3].getType(true), `is`(HandType.FOUR_KIND))
        assertThat(example5[4].getType(true), `is`(HandType.FOUR_KIND))
        assertThat(day7.rank(example5, true).map(Hand::cards), contains("32T3K", "KK677", "T55J5", "QQQJA", "KTJJT"))
    }
}
