class Day06 : Day<Int>(7, 19) {
    private fun parseInput(input: List<String>): String {
        return input[0]
    }

    private fun String.indexOfFirstDistinctChars(count: Int) = toCharArray()
        .toList()
        .windowed(count)
        .indexOfFirst { it.allDistinct() } + count - 1

    override fun part1(input: List<String>): Int {
        return parseInput(input).indexOfFirstDistinctChars(4) + 1
    }

    override fun part2(input: List<String>): Int {
        return parseInput(input).indexOfFirstDistinctChars(14) + 1
    }
}
