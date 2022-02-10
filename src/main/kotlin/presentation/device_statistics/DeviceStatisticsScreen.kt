package presentation.device_statistics

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.presentation.TableCell
import presentation.auto_mode.AutoModeViewModel
import presentation.model.AutoModeDevice

private const val numDeviceCellWeight = 0.1f
private const val utilizationRateWeight = 0.1f

@Composable
fun DeviceStateScreen(viewModel: AutoModeViewModel = AutoModeViewModel()) {

    val statistics by viewModel.deviceStatistics.collectAsState()

    Column(modifier = Modifier.wrapContentSize().padding(32.dp)) {
        DeviceStatisticsTableHeader()
        statistics.forEach {
            DeviceStatisticsCard(it)
        }
    }
}

@Composable
private fun DeviceStatisticsTableHeader() {
    Row(modifier = Modifier.height(80.dp)) {
        TableCell("№ device", numDeviceCellWeight)
        TableCell("Utilization rate", utilizationRateWeight)
    }
}

@Composable
private fun DeviceStatisticsCard(card: AutoModeDevice) {
    Row {
        TableCell(card.name, numDeviceCellWeight)
        TableCell("${card.usage}", utilizationRateWeight)
    }
}