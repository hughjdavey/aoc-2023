package days

class Day4 : Day(4) {

    private val cards = inputList.map(this::parseCard)

    override fun partOne(): Any {
        return cards.sumOf { it.getPoints() }
    }

    override fun partTwo(): Any {
        var cardCount = 0
        val cachedWins = cards.associate { it.id to it.getCardWins() }
        var cardIds = cards.map { it.id }
        while (cardIds.isNotEmpty()) {
            cardCount += cardIds.size
            cardIds = cardIds.flatMap { id -> cachedWins[id].orEmpty() }
        }
        return cardCount
    }

    fun parseCard(str: String): Card {
        val colon = str.indexOf(':')
        val id = str.substring(str.indexOf(' ') + 1, colon).trim().toInt()
        val (winningNumbers, numbers) = str.substring(colon + 2).split('|')
            .map { it.split(' ').filter { it.isNotEmpty() }.map { it.toInt() } }
        return Card(id, winningNumbers, numbers)
    }

    data class Card(val id: Int, val winningNumbers: List<Int>, val numbers: List<Int>) {

        fun getPoints(): Int {
            System.err.println(winningNumbers.count { numbers.contains(it) })
            return numbers.fold(0) { points, number -> if (winningNumbers.contains(number)) double(points) else points }
        }

        fun getCardWins(): List<Int> {
            val matches = winningNumbers.count { numbers.contains(it) }
            return (0 until matches).map { id.toInt() + 1 + it }
        }

        private fun double(points: Int) = if (points == 0) 1 else points * 2
    }
}
