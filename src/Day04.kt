class Day04 : Day<Int>(2, 4, 588, 911) {

    private fun parseInput(input: List<String>) = input.map { line -> line
        .split(",")
        .map { it.split('-').map { it.toInt() }.exactPair() }
        .map { (a, b) ->  (a..b).toSet() }
        .exactPair()
    }

    override fun part1(input: List<String>): Int {
        return parseInput(input).count { (a, b) -> a.containsAll(b) || b.containsAll(a) }
    }

    override fun part2(input: List<String>): Int {
        return parseInput(input).count { (a, b) -> a.intersect(b).isNotEmpty() }
    }
}
