package presentation.model


data class Settings(
    val countSources: Int,
    val countDevices: Int,
    val bufferSize: Int,
    val requestCount: Int,
    val a: Double,
    val b: Double,
    val lambda: Double
)
