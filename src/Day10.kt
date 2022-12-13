class Day10 : Day<String>("13140", """
##..##..##..##..##..##..##..##..##..##..
###...###...###...###...###...###...###.
####....####....####....####....####....
#####.....#####.....#####.....#####.....
######......######......######......####
#######.......#######.......#######.....""".removePrefix("\n"), "12840", """
####.#..#...##.####.###....##.####.####.
...#.#.#.....#.#....#..#....#.#.......#.
..#..##......#.###..###.....#.###....#..
.#...#.#.....#.#....#..#....#.#.....#...
#....#.#..#..#.#....#..#.#..#.#....#....
####.#..#..##..#....###...##..#....####.""".removePrefix("\n")
) {
    private fun getXValueForCycles(input: List<String>): List<Int> = input
        .flatMap { if (it == "noop") listOf(0) else listOf(0, it.split(" ")[1].toInt()) }
        .scan(1, Int::plus)

    override fun part1(input: List<String>): String {
        val xValueForCycles = getXValueForCycles(input)
        return (20..xValueForCycles.size step 40).asSequence()
            .map { it to xValueForCycles[it - 1] }
            //.onEach { println(it) }
            .map { (a, b) -> a * b }
            //.onEach { println(it) }
            .sum()
            .toString()
    }

    override fun part2(input: List<String>): String {
        return getXValueForCycles(input).mapIndexed { cycle, x -> (cycle % 40) in (x - 1)..(x + 1) }
            .dropLast(1)
            .joinToString("") { if (it) "#" else "." }
            .chunked(40)
            .joinToString("\n")
    }
}
