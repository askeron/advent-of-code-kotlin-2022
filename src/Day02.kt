fun main() {
    fun String.singleChar(): Char {
        assert(this.length == 1) { "not a single char" }
        return toCharArray()[0]
    }

    fun part1(input: List<String>): Int {
        return input
            .asSequence()
            .map { it.split(" ").map { Shape.byChar(it.singleChar()) } }
            .map { it[0] to it[1] }
            .map { it.second.pointsAgainst(it.first) }
            .sum()
    }

    fun part2(input: List<String>): Int {
        return input
            .asSequence()
            .map { it.split(" ").map { it.singleChar() } }
            .map { Shape.byChar(it[0]) to it[1] }
            .map { it.first to it.first.shapeForPart2(it.second) }
            .map { it.second.pointsAgainst(it.first) }
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val input = readInput("Day02")
    assertEquals(15, part1(testInput))
    println(part1(input))
    assertEquals(12, part2(testInput))
    println(part2(input))

}

enum class Shape(val primary: Char, val secondary: Char, private val points: Int, private val winnerAgainstPrimary: Char) {
    ROCK('A', 'X', 1, 'C'),
    PAPER('B', 'Y', 2, 'A'),
    SCISSORS('C', 'Z', 3, 'B'),
    ;

    private val winsAgainst by lazy { values().first { it.primary == winnerAgainstPrimary } }
    private val drawsAgainst = this
    private val losesAgainst by lazy { values().first { it !in listOf(winsAgainst, drawsAgainst) } }

    fun pointsAgainst(shape: Shape) = points + when (shape) {
        winsAgainst -> 6
        drawsAgainst -> 3
        else -> 0
    }

    fun shapeForPart2(c: Char): Shape = when(c) {
        'X' -> winsAgainst
        'Y' -> drawsAgainst
        'Z' -> losesAgainst
        else -> error("invalid char")
    }

    companion object {
        fun byChar(c: Char): Shape = values().first { c == it.primary || c == it.secondary }
    }
}
