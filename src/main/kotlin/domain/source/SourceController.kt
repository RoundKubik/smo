package domain.source

import domain.model.Request

interface SourceController<T> {
    fun getNextRequest(): Request
    fun getRequestsForStatistic() : Array<Request>
    fun getNextEventTime() : Double
    fun isEndRequests() : Boolean
}