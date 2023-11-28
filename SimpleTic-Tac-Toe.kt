package tictactoe
val validMove = "\\d\\s\\d".toRegex()
val validNums = "[123]".toRegex()

fun printTTT(ttt: MutableList<Char>) {
    println("---------")

    repeat(3) {
        print("| ")

        for (i in 0..2) {
            print("${ttt[it * 3 + i]} ")
        }

        println("|")
    }

    println("---------")
}

fun makeAMove(ttt: MutableList<Char>, player: Char) {

    var nextMove = readln()

    while (true) {
        if (!validMove.matches(nextMove)) {
            println("You should enter numbers!")
            nextMove = readln()
        } else if (!validNums.matches(nextMove[0].toString()) || !validNums.matches(nextMove[2].toString())) {
            println("Coordinates should be from 1 to 3!")
            nextMove = readln()
        } else if (ttt[(nextMove[0].toString().toInt() - 1) * 3 + (nextMove[2].toString().toInt() - 1)] != ' ') {
            println("This cell is occupied! Choose another one!")
            nextMove = readln()
        } else {
            break
        }
    }

    ttt[(nextMove[0].toString().toInt() - 1) * 3 + (nextMove[2].toString().toInt() - 1)] = player

    printTTT(ttt)
}

fun checkForWinner(ttt: MutableList<Char>): Pair<String, Char> {
    val possibleWins = mutableListOf(mutableListOf(0, 1, 2), mutableListOf(3, 4, 5), mutableListOf(6, 7, 8), mutableListOf(0, 3, 6), mutableListOf(1, 4, 7), mutableListOf(2, 5, 8), mutableListOf(0, 4, 8), mutableListOf(2, 4, 6))

    val wins: MutableList<Char>  = mutableListOf()

    for (i in possibleWins) {
        if (ttt[i[0]] == ttt[i[1]] && ttt[i[1]] == ttt[i[2]] && ttt[i[0]] != ' ') {
            wins.add(ttt[i[0]])
        }
    }

    when (wins.size) {
        0 -> {
            for (i in ttt) {
                if (i == ' ') {
                    return Pair("No winner", ' ')
                }
            }
            return Pair("Draw", ' ')
        }
        1 -> return Pair("Winner", wins[0])
        else -> return Pair("Impossible", ' ')
    }
}

fun main() {
    val ttt = MutableList(9) {' '}

    val players = listOf('X', 'O')

    printTTT(ttt)

    while (true) {
        for (player in players) {
            makeAMove(ttt, player)
            val (gg, winner) = checkForWinner(ttt)
            when (gg) {
                "Winner" -> {
                    println("$winner wins!")
                    return
                }
                "No winner" -> continue
                "Draw" -> {
                    println("Draw")
                    return
                }
                "Impossible" -> {
                    println("Impossible")
                    return
                }
            }
        }
    }
}
