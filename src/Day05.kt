class Day05 : Day<String>("CMZ", "MCD", "CNSZFDVLJ", "QNDWLMGNS") {
    class State(initialCols: List<List<Char>>) {
        private val cols = initialCols.map { it.toMutableList() }
        fun movePart1(move: Move) {
            repeat(move.count) { cols[move.to - 1] += cols[move.from - 1].removeLast() }
        }
        fun movePart2(move: Move) {
            cols[move.to - 1] += cols[move.from - 1].removeLast(move.count)
        }
        fun getTopCrates() = cols.map { it.last() }
        override fun toString() = (0 until cols.maxOf { it.size }).reversed().joinToString("\n") { i ->
            cols.joinToString(" ") { col -> col.getOrNull(i)?.let { "[$it]" } ?: "   " }
        } + "\n" + cols.mapIndexed { index, _ -> " $index " }.joinToString(" ")
    }
    data class Move(val count: Int, val from: Int, val to: Int)

    private fun parseInput(input: List<String>): Pair<State, List<Move>> {
        val rows = input.takeWhile { it.isNotEmpty() }.dropLast(1).map { it.chunked(4).map { it.elementAt(1) } }
        val cols = rows.turnMatrix().map { it.filter { it != ' ' }.reversed() }

        val moves = input.takeLastWhile { it.isNotEmpty() }.map { it.split(' ') }
            .map { Move(it[1].toInt(), it[3].toInt(), it[5].toInt()) }
        return State(cols) to moves
    }

    private fun partCommon(input: List<String>, moveFunction: (State, Move) -> Unit): String {
        val (state, moves) = parseInput(input)
        //println(state)
        moves.forEach {
            moveFunction.invoke(state, it)
            //println(state)
        }
        return state.getTopCrates().joinToString("")
    }

    override fun part1(input: List<String>): String {
        return partCommon(input, State::movePart1)
    }

    override fun part2(input: List<String>): String {
        return partCommon(input, State::movePart2)
    }
}

