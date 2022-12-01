import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')


fun <T> assertEquals(expected: T, actual: T) {
    check(expected == actual) { "expected $expected but found $actual" }
}

// helpers from 2021 START

fun <T> List<T>.allDistinct() = this.size == this.distinct().size

fun <T : Comparable<T>> List<T>.isSorted() = this == this.sorted()

fun <T> List<T>.singleValue(): T {
    assert(size == 1)
    return get(0)
}

infix fun <A, B, C> Pair<A, B>.toTriple(that: C): Triple<A, B, C> = Triple(first, second, that)

fun <T> T.transform(times: Int, transform: (T) -> T): T {
    var result = this
    repeat(times) {
        result = transform.invoke(result)
    }
    return result
}

fun <T> List<T>.transformUntilNoChange(transform: (List<T>) -> List<T>) : List<T> =
    transform.invoke(this).let {
        if (it == this) {
            it
        } else {
            it.transformUntilNoChange(transform)
        }
    }

fun List<String>.IntMatrixToPointMap() = flatMapIndexed { x, s ->
    s.toCharArray().mapIndexed { y, c -> Point(x,y) to c.digitToInt() }
}.toMap()

fun Int.gaussSum() = (this * (this + 1)) / 2

data class Point(val x: Int, val y: Int) {
    operator fun unaryMinus() = Point(-x, -y)
    operator fun plus(b: Point) = Point(x + b.x, y + b.y)
    operator fun minus(b: Point) = this + (-b)
    val values = listOf(x, y)

    val neighboursNotDiagonal by lazy { listOf(
        Point(-1,0),
        Point(1,0),
        Point(0,-1),
        Point(0,1),
    ).map { this + it } }

    val neighboursWithItself by lazy { listOf(
        Point(-1,-1),
        Point(-1,0),
        Point(-1,1),
        Point(0,-1),
        Point(0,0),
        Point(0,1),
        Point(1,-1),
        Point(1,0),
        Point(1,1),
    ).map { this + it } }

    val neighbours by lazy { neighboursWithItself.filterNot { it == this } }
}

// helpers from 2021 START
