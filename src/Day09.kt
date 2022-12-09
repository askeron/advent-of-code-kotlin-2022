class Day09 : Day<Int>(13, 1, 6236, 2449) {
    private fun parseInput(input: List<String>): List<Point> {
        return input.map { it.split(" ").toPair() }
            .map {
                when(it.first) {
                    "L" -> Point(-1,0)
                    "R" -> Point(1,0)
                    "U" -> Point(0,1)
                    "D" -> Point(0,-1)
                    else -> error("invalid direction")
                } to it.second.toInt()
            }
            .flatMap { (direction, count) -> (1..count).map { direction } }
    }

    private class State(val tailCount: Int) {
        val positions = Array(tailCount + 1) { Point(0,0) }
        val lastTailPositions = mutableSetOf(positions.last())

        fun moveHead(direction: Point) {
            positions[0] += direction
            (0 until tailCount).forEach { i ->
                if (positions[i+1] !in positions[i].neighboursWithItself) {
                    positions[i+1] += (positions[i] - positions[i+1]).sign
                }
            }
            lastTailPositions += positions.last()
        }
    }

    fun partCommon(input: List<String>, tailCount: Int): Int {
        return State(tailCount).also { state -> parseInput(input).forEach { state.moveHead(it) } }
            .lastTailPositions.size
    }

    override fun part1(input: List<String>): Int {
        return partCommon(input, 1)
    }

    override fun part2(input: List<String>): Int {
        return partCommon(input, 9)
    }
}
