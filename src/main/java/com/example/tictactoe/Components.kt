package com.example.tictactoe

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoe.ui.theme.Aqua
import com.example.tictactoe.ui.theme.GreenishYellow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BoardBase(grid: List<List<String>>, onCellClick: (Int, Int) -> Unit) {
    val cellSize = 300.dp / 3
    val cellSizePx = with(LocalDensity.current) { cellSize.toPx() }

    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
            .pointerInteropFilter { event ->
                // Calculate the clicked row and column based on the actual click position
                val clickedRow = (event.y / cellSizePx).toInt()
                val clickedColumn = (event.x / cellSizePx).toInt()

                // Invoke the onCellClick callback
                onCellClick(clickedRow, clickedColumn)

                // Consume the event to prevent further processing
                true
            }
    ) {
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width / 3, y = 0f),
            end = Offset(x = size.width / 3, y = size.height)
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 2 / 3, y = 0f),
            end = Offset(x = size.width * 2 / 3, y = size.height)
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height / 3),
            end = Offset(x = size.width, y = size.height / 3)
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height * 2 / 3),
            end = Offset(x = size.width, y = size.height * 2 / 3)
        )

        // Draw symbols in the grid
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                // Draw only in the respective cell
                if (grid[i][j] == "O") {
                    drawCircle(i,j,cellSizePx)
                } else if (grid[i][j] == "X") {
                    drawCross(i,j,cellSizePx)
                }
            }
        }
    }
}

fun DrawScope.drawCircle(row: Int, column: Int, cellSize: Float) {
    val padding = cellSize / 4
    val radius = cellSize / 2 - padding
    val centerX = cellSize / 2 + column * cellSize
    val centerY = cellSize / 2 + row * cellSize

    drawCircle(
        color = Aqua,
        center = Offset(centerX, centerY),
        radius = radius,
        style = Stroke(width = 20f)
    )
}

fun DrawScope.drawCross(row: Int, column: Int, cellSize: Float) {
    val padding = cellSize / 4
    val startX = column * cellSize + padding
    val startY = row * cellSize + padding
    val endX = (column + 1) * cellSize - padding
    val endY = (row + 1) * cellSize - padding

    drawLine(
        color = GreenishYellow,
        strokeWidth = 20f,
        cap = StrokeCap.Round,
        start = Offset(startX, startY),
        end = Offset(endX, endY)
    )
    drawLine(
        color = GreenishYellow,
        strokeWidth = 20f,
        cap = StrokeCap.Round,
        start = Offset(startX, endY),
        end = Offset(endX, startY)
    )
}

@Composable
fun WinVerticalLine1() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width*1/6, y = 0f),
            end = Offset(x = size.width*1/6, y = size.height)
        )
    }
}

@Composable
fun WinVerticalLine2() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width*3/6, y = 0f),
            end = Offset(x = size.width*3/6, y = size.height)
        )
    }
}

@Composable
fun WinVerticalLine3() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width*5/6, y = 0f),
            end = Offset(x = size.width*5/6, y = size.height)
        )
    }
}

@Composable
fun WinHorizontalLine1() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height*1/6),
            end = Offset(x = size.width, y = size.height*1/6)
        )
    }
}

@Composable
fun WinHorizontalLine2() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height*3/6),
            end = Offset(x = size.width, y = size.height*3/6)
        )
    }
}

@Composable
fun WinHorizontalLine3() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height*5/6),
            end = Offset(x = size.width, y = size.height*5/6)
        )
    }
}

@Composable
fun WinDiagonalLine1() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height)
        )
    }
}

@Composable
fun WinDiagonalLine2() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height),
            end = Offset(x = size.width, y = 0f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Prevs() {
    WinVerticalLine1()
    WinVerticalLine2()
    WinVerticalLine3()
    WinHorizontalLine1()
    WinHorizontalLine2()
    WinHorizontalLine3()
    WinDiagonalLine1()
    WinDiagonalLine2()
}