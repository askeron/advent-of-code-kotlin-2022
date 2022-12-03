fun main() {
    fun <T> List<T>.splitInHalf(): Pair<List<T>, List<T>> {
        check(size % 2 == 0) { "not an even size" }
        return take(size / 2) to drop(size / 2)
    }

    fun Char.getPriority(): Int = code - (if (code > 96) 96 else (64-26))

    fun part1(input: List<String>): Int {
        return input.map { it.toCharArray().toList().splitInHalf() }
            .map { pair -> pair.first.filter { it in pair.second } }
            .map { it.distinct().singleValue() }
            .sumOf { it.getPriority() }
    }

    fun part2(input: List<String>): Int {
        return input.map { it.toCharArray().toList() }
            .chunked(3)
            .map { group -> group[0].filter { it in group[1] && it in group[2] } }
            .map { it.distinct().singleValue() }
            .sumOf { it.getPriority() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val input = readInput("Day03")
    assertEquals(157, part1(testInput))
    println(part1(input))
    assertEquals(70, part2(testInput))
    println(part2(input))

}

