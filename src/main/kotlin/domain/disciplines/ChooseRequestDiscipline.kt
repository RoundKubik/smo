package domain.disciplines

import domain.model.ChooseRequestResult
import domain.model.Request

interface ChooseRequestDiscipline<T> {
    fun choose(requests: Array<Request>) : ChooseRequestResult<T>
}