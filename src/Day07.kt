class Day07 : Day<Long>(95437L, 24933642L, 1543140L, 1117448L) {

    data class File(val dirPath: String, val filename: String, val size: Long) {
        val path = "$dirPath$filename"
    }

    private fun parseInput(input: List<String>): List<File> {
        val files = mutableListOf<File>()
        var currentPath = "/"
        input.forEach { line ->
            if (line == "$ cd /") {
                currentPath = "/"
            } else if (line == "$ cd ..") {
                currentPath = getParentDirPath(currentPath)
            } else if (line.startsWith("$ cd ")) {
                currentPath += "${line.removePrefix("$ cd ")}/"
            } else if (line.first().isDigit()) {
                files += line.split(" ").toPair().let { File(currentPath, it.second, it.first.toLong()) }
            }
        }
        return files.distinctBy { it.path }
    }

    override fun part1(input: List<String>): Long {
        val files = parseInput(input)
        return files.getDirectorySizes()
            .filter { it <= 100_000 }
            .sumOf { it }
    }

    override fun part2(input: List<String>): Long {
        val files = parseInput(input)
        val totalSpace = 70_000_000
        val freeSpaceNeeded = 30_000_000
        val spaceToFreeUp = freeSpaceNeeded - (totalSpace - files.sumOf { it.size })
        return files.getDirectorySizes()
            .sorted()
            .first { it >= spaceToFreeUp }
    }

    private fun List<File>.getDirectorySizes() = map { it.dirPath }
        .distinct()
        .flatMap { getDirPathIncludingAllParentPaths(it) }
        .distinct()
        .map { dirPath -> this.filter { it.dirPath.startsWith(dirPath) }.sumOf { it.size } }

    private fun getParentDirPath(dirPath: String) = dirPath.removeSuffix("/").replaceAfterLast('/',"")

    private fun getDirPathIncludingAllParentPaths(dirPath: String): List<String> {
        val result = mutableListOf(dirPath)
        var path = dirPath
        repeat(dirPath.count { it == '/' } - 1) {
            path = getParentDirPath(path)
            result += path
        }
        return result.toList()
    }
}
