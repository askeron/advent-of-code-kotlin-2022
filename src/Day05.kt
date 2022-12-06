class Day05 : Day<String>("CMZ", "MCD") {
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
        return input.joinToString("\n")
            .split("\n\n")
            .map { it.lines() }
            .exactPair()
            .let { (x,y) ->
                val colCount = x.last().trim().split(' ').last().toInt()
                val rows = x.dropLast(1).map { line -> (0 until colCount).map { line.elementAt(4 * it + 1) } }
                val cols = rows.turnMatrix().map { it.filter { it != ' ' }.reversed() }
                val moves = y.map { it.split(' ') }
                    .map { it[1].toInt() to it[3].toInt() toTriple it[5].toInt() }
                    .map { (a,b,c) -> Move(a, b, c) }
                State(cols) to moves
            }
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

