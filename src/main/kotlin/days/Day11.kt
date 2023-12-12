package days

import xyz.hughjd.aocutils.Coords.Coord

class Day11 : Day(11) {

    override fun partOne(): Any {
        return sumOfShortestPaths(2)
    }

    override fun partTwo(): Any {
        val factors = listOf(
            sumOfShortestPaths(1),
            sumOfShortestPaths(10),
            sumOfShortestPaths(100)
        ).map { it.toLong() }

        // borrow line from day 9
        val diffs = factors.windowed(2) { (l, r) -> r - l }

        // 4 more x10s to get to a million (1000 -> 10000 -> 100000 -> 1000000)
        val projectedDiffs = (0 until 4).fold(diffs) { acc, _ -> acc + acc.last() * 10 }.takeLast(4)

        // add all projected differences onto the value we got from factor 100
        return projectedDiffs.fold(factors.last()) { acc, elem -> acc + elem }
    }

    fun sumOfShortestPaths(expansionFactor: Int): Int {
        val expanded = spaceExpansion(expansionFactor)
        return getAllPairs(expanded).sumOf { shortestPath(it.first, it.second) }
    }

    fun shortestPath(start: Coord, end: Coord): Int {
        return start.manhattan(end)
    }

    fun getAllPairs(space: List<String>): List<Pair<Coord, Coord>> {
        val galaxies = space.flatMapIndexed { y, row -> row.mapIndexedNotNull { x, char -> if (char == '#') Coord(x, y) else null } }
        return galaxies.flatMapIndexed { index, c1 -> galaxies.drop(index).mapNotNull { c2 -> if (c1 != c2) c1 to c2 else null } }
    }

    fun spaceExpansion(scaleFactor: Int = 2): List<String> {
        val rowsToDuplicate = inputList.mapIndexedNotNull { index, row -> if (row.all { it == '.' }) index else null }
        val columns = (0..inputList.lastIndex).map { x -> inputList.map { row -> row[x] } }
        val columnsToDuplicate = columns.mapIndexedNotNull { index, column -> if (column.all { it == '.' }) index else null }

        return inputList.flatMapIndexed { y, row ->
            fun xFn(x: Int, char: Char): List<Char> {
                return if (columnsToDuplicate.contains(x)) List(scaleFactor) { char } else listOf(char)
            }

            if (rowsToDuplicate.contains(y)) {
                List(scaleFactor) { row.flatMapIndexed { x, char -> xFn(x, char) } }
            } else {
                listOf(row.flatMapIndexed { x, char -> xFn(x, char) })
            }
        }.map { it.joinToString("") }
    }
}
