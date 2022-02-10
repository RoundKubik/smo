package presentation.model

data class AutoModeSource(
    val name: String,
    val requestCount: Int,
    val probReject: Double,
    val systemRequestTime: Double,
    val bufferTime: Double, // dispersion
    val processingTime: Double, // dispersion
    val bufferDispersion: Double,
    val processingDispersion: Double
)