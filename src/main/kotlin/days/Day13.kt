package days

import xyz.hughjd.aocutils.Collections.split

class Day13 : Day(13) {

    private val patterns = inputList.split("")

    override fun partOne(): Any {
        return reflectionSum(patterns.map { findReflection(it) })
    }

    override fun partTwo(): Any {
        return reflectionSum(patterns.map { findSmudgeReflection(it) })
    }

    private fun reflectionSum(reflections: List<Reflection?>) = reflections.filterNotNull().sumOf(Reflection::sum)

    data class Reflection(val x: Int = 0, val y: Int = 0) {

        fun sum() = x + y * 100
    }

    /*
                        #.##..##.
                        ..#.##.#.
                        ##......#
                        ##......#
                        ..#.##.#.
                        ..##..##.
                        #.#.##.#.

                        #...##..#
                        #....#..#
                        ..##..###
                        #####.##.
                        #####.##.
                        ..##..###
                        #....#..#

                        #.#.....#.##.##.#
                        #.#.....#.##.##.#
                        .....#...#####...
                        ...#.###....#...#
                        .###..####.#..#.#
                        #.###.#.#..###..#
                        ..####...##.#.##.
                        ..####...##.#.##.
                        #.###.#.#..###..#
                        .###..####.#..#.#
                        ...#.###....#...#
                        .....#...#####...
                        #.#.....#.##.##.#

     */

    fun findSmudgeReflection(pattern: List<String>): Reflection? {
        val originalReflection = findReflection(pattern)
        val patternString = pattern.joinToString("")
        val xLen = pattern.first().length
        for (y in pattern.indices) {
            for (x in 0 until pattern.first().length) {
                val index = (xLen * y) + x
                System.err.println("($x, $y) $index => ${patternString[index]}")
                val current = patternString[index]
                val new = if (current == '#') '.' else '#'
                val modified = patternString.substring(0, index) + new + patternString.substring(index + 1)
                val modifiedPattern = modified.chunked(xLen)

                val reflection = findReflections(modifiedPattern).filterNot { it == originalReflection }.firstOrNull()
                if (reflection != null) {
                    return reflection
                }
            }
        }

        sequenceOf(*pattern.indices.flatMap { y -> (0..pattern.first().lastIndex).map { x -> x to y } }.toTypedArray())
            .map {
                val (x, y) = it
                val index = (xLen * y) + x
                val current = patternString[index]
                val new = if (current == '#') '.' else '#'
                val modified = patternString.substring(0, index) + new + patternString.substring(index + 1)
                modified.chunked(xLen)
            }
            .mapNotNull { findReflections(it).filterNot { it == originalReflection }.firstOrNull() }.first()

        throw RuntimeException("No reflection")
    }

    fun findReflection(pattern: List<String>): Reflection? {
        return findReflections(pattern).firstOrNull()
    }

    private fun findReflections(pattern: List<String>): List<Reflection> {
        val reflections = mutableListOf<Reflection>()

        val horizontalReflections = pattern.withIndex().windowed(2).filter { it.first().value == it.last().value }
        for (horizontalReflection in horizontalReflections) {
            val indices = horizontalReflection.first().index to horizontalReflection.last().index
            if (hasReflection(pattern, indices)) {
                reflections.add(Reflection(y = indices.second))
            }
        }

        val columns = (0..pattern.first().lastIndex).map { x -> pattern.map { it[x] }.joinToString("") }
        val verticalReflections = columns.withIndex().windowed(2).filter { it.first().value == it.last().value }
        for (verticalReflection in verticalReflections) {
            val indices = verticalReflection.first().index to verticalReflection.last().index
            if (hasReflection(columns, indices)) {
                reflections.add(Reflection(x = indices.second))
            }
        }

        return reflections
    }

    fun hasReflection(pattern: List<String>, startingIndices: Pair<Int, Int>): Boolean {
        var indices = startingIndices
        while (indices.first >= 0 && indices.second <= pattern.lastIndex) {
            //System.err.println("pattern = $pattern, indices = $indices")
            if (pattern[indices.first] != pattern[indices.second]) {
                return false
            }
            indices = indices.first - 1 to indices.second + 1
        }
        return true
    }
}
