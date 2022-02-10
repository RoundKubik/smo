package domain.source

import domain.model.Request
import presentation.Analytics
import kotlin.random.Random

// TODO: 20.11.2021 add documentation
class Source(
    val sourceNumber: Int,
    private val min: Double,
    private val max: Double
) {

    private var _lastRequest: Request = Request()
    val lastRequest get() = _lastRequest

    // TODO: 20.11.2021 documentation
    fun generateRequest(): Request {
        _lastRequest = Request(
            generationTime = _lastRequest.generationTime + getNextGenerationTimeByUniformDistribution(),
            sourceNum = sourceNumber,
            requestNum = _lastRequest.requestNum + 1
        )
        return _lastRequest
    }

    private fun getNextGenerationTimeByUniformDistribution() = min + (max - min) * Random.nextDouble()
}