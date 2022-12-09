class Day09 : Day<Int>(13, 13, 1, 2) {
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

    private class StatePart1 {
        var head = Point(0,0)
        var tail = head
        val tailPositions = mutableSetOf(tail)

        fun moveHead(direction: Point) {
            head += direction
            if (tail !in head.neighboursWithItself) {
                tail += (head - tail).sign
            }
            tailPositions += tail
        }
    }

    private class StatePart2 {
        var parts = List(10) { SingleReference(Point(0,0)) }
        val head = parts.first()
        val lastTail = parts.last()
        val pairs = parts.windowed(2).map { it.toPair() }
        val tailPositions = mutableSetOf(lastTail.value)

        fun moveHead(direction: Point) {
            head.value = head.value + direction
            pairs.forEach { (a,b) ->
                if (b.value !in a.value.neighboursWithItself) {
                    b.value = b.value + (a.value - b.value).sign
                }
            }
            tailPositions += lastTail.value
        }
    }

    override fun part1(input: List<String>): Int {
        return StatePart1().also { state -> parseInput(input).forEach { state.moveHead(it) } }
            .tailPositions.size
    }

    override fun part2(input: List<String>): Int {
        return part1(input)
    }
}
