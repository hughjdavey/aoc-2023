package days

import xyz.hughjd.aocutils.Collections.stackOf
import xyz.hughjd.aocutils.Strings.indicesOf

class Day12 : Day(12) {

    override fun partOne(): Any {
        return inputList.map { ConditionRecord(it) }.sumOf { it.possibleArrangements().size }
    }

    override fun partTwo(): Any {
        return 0
        //return inputList.map { ConditionRecord(unfold(it)) }.sumOf { it.possibleArrangements().size }
    }

    fun unfold(record: String): String {
        val (springsStr, sizesStr) = record.split(' ')
        return "${springsStr}?".repeat(5).dropLast(1) + ' ' + "${sizesStr},".repeat(5).dropLast(1)
    }

    class ConditionRecord(private val record: String) {

        val springs: List<SpringCondition>

        val sizes: List<Int>

        init {
            val (springsStr, sizesStr) = record.split(' ')
            springs = springsStr.map { when (it) {
                '.' -> SpringCondition.OPERATIONAL
                '#' -> SpringCondition.DAMAGED
                else -> SpringCondition.UNKNOWN
            } }
            sizes = sizesStr.split(',').map { it.toInt() }
        }

        fun possibleArrangements(): List<String> {
            val questionMarkIndices = record.indicesOf('?')
            val possibleStrings = allPossibleStrings(questionMarkIndices.size)
            return possibleStrings.map {
                val charStack = stackOf(it.toList())
                record.mapIndexed { index, c -> if (questionMarkIndices.contains(index)) charStack.pop() else c }
                    .joinToString("")
            }.filter(ConditionRecord::isValidArrangement)
        }

        companion object {

            fun isValidArrangement(arrangement: String): Boolean {
                val cr = ConditionRecord(arrangement)
                return cr.springs.none { it == SpringCondition.UNKNOWN } &&
                        cr.springs.filter { it == SpringCondition.DAMAGED }.size == cr.sizes.sum() &&
                        validDamageRanges(arrangement, cr)
            }

            private fun validDamageRanges(arrangement: String, conditionRecord: ConditionRecord): Boolean {
                val damageRanges = Regex("#+").findAll(arrangement).toList().map { it.range }
                return damageRanges.map { it.count() } == conditionRecord.sizes
            }

            // see https://www.geeksforgeeks.org/print-all-combinations-of-given-length
            fun allPossibleStrings(length: Int, charset: List<Char> = listOf('.', '#'), prefix: String = "", strings: List<String> = emptyList()): List<String> {
                if (length == 0) {
                    return strings + prefix
                }
                return charset.flatMap { char -> allPossibleStrings(length - 1, charset, prefix + char, strings) }
            }
        }
    }

    enum class SpringCondition {

        OPERATIONAL, DAMAGED, UNKNOWN
    }
}
