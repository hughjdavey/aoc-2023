package days

class Day14 : Day(14) {

    override fun partOne(): Any {
        return tilt(inputList, Direction.NORTH).reversed()
            .mapIndexed { index, s -> (index + 1) * s.count { it == 'O' } }
            .sum()
    }

    override fun partTwo(): Any {
        val (indexOfRepeated, indexOfRepeat) = generateSequence(0 to listOf(inputList)) {
            it.first + 1 to it.second.plusElement(spinCycle(it.second.last()))
        }.mapIndexedNotNull { index, it ->
            val spin = spinCycle(it.second.last())
            if (it.second.contains(spin)) {
                it.second.indexOf(spin) to index + 1
            } else null
        }.first()

        val diff = indexOfRepeat - indexOfRepeated
        val stableCycle = generateSequence(1000000000 % diff) { it + diff }.map { (1..it)
            .fold(inputList) { a, _ -> spinCycle(a) } }
            .windowed(2).first { it[0] == it[1] }.first()
        return stableCycle.reversed()
            .mapIndexed { index, s -> (index + 1) * s.count { it == 'O' } }
            .sum()
    }

    fun tilt(platform: List<String>, direction: Direction): List<String> {
        val lines = if (direction == Direction.WEST || direction == Direction.EAST) platform else {
            (0..platform.first().lastIndex).map { x -> platform.map { it[x] }.joinToString("") }
        }

        val match = when (direction) {
            Direction.NORTH, Direction.WEST -> ".O"
            Direction.SOUTH, Direction.EAST -> "O."
        }

        val tilted = lines.map {
            var line = it
            var nextIndex = line.indexOf(match)
            while (nextIndex != -1) {
                line = line.substring(0, nextIndex) + match.reversed() + line.substring(nextIndex + 2)
                nextIndex = line.indexOf(match)
            }
            line
        }

        return if (direction == Direction.WEST || direction == Direction.EAST) tilted else {
            (0..platform.lastIndex).map { y -> tilted.map { it[y] }.joinToString("") }
        }
    }

    fun spinCycle(platform: List<String>): List<String> {
        return listOf(Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.EAST).fold(platform) { p, direction -> tilt(p, direction) }
    }

    enum class Direction {
        NORTH, SOUTH, EAST, WEST
    }
}
