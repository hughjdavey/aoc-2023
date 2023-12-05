package days

import xyz.hughjd.aocutils.Collections.split

class Day5 : Day(5) {

    private val almanac = parseAlmanac()

    override fun partOne(): Any {
        return seedToLocationSequence()
            .filter { almanac.hasInitialSeed(it.seed, false) }
            .first().location
    }

    override fun partTwo(): Any {
        return seedToLocationSequence()
            .filter { almanac.hasInitialSeed(it.seed, true) }
            .first().location
    }

    private fun seedToLocationSequence(): Sequence<SeedToLocation> {
        return generateSequence(0L) { it + 1L }
            .map { SeedToLocation(almanac.locationToSeed(it), it) }
    }

    fun parseAlmanac(): Almanac {
        val seeds = inputList[0].substring(7).split(' ').map { it.toLong() }
        val maps = inputList.drop(2).split("").flatMap { it.split(",") }.fold(listOf<AlmanacMap>()) { acc, elem ->
            val name = elem.first().replace(" map:", "")
            val ranges = elem.drop(1).map { it.trim().split(' ').map { it.toLong() } }
            acc + AlmanacMap(name, ranges)
        }
        return Almanac(seeds, maps)
    }

    data class SeedToLocation(val seed: Long, val location: Long)

    data class Almanac(val seeds: List<Long>, val maps: List<AlmanacMap>) {

        fun seedToLocation(seed: Long): Long {
            return maps.fold(seed) { value, map -> map.get(value) }
        }

        fun locationToSeed(location: Long): Long {
            return maps.reversed().fold(location) { value, map -> map.getReversed(value) }
        }

        fun hasInitialSeed(seed: Long, seedRanges: Boolean): Boolean {
            return if (!seedRanges) seeds.contains(seed) else {
                seeds.chunked(2).any { (start, length) ->
                    seed >= start && seed < (start + length)
                }
            }
        }
    }

    data class AlmanacMap(val name: String, val ranges: List<List<Long>>) {

        fun get(n: Long): Long {
            for ((destStart, srcStart, length) in ranges) {
                if (n >= srcStart && n < srcStart + length) {
                    return destStart + (n - srcStart)
                }
            }
            return n
        }

        fun getReversed(n: Long): Long {
            for ((destStart, srcStart, length) in ranges) {
                if (n >= destStart && n < destStart + length) {
                    return srcStart + (n - destStart)
                }
            }
            return n
        }
    }
}
