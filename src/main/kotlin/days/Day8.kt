package days

import kotlin.math.max

class Day8 : Day(8) {

    private val instructions = getInstructions(inputString.takeWhile { it == 'L' || it == 'R' })

    private val network = getNetwork(inputList.drop(2))

    override fun partOne(): Any {
        return navigateSteps()
    }

    override fun partTwo(): Any {
        return navigateGhostSteps()
    }

    private fun navigateSteps(): Long {
        val startNode = network.find { it.name == "AAA" }!!
        return countSteps(startNode = startNode) { it == "ZZZ" }
    }

    // public and takes instructions and network params for testing
    fun navigateGhostSteps(instructions: Sequence<Char> = this.instructions, network: List<Node> = this.network): Long {
        val startNodes = network.filter { it.name.endsWith('A') }
        val cycles = startNodes.map { countSteps(instructions, network, it) { name -> name.endsWith('Z') } }
        return lowestCommonMultiple(cycles)
    }

    // implementation inspired by https://www.baeldung.com/kotlin/lcm
    private fun lowestCommonMultiple(numbers: List<Long>): Long {
        return numbers.reduce { result, number -> lowestCommonMultiple(result, number) }
    }

    // implementation inspired by https://www.baeldung.com/kotlin/lcm
    private fun lowestCommonMultiple(a: Long, b: Long): Long {
        val maxNumber = max(a, b)
        val maxLcm = a * b
        var lcm = maxNumber
        while (lcm <= maxLcm) {
            if (lcm % a == 0L && lcm % b == 0L) {
                return lcm
            }
            lcm += maxNumber
        }
        return maxLcm
    }

    private fun countSteps(instructions: Sequence<Char> = this.instructions, network: List<Node> = this.network, startNode: Node, isTargetName: (String) -> Boolean): Long {
        return instructions.scan(startNode) { node, instruction ->
            val next = if (instruction == 'L') node.left else node.right
            network.find { it.name == next }!!
        }.takeWhile { !isTargetName(it.name) }.toList().size.toLong()
    }

    fun getInstructions(instructionString: String): Sequence<Char> {
        return generateSequence(0) { (it + 1) % instructionString.length }.map { instructionString[it] }
    }

    fun getNetwork(nodeList: List<String>): List<Node> {
        return nodeList.map { node ->
            val (name, left, right) = Regex("([A-Z0-9]{3})").findAll(node).toList().map { it.value }
            Node(name, left, right)
        }
    }

    data class Node(val name: String, val left: String, val right: String)
}
