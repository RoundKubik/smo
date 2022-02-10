package presentation.model

data class BufferStatistics(
    val name: String,
    val time: Double,
    val sourceNum: Int,
    val requestNum: Int
)