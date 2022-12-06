abstract class Day<T>(
    private val expectedTestPart1: T,
    private val expectedTestPart2: T,
    private val expectedPart1: T,
    private val expectedPart2: T,
) {
    private val dayString by lazy { javaClass.name }

    private val testInput by lazy { readInput("${dayString}_test") }
    private val input by lazy { readInput(dayString) }

    abstract fun part1(input: List<String>): T
    abstract fun part2(input: List<String>): T

    fun run() {
        val dayString = javaClass.name
        val testInput = readInput("${dayString}_test")
        val input = readInput(dayString)

        println(dayString)
        assertEquals(expectedTestPart1, part1(testInput))
        assertEquals(expectedPart1, part1(input))
        assertEquals(expectedTestPart2, part2(testInput))
        assertEquals(expectedPart2, part2(input))
    }
}