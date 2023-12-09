package days

class Day9 : Day(9) {

    override fun partOne(): Any {
        return inputList.map { line -> line.split(' ').map { it.toInt() } }.sumOf { extrapolateNext(it) }
    }

    override fun partTwo(): Any {
        return inputList.map { line -> line.split(' ').map { it.toInt() } }.sumOf { extrapolatePrevious(it) }
    }

    fun extrapolateNext(history: List<Int>): Int {
        if (history.all { it == 0 }) {
            return 0
        }
        val differences = history.windowed(2) { (l, r) -> r - l }
        return history.last() + extrapolateNext(differences)
    }

    fun extrapolatePrevious(history: List<Int>): Int {
        if (history.all { it == 0 }) {
            return 0
        }
        val differences = history.windowed(2) { (l, r) -> r - l }
        return history.first() - extrapolatePrevious(differences)
    }
}
