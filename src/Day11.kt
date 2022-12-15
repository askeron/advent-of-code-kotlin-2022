class Day11 : Day<Long>(10605, 2713310158L, 58056, 15048718170L) {

    private class Monkey(
        private val items: MutableList<Long>,
        private val operation: (Long) -> Long,
        private val divisibleForTest: Int,
        private val monkeyForTrue: Int,
        private val monkeyForFalse: Int,
    ) {
        var itemsInspectedCount = 0

        fun inspect(monkeys: List<Monkey>, reliefFactor: Int) {
            items.forEach { value ->
                itemsInspectedCount++
                val newValue = (operation.invoke(value) / reliefFactor) % monkeys.map { it.divisibleForTest }.fold(1) { a, b -> a * b }
                if (newValue % divisibleForTest == 0L) {
                    monkeyForTrue
                } else {
                    monkeyForFalse
                }.also { monkeys[it].items += newValue }
            }
            items.clear()
        }
    }

    private fun parseInput(input: List<String>): List<Monkey> {
        return input.split("")
            .map { lines ->
                Monkey(
                    items = lines[1].substringAfter(':').split(',').map { it.trim().toLong() }.toMutableList(),
                    operation = lines[2].split(' ').takeLast(2).toPair().let { (a, b) ->
                        if (a == "*" && b == "old") {
                            { it * it }
                        } else if (a == "*") {
                            { it * b.toInt() }
                        } else if (a == "+") {
                            { it + b.toInt() }
                        } else {
                            error("no operation found")
                        }
                    },
                    divisibleForTest = lines[3].split(' ').last().toInt(),
                    monkeyForTrue = lines[4].split(' ').last().toInt(),
                    monkeyForFalse = lines[5].split(' ').last().toInt(),
                )
            }
    }

    private fun partCommon(input: List<String>, reliefFactor: Int, roundCount: Int): Long {
        val monkeys = parseInput(input)
        repeat(roundCount) { _ -> monkeys.forEach { it.inspect(monkeys, reliefFactor) } }
        return monkeys.map { it.itemsInspectedCount }.sortedDescending().let { it[0].toLong() * it[1] }
    }

    override fun part1(input: List<String>): Long {
        return partCommon(input, 3, 20)
    }

    override fun part2(input: List<String>): Long {
        return partCommon(input, 1, 10_000)
    }
}
