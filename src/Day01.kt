class Day01 : Day<Int>(24000, 45000) {
    fun getData(input: List<String>): List<List<Int>> = input.joinToString("\n")
        .split("\n\n")
        .map { elf ->
            elf.lines()
                .map { it.toInt() }
        }

    override fun part1(input: List<String>): Int {
        return getData(input).maxOf { it.sum() }
    }

    override fun part2(input: List<String>): Int {
        return getData(input).map { it.sum() }
            .sortedDescending()
            .take(3)
            .sum()
    }
}
