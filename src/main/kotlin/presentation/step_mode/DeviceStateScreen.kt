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
import presentation.model.DeviceStatistics

//region
private const val deviceNameCellWeight = 0.1f
private const val deviceStatusCellWeight = 0.1f
private const val deviceBeginTimeCellWeight = 0.1f
private const val requestNumCellWeight = 0.1f
private const val sourceNumCellWeight = 0.1f
//endregion


@Composable
fun DevicesStateScreen(viewModel: StepModeViewModel = StepModeViewModel()) {


    Column(modifier = Modifier.wrapContentSize().padding(32.dp)) {
        Box(
            contentAlignment = Alignment.Center

        ) {
            Text("Device statistics")
        }
    }

}

@Composable
fun DeviceStateHeader() {
    Row(modifier = Modifier.height(80.dp)) {
        TableCell("Device num", deviceNameCellWeight)
        TableCell("Status", deviceStatusCellWeight)
        TableCell("End time", deviceBeginTimeCellWeight)
        TableCell("Request num", requestNumCellWeight)
        TableCell("Source num", sourceNumCellWeight)
    }
}

@Composable
fun DeviceStatisticsCard(statistics: DeviceStatistics) {
    Row {
        TableCell(statistics.name, deviceNameCellWeight)
        TableCell(statistics.status, deviceStatusCellWeight)
        TableCell("${statistics.endTime}", deviceBeginTimeCellWeight)
        TableCell("${statistics.requestNum}", requestNumCellWeight)
        TableCell("${statistics.sourceNum}", sourceNumCellWeight)
    }
}
