class Day04 : Day<Int>(2, 4) {

    private fun parseInput(input: List<String>) = input.map { line -> line
        .split(",")
        .map { it.split('-').map { it.toInt() }.exactPair() }
        .map { (a, b) ->  (a..b).toList() }
        .exactPair()
    }

    override fun part1(input: List<String>): Int {
        return parseInput(input)
            .count { (a, b) -> a.all { it in b } || b.all { it in a } }
    }

    override fun part2(input: List<String>): Int {
        return parseInput(input)
            .count { (a, b) -> a.any { it in b } || b.any { it in a } }
    }
}
