package presentation.device_statistics

import kotlinx.coroutines.flow.MutableStateFlow
import presentation.model.AutoModeDevice

class DeviceStatisticsViewModel {

    val statistics = MutableStateFlow<List<AutoModeDevice>>(
        listOf(
        )
    )
}