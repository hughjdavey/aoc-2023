package days

import xyz.hughjd.aocutils.Coords.Coord

class Day3 : Day(3) {

    val bounds = Coord(inputList[0].length, inputList.size)

    private val numbers = schematicMatches(Regex("\\d+"))

    private val partNumbers = numbers.filter { number -> number.getValidAdjacents(bounds).any {
        !Character.isDigit(inputList[it.y][it.x]) && inputList[it.y][it.x] != '.'
    } }

    override fun partOne(): Any {
        return partNumbers.sumOf { it.value.toInt() }
    }

    override fun partTwo(): Any {
        return schematicMatches(Regex("\\*"))
            .filter { star -> star.getAdjacentSlices(bounds, partNumbers).size == 2 }
            .map { gear -> gear.getAdjacentSlices(bounds, partNumbers) }
            .sumOf { it[0].toInt() * it[1].toInt() }
    }

    fun schematicMatches(regex: Regex): List<SchematicMatch> {
        return inputList.flatMapIndexed { y, line ->
            val matches = regex.findAll(line).toList()
            matches.map { SchematicMatch(it.value, it.range.map { x -> Coord(x, y) }) }
        }
    }

    data class SchematicMatch(val value: String, val coords: List<Coord>) {

        fun getValidAdjacents(bounds: Coord): List<Coord> {
            return coords.flatMap { it.getAdjacent(true) }
                .filter { it.x >= 0 && it.y >= 0 && it.x < bounds.x && it.y < bounds.y }
                .filterNot { coords.contains(it) }
                .distinct()
        }

        fun getAdjacentSlices(bounds: Coord, slices: List<SchematicMatch>): List<String> {
            return getValidAdjacents(bounds)
                .flatMap { a -> slices.filter { it.coords.contains(a) }.map { it.value } }
                .distinct()
        }
    }
}
