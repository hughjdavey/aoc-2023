package days

class Day2 : Day(2) {

    val games = inputList.map(this::parseGame)

    override fun partOne(): Any {
        val bag = CubeSet(12, 13, 14)
        return games.filter { it.isPossible(bag) }.sumOf { it.id }
    }

    override fun partTwo(): Any {
        return games.sumOf { it.minimumCubeSet().power() }
    }

    fun parseGame(record: String): Game {
        val colon = record.indexOf(':')
        val id = record.substring(record.indexOf(' ') + 1, colon).toInt()
        val sets = record.substring(colon + 1).split(';').map { it.split(',') }
        return Game(id, sets.map { it.associate(this::parseCube) }.map(this::parseCubeSet))
    }

    private fun parseCube(string: String): Pair<String, Int> {
        val (count, colour) = Regex("(\\d+) (.+)").findAll(string).toList().flatMap { it.groupValues.drop(1) }
        return colour to count.toInt()
    }

    private fun parseCubeSet(cubes: Map<String, Int>): CubeSet {
        return CubeSet(
            cubes.getOrDefault("red", 0),
            cubes.getOrDefault("green", 0),
            cubes.getOrDefault("blue", 0)
        )
    }

    data class Game(val id: Int, val cubeSets: List<CubeSet>) {

        fun highestRed() = cubeSets.maxOf { it.red }

        fun highestGreen() = cubeSets.maxOf { it.green }

        fun highestBlue() = cubeSets.maxOf { it.blue }

        fun isPossible(bag: CubeSet): Boolean {
            return highestRed() <= bag.red && highestGreen() <= bag.green && highestBlue() <= bag.blue
        }

        fun minimumCubeSet(): CubeSet {
            return CubeSet(highestRed(), highestGreen(), highestBlue())
        }
    }

    data class CubeSet(val red: Int, val green: Int, val blue: Int) {

        fun power() = red * green * blue
    }
}
