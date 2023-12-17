package days

import days.Day14.Direction.EAST
import days.Day14.Direction.NORTH
import days.Day14.Direction.SOUTH
import days.Day14.Direction.WEST
import xyz.hughjd.aocutils.Coords.Coord

class Day16 : Day(16) {

    private val grid = BeamGrid(inputList)

    override fun partOne(): Any {
        return grid.getEnergy()
    }

    // todo takes 40 seconds
    override fun partTwo(): Any {
        val startingBeams = grid.getEdgeTiles().flatMap {
            when {
                it.y == 0 -> when (it.x) {
                    0 -> listOf(Beam(it, SOUTH), Beam(it, EAST))
                    grid.lastX -> listOf(Beam(it, SOUTH), Beam(it, WEST))
                    else -> listOf(Beam(it, SOUTH))
                }
                it.y == grid.lastY -> when (it.x) {
                    0 -> listOf(Beam(it, NORTH), Beam(it, EAST))
                    grid.lastX -> listOf(Beam(it, NORTH), Beam(it, WEST))
                    else -> listOf(Beam(it, NORTH))
                }
                it.x == 0 -> listOf(Beam(it, EAST))
                it.x == grid.lastX -> listOf(Beam(it, WEST))
                else -> emptyList()
            }
        }
        return startingBeams.maxOf { grid.getEnergy(it) }
    }

    class BeamGrid(private val input: List<String>) {

        val lastX = input.first().lastIndex
        val lastY = input.lastIndex

        fun getEnergy(startingBeam: Beam = Beam(Coord(0, 0), EAST)): Int {
            var beams = listOf(startingBeam)
            val tiles = mutableListOf<Beam>()
            while (beams.isNotEmpty()) {
                tiles.addAll(beams)
                beams = beams
                    .flatMap { it.advance(getTile(it.position)) }
                    .filterNot { isOutsideGrid(it.position) }
                    .filterNot { tiles.contains(it) }
            }
            return tiles.map { it.position }.distinct().map { getTile(it) }.size
        }

        fun getEdgeTiles(): List<Coord> {
            return input.first().mapIndexed { x, _ -> Coord(x, 0) } +
                    input.last().mapIndexed { x, _ -> Coord(x, lastY) } +
                    (0..lastY).map { y -> Coord(0, y) } +
                    (0..lastY).map { y -> Coord(lastX, y) }
        }

        private fun isOutsideGrid(coord: Coord) = coord.x < 0 || coord.y < 0 || coord.x > lastX || coord.y > lastY

        private fun getTile(coord: Coord): Char {
            return input[coord.y][coord.x]
        }
    }

    data class Beam(val position: Coord, val direction: Day14.Direction) {

        fun advance(tile: Char): List<Beam> {
            return when (tile) {
                '.' -> listOf(onEmptySpace())
                '/','\\' -> listOf(onMirror(tile))
                '-','|' -> onSplitter(tile)
                else -> emptyList()
            }
        }

        private fun onEmptySpace(): Beam {
            return when (direction) {
                NORTH -> copy(position = position.minusY(1))
                EAST -> copy(position = position.plusX(1))
                SOUTH -> copy(position = position.plusY(1))
                WEST -> copy(position = position.minusX(1))
            }
        }

        private fun onMirror(mirror: Char): Beam {
            return when (direction) {
                NORTH -> if (mirror == '/') Beam(position.plusX(1), EAST) else Beam(position.minusX(1), WEST)
                EAST -> if (mirror == '/') Beam(position.minusY(1), NORTH) else Beam(position.plusY(1), SOUTH)
                SOUTH -> if (mirror == '/') Beam(position.minusX(1), WEST) else Beam(position.plusX(1), EAST)
                WEST -> if (mirror == '/') Beam(position.plusY(1), SOUTH) else Beam(position.minusY(1), NORTH)
            }
        }

        private fun onSplitter(splitter: Char): List<Beam> {
            return when (direction) {
                NORTH, SOUTH ->
                    if (splitter == '-') listOf(Beam(position.minusX(1), WEST), Beam(position.plusX(1), EAST))
                    else listOf(onEmptySpace())
                EAST, WEST ->
                    if (splitter == '|') listOf(Beam(position.minusY(1), NORTH), Beam(position.plusY(1), SOUTH))
                    else listOf(onEmptySpace())
            }
        }
    }
}
