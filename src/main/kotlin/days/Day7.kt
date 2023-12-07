package days

class Day7 : Day(7) {

    private val hands = inputList.map { it.split(' ') }.map { (cards, bid) -> Hand(cards, bid.toInt()) }

    override fun partOne(): Any {
        return getTotalWinnings(hands, false)
    }

    override fun partTwo(): Any {
        return getTotalWinnings(hands, true)
    }

    private fun getTotalWinnings(hands: List<Hand>, jIsJoker: Boolean): Int {
        return rank(hands, jIsJoker).foldIndexed(0) { index, total, hand ->
            total + (hand.bid * (index + 1))
        }
    }

    fun rank(hands: List<Hand>, jIsJoker: Boolean): List<Hand> {
        return hands.sortedWith { h1, h2 ->
            val h1Type = h1.getType(jIsJoker)
            val h2Type = h2.getType(jIsJoker)
            if (h1Type != h2Type) h2Type.ordinal - h1Type.ordinal else {
                val firstDifference = h1.cards.zip(h2.cards).first { it.first != it.second }
                strength(firstDifference.first, jIsJoker) - strength(firstDifference.second, jIsJoker)
            }
        }
    }

    private fun strength(card: Char, jIsJoker: Boolean): Int {
        return when (card) {
            'A' -> 14
            'K' -> 13
            'Q' -> 12
            'J' -> if (jIsJoker) 1 else 11
            'T' -> 10
            else -> card.digitToInt()
        }
    }

    data class Hand(val cards: String, val bid: Int = 0) {

        fun getType(jIsJoker: Boolean): HandType {
            var cards = cards
            if (jIsJoker && cards.contains('J') && cards.count { it == 'J' } != 5) {
                val mcc = cards.filterNot { it == 'J' }.map { c -> cards.count { c == it } to c }.maxBy { it.first }.second
                cards = cards.replace('J', mcc)
            }

            val set = cards.toSet()
            return when (set.size) {
                1 -> HandType.FIVE_KIND
                2 -> {
                    if (set.any { c -> cards.count { c == it } == 4 }) HandType.FOUR_KIND
                    else HandType.FULL_HOUSE
                }
                3 -> {
                    if (set.any { c -> cards.count { c == it } == 3 }) HandType.THREE_KIND
                    else HandType.TWO_PAIR
                }
                4 -> HandType.ONE_PAIR
                else -> HandType.HIGH_CARD
            }
        }
    }

    enum class HandType {

        FIVE_KIND, FOUR_KIND, FULL_HOUSE, THREE_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD
    }
}
