package presentation.step_mode

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.presentation.TableCell
import presentation.model.BufferStatistics

//region
private const val numBufferCellWeight = 0.1f
private const val timeBufferCellWeight = 0.1f
private const val numSourceCellWeight = 0.1f
private const val numRequestWeight = 0.1f
//endregion

@Composable
fun BufferStateScreen(viewModel: StepModeViewModel = StepModeViewModel()) {

    val bufferStatistics by viewModel.bufferStatistics.collectAsState()

    Column(modifier = Modifier.wrapContentSize().padding(32.dp)) {
        Box(
            contentAlignment = Alignment.Center

        ) {
            Text("Buffer statistics")
        }
        BufferStateHeader()
        bufferStatistics.forEach {
            BufferStateCard(it)
        }
    }
}

@Composable
fun BufferStateHeader() {
    Row(modifier = Modifier.height(80.dp)) {
        TableCell("Position", numBufferCellWeight)
        TableCell("Time", timeBufferCellWeight)
        TableCell("Source num", numSourceCellWeight)
        TableCell("Request num", numRequestWeight)
    }
}

@Composable
fun BufferStateCard(bufferStatistics: BufferStatistics) {
    Row {
        TableCell(bufferStatistics.name, numBufferCellWeight)
        TableCell("${bufferStatistics.time}", timeBufferCellWeight)
        TableCell("${bufferStatistics.sourceNum}", numSourceCellWeight)
        TableCell("${bufferStatistics.requestNum}", numRequestWeight)
    }
}