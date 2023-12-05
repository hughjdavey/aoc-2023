package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class Day5Test {

    private val day5 = Day5()

    @Test
    fun testPartOne() {
        assertThat(day5.partOne(), `is`(35L))
    }

    @Test
    fun testPartTwo() {
        assertThat(day5.partTwo(), `is`(46L))
    }

    @Test
    fun testParseAlmanac() {
        val almanac = day5.parseAlmanac()
        assertThat(almanac.seeds, contains(79, 14, 55, 13))
        assertThat(almanac.maps, hasSize(7))
        assertThat(almanac.maps[0].name, `is`("seed-to-soil"))
        assertThat(almanac.maps[0].get(0), `is`(0))
        assertThat(almanac.maps[0].get(1), `is`(1))
        assertThat(almanac.maps[0].get(48), `is`(48))
        assertThat(almanac.maps[0].get(49), `is`(49))
        assertThat(almanac.maps[0].get(50), `is`(52))
        assertThat(almanac.maps[0].get(51), `is`(53))
        assertThat(almanac.maps[0].get(96), `is`(98))
        assertThat(almanac.maps[0].get(97), `is`(99))
        assertThat(almanac.maps[0].get(98), `is`(50))
        assertThat(almanac.maps[0].get(99), `is`(51))
    }

    @Test
    fun testForwardsChain() {
        val almanac = day5.parseAlmanac()
        assertThat(almanac.maps[0].get(13L), `is`(13L))
        assertThat(almanac.maps[1].get(13L), `is`(52L))
        assertThat(almanac.maps[2].get(52L), `is`(41L))
        assertThat(almanac.maps[3].get(41L), `is`(34L))
        assertThat(almanac.maps[4].get(34L), `is`(34L))
        assertThat(almanac.maps[5].get(34L), `is`(35L))
        assertThat(almanac.maps[6].get(35L), `is`(35L))
    }

    @Test
    fun testBackwardChain() {
        val almanac = day5.parseAlmanac()
        assertThat(almanac.maps[6].getReversed(35L), `is`(35L))
        assertThat(almanac.maps[5].getReversed(35L), `is`(34L))
        assertThat(almanac.maps[4].getReversed(34L), `is`(34L))
        assertThat(almanac.maps[3].getReversed(34L), `is`(41L))
        assertThat(almanac.maps[2].getReversed(41L), `is`(52L))
        assertThat(almanac.maps[1].getReversed(52L), `is`(13L))
        assertThat(almanac.maps[0].getReversed(13L), `is`(13L))
    }

    @Test
    fun testSeedToLocation() {
        val almanac = day5.parseAlmanac()
        assertThat(almanac.seedToLocation(79L), `is`(82L))
        assertThat(almanac.seedToLocation(14L), `is`(43L))
        assertThat(almanac.seedToLocation(55L), `is`(86L))
        assertThat(almanac.seedToLocation(13L), `is`(35L))
    }

    @Test
    fun testLocationToSeed() {
        val almanac = day5.parseAlmanac()
        assertThat(almanac.locationToSeed(82L), `is`(79L))
        assertThat(almanac.locationToSeed(43L), `is`(14L))
        assertThat(almanac.locationToSeed(86L), `is`(55L))
        assertThat(almanac.locationToSeed(35L), `is`(13L))
    }

    @Test
    fun testHasInitialSeed() {
        val almanac = day5.parseAlmanac()
        assertThat(almanac.hasInitialSeed(79L, false), `is`(true))
        assertThat(almanac.hasInitialSeed(14L, false), `is`(true))
        assertThat(almanac.hasInitialSeed(55L, false), `is`(true))
        assertThat(almanac.hasInitialSeed(13L, false), `is`(true))
        (79L..92L).forEach { assertThat(almanac.hasInitialSeed(it, true), `is`(true)) }
        (55L..67L).forEach { assertThat(almanac.hasInitialSeed(it, true), `is`(true)) }
    }
}
