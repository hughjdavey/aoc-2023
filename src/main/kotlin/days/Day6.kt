package days

class Day6 : Day(6) {

    private val times = inputList.first().split(' ').filter { it.matches(Regex("\\d+")) }
    private val distances = inputList.last().split(' ').filter { it.matches(Regex("\\d+")) }

    override fun partOne(): Any {
        val races = times.zip(distances).map { (time, distance) -> Race(time.toLong(), distance.toLong()) }
        return races.map { it.winningHoldTimes().size }.fold(1) { a, e -> a * e }
    }

    override fun partTwo(): Any {
        val time = times.joinToString("")
        val distance = distances.joinToString("")
        val race = Race(time.toLong(), distance.toLong())
        return race.winningHoldTimes().size
    }

    data class Race(val time: Long, val distance: Long) {

        fun winningHoldTimes(): List<Long> {
            return (0..time).filter { holdTime ->
                val remainingTime = time - holdTime
                val boatDistance = holdTime * remainingTime
                boatDistance > distance
            }
        }
    }
}
