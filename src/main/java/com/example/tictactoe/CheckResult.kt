package com.example.tictactoe

enum class GameResult {
    WIN_O,
    WIN_X,
    DRAW,
    ONGOING
}
fun checkGameResult(grid: List<List<String>>): GameResult {
    // Check rows
    for (row in grid) {
        if (row.all { it == "O" }) {
            println("DEBUG: Win in a row for O")
            return GameResult.WIN_O
        }
        if (row.all { it == "X" }) {
            println("DEBUG: Win in a row for X")
            return GameResult.WIN_X
        }
    }

    // Check columns
    for (col in grid.indices) {
        if (grid.all { it[col] == "O" }) {
            println("DEBUG: Win in a column for O")
            return GameResult.WIN_O
        }
        if (grid.all { it[col] == "X" }) {
            println("DEBUG: Win in a column for X")
            return GameResult.WIN_X
        }
    }

    // Check diagonals
    if (grid[0][0] == "O" && grid[1][1] == "O" && grid[2][2] == "O" ||
        grid[0][2] == "O" && grid[1][1] == "O" && grid[2][0] == "O"
    ) {
        println("DEBUG: Win in a diagonal for O")
        return GameResult.WIN_O
    }

    if (grid[0][0] == "X" && grid[1][1] == "X" && grid[2][2] == "X" ||
        grid[0][2] == "X" && grid[1][1] == "X" && grid[2][0] == "X"
    ) {
        println("DEBUG: Win in a diagonal for X")
        return GameResult.WIN_X
    }

    // Check for a draw
    if (grid.flatten().none { it.isEmpty() }) {
        println("DEBUG: Draw")
        return GameResult.DRAW
    }

    println("DEBUG: Ongoing game")
    return GameResult.ONGOING
}
