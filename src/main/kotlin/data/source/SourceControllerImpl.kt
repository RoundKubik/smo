package data.source

import domain.model.Request
import domain.source.Source
import domain.source.SourceController
import presentation.Analytics
import presentation.model.BufferStatistics
import presentation.model.EventCalendarStatistics

// TODO: 24.11.2021 add documentation
class SourceControllerImpl(
    private val sourceCount: Int,
    private val countRequest: Int,
    private val min: Double,
    private val max: Double
) : SourceController<Source> {

    private val devices: Array<Source> = Array(sourceCount) {
        Source(
            it + 1,
            min,
            max
        )
    }

    private var _nextEventTime: Double = 0.0


    private val requests: Array<ArrayList<Request>> = Array<ArrayList<Request>>(sourceCount) {
        ArrayList()
    }.apply {
        for (i in 0..countRequest / sourceCount) {
            devices.forEachIndexed { index, source ->
                this[index].add(source.generateRequest())
            }
        }
        for (i in 0..countRequest % sourceCount) {
            this[i].add(devices[i].generateRequest())
        }
    }


    override fun getNextRequest(): Request {
        var lastRequest = Request()
        val data = mutableListOf<Request>()
        requests.forEach {
            data.addAll(it)
        }
        if (data.isNotEmpty()) {
            lastRequest = data[0]
            data.forEach {
                if ((lastRequest.generationTime > it.generationTime)) {
                    lastRequest = it
                }
            }
        }
        requests.forEach {
            it.remove(lastRequest)
        }
        if (lastRequest.sourceNum != -1) {
            Analytics.deviceRequestCount[lastRequest.sourceNum - 1]++
        }
        return lastRequest
    }


    override fun getRequestsForStatistic(): Array<Request> {
        return Array(sourceCount) { source ->
            if (requests[source].isEmpty()) Request() else requests[source][0]
        }
    }

    override fun getNextEventTime() = _nextEventTime

    override fun isEndRequests(): Boolean {
        requests.forEach {
            if (it.isNotEmpty()) {
                return false
            }
        }
        return true
    }
}

