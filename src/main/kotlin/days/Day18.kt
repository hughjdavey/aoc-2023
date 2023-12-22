package days

import days.Day14.Direction
import xyz.hughjd.aocutils.Coords.Coord
import kotlin.math.abs

class Day18 : Day(18) {

    override fun partOne(): Any {
        val (perimeter, vertices) = getPerimeterAndVertices(parseInstructions())
        return getInteriorPoints(perimeter, vertices)
    }

    override fun partTwo(): Any {
        val (perimeter, vertices) = getPerimeterAndVertices(parseInstructionsFromColours())
        return getInteriorPoints(perimeter, vertices)
    }

    private fun getPerimeterAndVertices(instructions: List<DigInstruction>): Pair<Long, List<Coord>> {
        return instructions.fold(0L to listOf(Coord(0, 0))) { (p, v), instruction ->
            val coord = v.last()
            val toDig = when (instruction.direction) {
                Direction.NORTH -> generateSequence(coord) { it.minusY(1) }
                Direction.SOUTH -> generateSequence(coord) { it.plusY(1) }
                Direction.EAST -> generateSequence(coord) { it.plusX(1) }
                Direction.WEST -> generateSequence(coord) { it.minusX(1) }
            }.take(instruction.metres + 1).toList().drop(1)
            p + toDig.size to v + toDig.last()
        }
    }

    // uses pick's theorem - thanks to reddit comment https://www.reddit.com/r/adventofcode/comments/18l0qtr/2023_day_18_solutions/kdveugr
    private fun getInteriorPoints(perimeterSize: Long, vertices: List<Coord>): Long {
        // pick is A = i + b / 2 - 1
        // we have A and b but want i
        // so rearrange to A - b / 2 = i - 1
        // and rearrange again to A - b / 2 + 1 = i
        // then we need to add the perimeter #s as well (b)
        // so final formula is A + b / 2 + 1 = i
        val A = getArea(vertices)
        val b = perimeterSize
        return A + (b / 2) + 1
    }

    // uses shoelace algorithm - thanks to comments in reddit thread https://old.reddit.com/r/adventofcode/comments/18l1mdm/2023_days_10_18_how_repetitive_can_this_get for inspriation
    // implementation based on description here https://www.101computing.net/the-shoelace-algorithm
    private fun getArea(vertices: List<Coord>): Long {
        val points = vertices + vertices.first()
        val one = points.windowed(2).fold(0L) { acc, (p1, p2) -> acc + (p1.x.toLong() * p2.y) }
        val two = points.windowed(2).fold(0L) { acc, (p1, p2) -> acc + (p1.y.toLong() * p2.x) }
        return abs(two - one) / 2
    }

    fun parseInstructions(): List<DigInstruction> {
        return inputList.map { it.split(' ') }.map { (d, m, c) ->
            DigInstruction(stringToDirection(d), m.toInt(), c.drop(1).dropLast(1))
        }
    }

    fun parseInstructionsFromColours(): List<DigInstruction> {
        return inputList.mapNotNull { Regex("(#.{6})").find(it)?.value }.map {
            val (d, m) = it.takeLast(1) to it.drop(1).dropLast(1)
            DigInstruction(stringToDirection(d), m.toInt(16), it)
        }
    }

    private fun stringToDirection(s: String): Direction {
        return when (s) {
            "U", "3" -> Direction.NORTH
            "D", "1" -> Direction.SOUTH
            "R", "0" -> Direction.EAST
            "L", "2" -> Direction.WEST
            else -> throw IllegalArgumentException()
        }
    }

    data class DigInstruction(val direction: Direction, val metres: Int, val colour: String)
}
