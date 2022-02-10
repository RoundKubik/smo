package domain.device

import domain.model.DeviceStatus
import domain.model.Request
import kotlin.math.ln
import kotlin.random.Random

// TODO: 22.11.2021 maybe can removed
typealias FinishWorkListener =  ((Request) -> Unit)

// TODO: 19.11.2021 add documentation
// TODO: 19.11.2021 add order of distribution
class Device(
    val deviceNum: Int = 0,
    private val lambda: Double = 0.0
) {
    
    private var _status: DeviceStatus = DeviceStatus.Free
    val status get() = _status

    private var _lastProcessedRequest = Request()
    val lastProcessedRequest get() = _lastProcessedRequest

    fun startWork(request: Request) {
        _status = DeviceStatus.InProgress

        val processTime = generateProcessingTimeByExpDistribution()

        if (_lastProcessedRequest.processedTime >  request.generationTime) {
            request.processedTime = _lastProcessedRequest.processedTime + processTime
            request.receiveTime = _lastProcessedRequest.processedTime
        } else {
            request.processedTime = request.generationTime + processTime
            request.receiveTime  = request.generationTime
        }
        _lastProcessedRequest = request
        // TODO: 22.11.2021 add logs
    }

    fun finishWork() {
        _status = DeviceStatus.Free
        // TODO: 22.11.2021  add logs
    }

    fun isFree() = status == DeviceStatus.Free

    private fun generateProcessingTimeByExpDistribution() = ln(1 - Random.nextDouble()) / (-lambda)

}