package com.example.tictactoe

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.BlueCustom
import com.example.tictactoe.ui.theme.GrayBackground
import com.example.tictactoe.ui.theme.TicTacToeTheme

@SuppressLint("RememberReturnType")
@Composable
fun GameScreen() {
    val initialGameState = remember { initialGameState() }
    var gameState by remember { mutableStateOf(initialGameState) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBackground)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Player 'O' : ${gameState.scoreO}", fontSize = 16.sp)
            Text(text = "Draw : ${gameState.scoreDraw}", fontSize = 16.sp)
            Text(text = "Player 'X' : ${gameState.scoreX}", fontSize = 16.sp)
        }
        Text(
            text = "Tic Tac Toe",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            color = BlueCustom
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp))
                .background(GrayBackground),
            contentAlignment = Alignment.Center
        ) {
            BoardBase(gameState.grid) { rowIndex, columnIndex ->
                if (gameState.grid[rowIndex][columnIndex].isEmpty() &&
                    checkGameResult(gameState.grid) == GameResult.ONGOING){
                    gameState = gameState.copy(
                        grid = gameState.grid.mapIndexed { i, row ->
                            row.mapIndexed { j, value ->
                                if (i == rowIndex && j == columnIndex) {
                                    if (gameState.isPlayerOTurn) "O" else "X"
                                } else {
                                    value
                                }
                            }
                        },
                        isPlayerOTurn = !gameState.isPlayerOTurn
                    )

                    val result = checkGameResult(gameState.grid)
                    when (result) {
                        GameResult.WIN_O -> {
                            gameState = gameState.copy(scoreO = gameState.scoreO + 1)
                        }
                        GameResult.WIN_X -> {
                            gameState = gameState.copy(scoreX = gameState.scoreX + 1)
                        }
                        GameResult.DRAW -> {
                            gameState = gameState.copy(scoreDraw = gameState.scoreDraw + 1)
                        }
                        else -> Unit
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (gameState.isPlayerOTurn) "Player 'O' turn" else "Player 'X' turn",
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic
            )
            Button(
                onClick = {
                    gameState = initialGameState().copy(scoreO = gameState.scoreO, scoreX = gameState.scoreX, scoreDraw = gameState.scoreDraw)
                },
                shape = RoundedCornerShape(5.dp),
                elevation = ButtonDefaults.elevation(5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = BlueCustom,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Play Again",
                    fontSize = 16.sp
                )
            }
        }
    }
}
// Update the GameState class to include a variable for the current player's turn
data class GameState(
    val grid: List<List<String>> = List(3) { List(3) { "" } },
    val isPlayerOTurn: Boolean = true,
    val scoreO: Int = 0,
    val scoreX: Int = 0,
    val scoreDraw: Int = 0
)


fun initialGameState(): GameState {
    return GameState(List(3) { List(3) { "" } }, isPlayerOTurn = true)
}
@Preview(showBackground = true)
@Composable
fun Prev() {
    TicTacToeTheme {
        GameScreen()
    }
}
