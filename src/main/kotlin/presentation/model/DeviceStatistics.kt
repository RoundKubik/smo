package presentation.model


data class DeviceStatistics(
    val name: String,
    val status: String,
    val endTime: Double,
    val requestNum: Int,
    val sourceNum: Int
)