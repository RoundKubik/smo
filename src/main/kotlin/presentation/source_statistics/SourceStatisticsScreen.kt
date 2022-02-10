package presentation.source_statistics

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.presentation.TableCell
import presentation.auto_mode.AutoModeViewModel
import presentation.model.AutoModeSource

private const val numSourceCelWeight = 0.1f
private const val requestCountCellWeight = 0.1f
private const val probRejectCellWeight = 0.1f
private const val systemTimeCellWeight = 0.1f
private const val bufferTimeCellWeight = 0.1f
private const val processingTimeCellWeight = 0.1f
private const val dispersionBufferTimeCellWeight = 0.1f
private const val dispersionProcessingTime = 0.1f


@Composable
fun SourceStatisticsScreen(viewModel: AutoModeViewModel = AutoModeViewModel()) {

    val sourceStatistics by viewModel.autoModeStatistics.collectAsState()

    Column (modifier = Modifier.wrapContentSize().padding(32.dp)) {
        TableStatisticsHeader()
        sourceStatistics.forEach {
            SourceStatisticCard(it)
        }
    }
}

@Composable
private fun TableStatisticsHeader() {
    Row(modifier = Modifier.height(80.dp)) {
        TableCell("№ source", numSourceCelWeight)
        TableCell("Requests count", requestCountCellWeight)
        TableCell("Prob. reject", probRejectCellWeight)
        TableCell("System time", systemTimeCellWeight)
        TableCell("Buffer time", bufferTimeCellWeight)
        TableCell("Processing time", processingTimeCellWeight)
        TableCell("Dispersion buffer time", dispersionBufferTimeCellWeight)
        TableCell("Dispersion processing time", dispersionProcessingTime)
    }
}

@Composable
private fun SourceStatisticCard(source: AutoModeSource) {
    Row {
        TableCell(source.name, numSourceCelWeight)
        TableCell("${source.requestCount}", requestCountCellWeight)
        TableCell("${source.probReject}", probRejectCellWeight)
        TableCell("${source.systemRequestTime}", systemTimeCellWeight)
        TableCell("${source.bufferTime}", bufferTimeCellWeight)
        TableCell("${source.processingTime}", processingTimeCellWeight)
        TableCell("${source.bufferDispersion}", dispersionBufferTimeCellWeight)
        TableCell("${source.processingDispersion}", dispersionProcessingTime)
    }
}


