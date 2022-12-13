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
    private fun getXValueForCycle(input: List<String>): List<Int> = mutableListOf(1).also {
        input.forEach { line ->
            it += it.last()
            if (line.startsWith("addx")) {
                it += it.last() + line.split(" ")[1].toInt()
            }
        }
    }.toList()

    override fun part1(input: List<String>): String {
        val xValueForCycle = getXValueForCycle(input)
        return (20..xValueForCycle.size step 40).asSequence()
            .map { it to xValueForCycle[it - 1] }
            //.onEach { println(it) }
            .map { (a, b) -> a * b }
            //.onEach { println(it) }
            .sum()
            .toString()
    }

    override fun part2(input: List<String>): String {
        return getXValueForCycle(input).mapIndexed { cycle, x -> (cycle % 40) in (x - 1)..(x + 1) }
            .dropLast(1)
            .joinToString("") { if (it) "#" else "." }
            .chunked(40)
            .joinToString("\n")
    }
}
