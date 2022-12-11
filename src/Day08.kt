import kotlin.math.max

typealias PointMap = Map<Point, Int>

class Day08 : Day<Int>(21, 8, 1779, 172224) {
    private fun parseInput(input: List<String>): PointMap {
        return input.IntMatrixToPointMap()
    }

    override fun part1(input: List<String>): Int {
        return parseInput(input).let { pointMap -> pointMap.keys.count { pointMap.isVisibleFromOutside(it) } }
    }

    override fun part2(input: List<String>): Int {
        return parseInput(input).let { pointMap -> pointMap.keys.maxOf { pointMap.getScenicScore(it) } }
    }

    private fun Set<Point>.viewLinesFromEdge(point: Point): List<List<Point>> = listOf(
        filter { it.x == point.x }.partition { it.y < point.y },
        filter { it.y == point.y }.partition { it.x < point.x },
    ).flatMap { listOf(it.first, it.second.filter { it != point }.reversed()) }

    private fun PointMap.isVisibleFromOutside(point: Point): Boolean = keys.viewLinesFromEdge(point)
        .also { if (it.any { it.isEmpty() }) return true }
        .any { it.all { get(it)!! < get(point)!! } }

    private fun PointMap.getScenicScore(point: Point): Int = keys.viewLinesFromEdge(point)
        .map { it.reversed().map { get(it)!! }.takeWhilePlusOne { it < get(point)!! }.count() }
        .reduce { a, b -> a * b}
}
