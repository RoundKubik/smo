package domain.model

// TODO: 19.11.2021 add documentation
data class Request(
    val generationTime: Double = 0.0,
    val sourceNum: Int = -1,
    val requestNum: Int = -1,
    val requestStatus: RequestStatus = RequestStatus.Generated
) {
    var receiveTime: Double = 0.0
    var processedTime: Double = 0.0
}
