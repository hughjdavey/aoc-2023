package days

import days.Day8.Node
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class Day8Test {

    private val day8 = Day8()

    @Test
    fun testPartOne() {
        assertThat(day8.partOne(), `is`(6L))
    }

    @Test
    fun testPartTwo() {
        // can't call partTwo directly as it uses a different input to partOne
        val ghostSteps = day8.navigateGhostSteps(day8.getInstructions("LR"), day8.getNetwork(listOf(
            "11A = (11B, XXX)",
            "11B = (XXX, 11Z)",
            "11Z = (11B, XXX)",
            "22A = (22B, XXX)",
            "22B = (22C, 22C)",
            "22C = (22Z, 22Z)",
            "22Z = (22B, 22B)",
            "XXX = (XXX, XXX)",
        )))
        assertThat(ghostSteps, `is`(6L))
    }

    @Test
    fun testGetInstructions() {
        val example1 = day8.getInstructions("RL").take(10).joinToString("")
        assertThat(example1, `is`("RLRLRLRLRL"))

        val example2 = day8.getInstructions("LLR").take(10).joinToString("")
        assertThat(example2, `is`("LLRLLRLLRL"))

        val example3 = day8.getInstructions("LR").take(10).joinToString("")
        assertThat(example3, `is`("LRLRLRLRLR"))
    }

    @Test
    fun testGetNetwork() {
        val example1 = day8.getNetwork(listOf(
            "AAA = (BBB, CCC)",
            "BBB = (DDD, EEE)",
            "CCC = (ZZZ, GGG)",
            "DDD = (DDD, DDD)",
            "EEE = (EEE, EEE)",
            "GGG = (GGG, GGG)",
            "ZZZ = (ZZZ, ZZZ)",
        ))
        assertThat(example1, hasSize(7))
        assertThat(example1, contains(Node("AAA", "BBB", "CCC"), Node("BBB", "DDD", "EEE"), Node("CCC", "ZZZ", "GGG"),
            Node("DDD", "DDD", "DDD"), Node("EEE", "EEE", "EEE"), Node("GGG", "GGG", "GGG"), Node("ZZZ", "ZZZ", "ZZZ")))

        val example2 = day8.getNetwork(listOf(
            "AAA = (BBB, BBB)",
            "BBB = (AAA, ZZZ)",
            "ZZZ = (ZZZ, ZZZ)",
        ))
        assertThat(example2, hasSize(3))
        assertThat(example2, contains(Node("AAA", "BBB", "BBB"), Node("BBB", "AAA", "ZZZ"), Node("ZZZ", "ZZZ", "ZZZ")))

        val example3 = day8.getNetwork(listOf(
            "11A = (11B, XXX)",
            "11B = (XXX, 11Z)",
            "11Z = (11B, XXX)",
            "22A = (22B, XXX)",
            "22B = (22C, 22C)",
            "22C = (22Z, 22Z)",
            "22Z = (22B, 22B)",
        ))
        assertThat(example3, hasSize(7))
        assertThat(example3, contains(Node("11A", "11B", "XXX"), Node("11B", "XXX", "11Z"), Node("11Z", "11B", "XXX"),
            Node("22A", "22B", "XXX"), Node("22B", "22C", "22C"), Node("22C", "22Z", "22Z"), Node("22Z", "22B", "22B")))
    }
}
