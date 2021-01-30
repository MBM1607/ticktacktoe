package tictactoe

import java.lang.NumberFormatException

fun playerWins(inputTable: List<CharArray>, player: Char): Boolean {
    if ((inputTable[0][0] == player && inputTable[0][1] == player && inputTable[0][2] == player) ||
        (inputTable[1][0] == player && inputTable[1][1] == player && inputTable[1][2] == player) ||
        (inputTable[2][0] == player && inputTable[2][1] == player && inputTable[2][2] == player) ||
        (inputTable[0][0] == player && inputTable[1][0] == player && inputTable[2][0] == player) ||
        (inputTable[0][1] == player && inputTable[1][1] == player && inputTable[2][1] == player) ||
        (inputTable[0][2] == player && inputTable[1][2] == player && inputTable[2][2] == player) ||
        (inputTable[0][0] == player && inputTable[1][1] == player && inputTable[2][2] == player) ||
        (inputTable[0][2] == player && inputTable[1][1] == player && inputTable[2][0] == player)) {
        return true
    }
    return false
}

fun printTable(table: List<CharArray>) {
    println("-".repeat(10))
    for (row in table) {
        println("| ${row[0]} ${row[1]} ${row[2]} |")
    }
    println("-".repeat(10))
}

fun main() {
    val inputTable = listOf(CharArray(3) { '_' }, CharArray(3) { '_' }, CharArray(3) { '_' })
    printTable(inputTable)
    var inputNum = 0
    var endMessage = ""
    while (inputTable.any { it.contains('_') }) {
        while (true) {
            print("Enter the coordinates: ")

            try {
                val (x, y) = readLine()!!.split(" ", limit = 2).map { it.toInt() }

                if (x !in 1..3 || y !in 1..3) {
                    println("Coordinates should be from 1 to 3!")
                }
                else if (inputTable[x - 1][y - 1] != '_') {
                    println("This cell is occupied! Choose another one!")
                }
                else {
                    inputTable[x - 1][y - 1] = if (inputNum % 2 == 0) 'X' else 'O'
                    inputNum++
                    break
                }
            } catch (e: NumberFormatException) {
                println("You should enter numbers!")
            } catch (e: IndexOutOfBoundsException) {
                println("Invalid Input!")
            }
        }
        printTable(inputTable)
        if (playerWins(inputTable, 'X')) {
            endMessage = "X wins"
            break
        }
        else if (playerWins(inputTable, 'O')) {
            endMessage = "O wins"
            break
        }
    }
    if (endMessage.isEmpty()) endMessage = "Draw"

    println(endMessage)
}

