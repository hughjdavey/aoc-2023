package days

class Day15 : Day(15) {

    private val initSequence = inputString.replace("\n", "").split(',')

    override fun partOne(): Any {
        return initSequence.sumOf(this::hash)
    }

    override fun partTwo(): Any {
        val boxes = (0..255).associateWith { mutableListOf<Lens>() }
        val steps = initSequence.map {
            val splits = Regex("([a-z]+)([-=])(\\d+)?").matchEntire(it)!!.groupValues.drop(1)
            Step(splits[0], splits[1].first(), if (splits[2].isNotEmpty()) splits[2].toInt() else null )
        }

        for (step in steps) {
            val box = hash(step.lensLabel)
            val boxLenses = boxes[box]!!
            if (step.operation == '-') {
                boxLenses.removeIf { it.label == step.lensLabel }
            } else {
                val lens = Lens(step.lensLabel, step.lensFocalLength!!)
                val lensWithSameLabelIndex = boxLenses.indexOfFirst { it.label == lens.label }
                if (lensWithSameLabelIndex != -1) {
                    boxLenses.removeAt(lensWithSameLabelIndex)
                    boxLenses.add(lensWithSameLabelIndex, lens)
                } else {
                    boxLenses.add(lens)
                }
            }
        }
        return boxes.map { (boxNumber, boxLenses) -> boxLenses.sumOf { it.focusingPower(boxNumber, boxLenses) } }.sum()
    }

    fun hash(s: String): Int {
        return s.fold(0) { value, char -> ((value + char.code) * 17) % 256 }
    }

    data class Lens(val label: String, val focalLength: Int) {

        fun focusingPower(boxNumber: Int, boxLenses: List<Lens>): Int {
            return (1 + boxNumber) * (boxLenses.indexOf(this) + 1) * focalLength
        }
    }

    data class Step(val lensLabel: String, val operation: Char, val lensFocalLength: Int?)
}
