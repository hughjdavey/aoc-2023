package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.empty
import org.hamcrest.Matchers.emptyIterable
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class Day4Test {

    private val day4 = Day4()

    @Test
    fun testPartOne() {
        assertThat(day4.partOne(), `is`(13))
    }

    @Test
    fun testPartTwo() {
        assertThat(day4.partTwo(), `is`(30))
    }

    @Test
    fun testParseCards() {
        assertThat(day4.parseCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"), `is`(
            Day4.Card(1, listOf(41, 48, 83, 86, 17), listOf(83, 86, 6, 31, 17, 9, 48, 53))))
        assertThat(day4.parseCard("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19"), `is`(
            Day4.Card(2, listOf(13, 32, 20, 16, 61), listOf(61, 30, 68, 82, 17, 32, 24, 19))))
        assertThat(day4.parseCard("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1"), `is`(
            Day4.Card(3, listOf(1, 21, 53, 59, 44), listOf(69, 82, 63, 72, 16, 21, 14, 1))))
        assertThat(day4.parseCard("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83"), `is`(
            Day4.Card(4, listOf(41, 92, 73, 84, 69), listOf(59, 84, 76, 51, 58, 5, 54, 83))))
        assertThat(day4.parseCard("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36"), `is`(
            Day4.Card(5, listOf(87, 83, 26, 28, 32), listOf(88, 30, 70, 12, 93, 22, 82, 36))))
        assertThat(day4.parseCard("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"), `is`(
            Day4.Card(6, listOf(31, 18, 13, 56, 72), listOf(74, 77, 10, 23, 35, 67, 36, 11))))
    }

    @Test
    fun testCardPoints() {
        assertThat(day4.parseCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53").getPoints(), `is`(8))
        assertThat(day4.parseCard("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19").getPoints(), `is`(2))
        assertThat(day4.parseCard("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1").getPoints(), `is`(2))
        assertThat(day4.parseCard("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83").getPoints(), `is`(1))
        assertThat(day4.parseCard("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36").getPoints(), `is`(0))
        assertThat(day4.parseCard("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11").getPoints(), `is`(0))
    }

    @Test
    fun testCardWins() {
        assertThat(day4.parseCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53").getCardWins(), contains(2, 3, 4, 5))
        assertThat(day4.parseCard("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19").getCardWins(), contains(3, 4))
        assertThat(day4.parseCard("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1").getCardWins(), contains(4, 5))
        assertThat(day4.parseCard("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83").getCardWins(), contains(5))
        assertThat(day4.parseCard("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36").getCardWins(), emptyIterable())
        assertThat(day4.parseCard("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11").getCardWins(), emptyIterable())
    }
}
