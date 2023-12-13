package days

import days.Day12.ConditionRecord
import days.Day12.SpringCondition.DAMAGED
import days.Day12.SpringCondition.OPERATIONAL
import days.Day12.SpringCondition.UNKNOWN
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class Day12Test {

    private val day12 = Day12()

    @Test
    fun testPartOne() {
        assertThat(day12.partOne(), `is`(21))
    }

    @Test
    fun testPartTwo() {
        assertThat(day12.partTwo(), `is`(525152))
    }

    @Test
    fun testParsingRecords() {
        val conditionRecord = ConditionRecord("???.### 1,1,3")
        assertThat(conditionRecord.springs, contains(UNKNOWN, UNKNOWN, UNKNOWN, OPERATIONAL, DAMAGED, DAMAGED, DAMAGED))
        assertThat(conditionRecord.sizes, contains(1, 1, 3))
    }

    @Test
    fun testArrangements() {
        assertThat(ConditionRecord("???.### 1,1,3").possibleArrangements(), contains("#.#.### 1,1,3"))
        assertThat(ConditionRecord(".??..??...?##. 1,1,3").possibleArrangements(), containsInAnyOrder(
            ".#...#....###. 1,1,3",
            ".#....#...###. 1,1,3",
            "..#..#....###. 1,1,3",
            "..#...#...###. 1,1,3",
        ))
        assertThat(ConditionRecord("?#?#?#?#?#?#?#? 1,3,1,6").possibleArrangements(), contains(".#.###.#.###### 1,3,1,6"))
        assertThat(ConditionRecord("????.#...#... 4,1,1").possibleArrangements(), contains("####.#...#... 4,1,1"))
        assertThat(ConditionRecord("????.######..#####. 1,6,5").possibleArrangements(), containsInAnyOrder(
            "#....######..#####. 1,6,5",
            ".#...######..#####. 1,6,5",
            "..#..######..#####. 1,6,5",
            "...#.######..#####. 1,6,5",
        ))
        assertThat(ConditionRecord("?###???????? 3,2,1").possibleArrangements(), hasSize(10))

        System.err.println(16384 / 4)
        System.err.println(225/5)
        System.err.println(9*5)
    }

    @Test
    fun testAllPossibleStrings() {
        val threeInARow = ConditionRecord.allPossibleStrings(3)
        assertThat(threeInARow, hasSize(8))
        assertThat(threeInARow, containsInAnyOrder(
            "...",
            "#..",
            ".#.",
            "..#",
            "##.",
            ".##",
            "#.#",
            "###",
        ))
    }

    @Test
    fun testUnfolding() {
        assertThat(day12.unfold(".# 1"), `is`(".#?.#?.#?.#?.# 1,1,1,1,1"))
        assertThat(day12.unfold("???.### 1,1,3"), `is`("???.###????.###????.###????.###????.### 1,1,3,1,1,3,1,1,3,1,1,3,1,1,3"))
    }

    @Test
    fun testUnfoldedArrangements() {
        assertThat(ConditionRecord(day12.unfold("???.### 1,1,3")).possibleArrangements(), hasSize(1))
        //assertThat(ConditionRecord(day12.unfold(".??..??...?##. 1,1,3")).possibleArrangements(), hasSize(16384))
        //assertThat(ConditionRecord(day12.unfold("?#?#?#?#?#?#?#? 1,3,1,6")).possibleArrangements(), hasSize(1))
        //assertThat(ConditionRecord(day12.unfold("????.#...#... 4,1,1")).possibleArrangements(), hasSize(16))
        //assertThat(ConditionRecord(day12.unfold("????.######..#####. 1,6,5")).possibleArrangements(), hasSize(2500))
        //assertThat(ConditionRecord(day12.unfold("?###???????? 3,2,1")).possibleArrangements(), hasSize(506250))
    }
}
