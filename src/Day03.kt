class Day03 : Day<Int>(157, 70) {
    fun <T> List<T>.splitInHalf(): Pair<List<T>, List<T>> {
        check(size % 2 == 0) { "not an even size" }
        return take(size / 2) to drop(size / 2)
    }

    fun Char.getPriority(): Int = when (this) {
        in 'a'..'z' -> code - 'a'.code + 1
        in 'A'..'Z' -> code - 'A'.code + 1 + 26
        else -> error("invalid char")
    }

    override fun part1(input: List<String>): Int {
        return input.map { it.toCharArray().toList().splitInHalf() }
            .map { pair -> pair.first.filter { it in pair.second } }
            .map { it.distinct().singleValue() }
            .sumOf { it.getPriority() }
    }

    override fun part2(input: List<String>): Int {
        return input.map { it.toCharArray().toList() }
            .chunked(3)
            .map { group -> group[0].filter { it in group[1] && it in group[2] } }
            .map { it.distinct().singleValue() }
            .sumOf { it.getPriority() }
    }
}

