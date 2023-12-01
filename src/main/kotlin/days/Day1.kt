package days

class Day1 : Day(1) {

    override fun partOne(): Any {
        return calibrationSum(inputList, REGEX_NUMBERS)
    }

    override fun partTwo(): Any {
        return calibrationSum(inputList, REGEX_NUMBER_WORDS)
    }

    fun calibrationSum(lines: List<String>, digitRegex: Regex): Int {
        return lines.sumOf { line ->
            val values = calibrationValues(line, digitRegex)
            "${values.first()}${values.last()}".toInt()
        }
    }

    fun calibrationValues(line: String, digitRegex: Regex): List<Int> {
        val matches = digitRegex.findAllOverlapping(line).map { it.value }
        return matches.map { digit ->
            val wordIndex = NUMBER_WORDS.indexOf(digit)
            if (wordIndex == -1) digit.toInt() else wordIndex + 1
        }
    }

    private fun Regex.findAllOverlapping(input: CharSequence): List<MatchResult> {
        val matches = mutableListOf<MatchResult>()
        var match = this.find(input)
        while (match != null) {
            matches.add(match)
            match = this.find(input, match.range.first + 1)
        }
        return matches
    }

    companion object {

        val REGEX_NUMBERS = Regex("\\d")

        val REGEX_NUMBER_WORDS = Regex("\\d|one|two|three|four|five|six|seven|eight|nine")

        val NUMBER_WORDS = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    }
}
