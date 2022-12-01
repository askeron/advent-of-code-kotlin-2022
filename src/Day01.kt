fun main() {
    fun getData(input: List<String>): List<List<Int>> = input.joinToString("\n")
        .split("\n\n")
        .map { elf ->
            elf.lines()
                .map { it.toInt() }
        }

    fun part1(input: List<String>): Int {
        return getData(input).maxOf { it.sum() }
    }

    fun part2(input: List<String>): Int {
        return getData(input).map { it.sum() }
            .sortedDescending()
            .take(3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    val input = readInput("Day01")
    assertEquals(24000, part1(testInput))
    println(part1(input))
    assertEquals(45000, part2(testInput))
    println(part2(input))
}
