package presentation.source_statistics

import kotlinx.coroutines.flow.MutableStateFlow
import presentation.model.AutoModeSource

class SourceStatisticsViewModel {

    var autoModeStatistics = MutableStateFlow<List<AutoModeSource>>(
        listOf()
    )
}