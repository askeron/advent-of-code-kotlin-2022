class Day12 : Day<Int>(31, 29, 447, 446) {
    private fun partCommon(input: List<String>, lookForBestStartPoint: Boolean): Int {
        val inputMap = input.charMatrixToPointMap()
        val allPoints = inputMap.keys
        val start = inputMap.entries.filter { it.value == 'S' }.singleValue().key
        val end = inputMap.entries.filter { it.value == 'E' }.singleValue().key
        val charMap = inputMap.toMutableMap().also {
            it[start] = 'a'
            it[end] = 'z'
        }.toMap()
        val possibleSteps = allPoints.flatMap { p -> p.neighboursNotDiagonal.filter { it in allPoints }.map { p to it } }
            .filter { (a, b) -> charMap[b]!! <= charMap[a]!! + 1 }
            .toSet()
        val possibleStartPoints = if (lookForBestStartPoint) {
            charMap.entries.filter { it.value == 'a' }.map { it.key }
        } else {
            listOf(start)
        }

        val shortestPath = possibleStartPoints
            .mapNotNull { shortestPathByDijkstra(possibleSteps.map { it toTriple 1 }.toSet(), it, end)?.first }
            .minByOrNull { it.size }!!

        //println(getPathMapString(allPoints, shortestPath, end))
        return shortestPath.size - 1
    }

    override fun part1(input: List<String>): Int {
        return partCommon(input, false)
    }

    override fun part2(input: List<String>): Int {
        return partCommon(input, true)
    }

    fun getPathMapString(allPoints: Set<Point>, path: List<Point>, end: Point): String {
        val directionCharMap = buildMap {
            put(Point.UP, '^')
            put(Point.DOWN, 'v')
            put(Point.LEFT, '<')
            put(Point.RIGHT, '>')
        }
        val pathCharMap = path.windowed(2)
            .map { (a, b) -> a to directionCharMap[b-a]!! }
            .plus(end to 'E')
            .toMap()
        return allPoints.matrixString { x, y -> pathCharMap[Point(x,y)] ?: '.' }
    }
}
