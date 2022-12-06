class Day03 : Day<Int>(157, 70, 7428, 2650) {
    private fun <T> List<T>.splitInHalf(): Pair<List<T>, List<T>> {
        check(size % 2 == 0) { "not an even size" }
        return take(size / 2) to drop(size / 2)
    }

    private val priorities: Map<Char, Int> = (('a'..'z')+('A'..'Z')).mapIndexed { i, c -> c to i+1 }.toMap()

    override fun part1(input: List<String>): Int {
        return input.map { it.toCharArray().toList().splitInHalf() }
            .map { (a, b) -> a.intersect(b) }
            .map { it.distinct().singleValue() }
            .sumOf { priorities[it]!! }
    }

    override fun part2(input: List<String>): Int {
        return input.map { it.toCharArray().toList() }
            .chunked(3)
            .map { (a,b,c) -> a.intersect(b).intersect(c) }
            .map { it.distinct().singleValue() }
            .sumOf { priorities[it]!! }
    }
}

