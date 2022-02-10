package data.disciplines

import domain.disciplines.BufferingDiscipline
import domain.model.BufferingResult
import domain.model.Request

// TODO: 23.11.2021
class BufferingDisciplineImpl : BufferingDiscipline<Request> {

    override fun buffering(request: Request, elements: Array<Request>): BufferingResult<Request> {
        for (i in elements.indices) {
            if (elements[i] == Request()) {
                elements[i] = request
                return BufferingResult.Success(elements)
            }
        }
        return BufferingResult.OverFlow(request)
    }
}


