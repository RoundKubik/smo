package presentation.step_mode

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.presentation.TableCell
import presentation.model.EventCalendarStatistics

//region
private const val eventCalendarEventCellWeight = 0.1f
private const val eventCalendarTimeCellWeight = 0.1f
private const val eventCalendarPropertyCellWeight = 0.1f
private const val eventCalendarRequestCountCellWeight = 0.1f
private const val eventCalendarRejectCountCellWeight = 0.1f
private const val eventCalendarRequestNumCellWeight = 0.1f
//endregion

@Composable
fun EventCalendarScreen(viewModel: StepModeViewModel = StepModeViewModel()) {

    val eventSourceStatisticsState by viewModel.eventSourceStatistics.collectAsState()
    val eventDeviceStatisticsState by viewModel.eventDeviceStatistics.collectAsState()
    val eventEndEmulationStatisticsState by viewModel.eventEndEmulationStatistics.collectAsState()

    Column(modifier = Modifier.wrapContentSize().padding(32.dp)) {
        EventsCalendarHeader()
        eventSourceStatisticsState.forEach {
            EventCard(it)
        }
        eventDeviceStatisticsState.forEach {
            EventCard(it)
        }
        eventEndEmulationStatisticsState.forEach {
            EventCard(it)
        }
    }
}

@Composable
fun EventsCalendarHeader() {
    Row(modifier = Modifier.height(80.dp)) {
        TableCell("Event", eventCalendarEventCellWeight)
        TableCell("Time", eventCalendarTimeCellWeight)
        TableCell("Property", eventCalendarPropertyCellWeight)
        TableCell("RequestNum", eventCalendarRequestNumCellWeight)
    }
}

@Composable
fun EventCard(event: EventCalendarStatistics) {
    Row {
        TableCell(event.event, eventCalendarEventCellWeight)
        TableCell(event.time, eventCalendarTimeCellWeight)
        TableCell(event.property, eventCalendarPropertyCellWeight)
        TableCell(event.requestNum, eventCalendarRequestNumCellWeight)
    }
}