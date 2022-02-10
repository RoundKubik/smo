package domain.disciplines

import domain.model.BufferingResult

// TODO: 23.11.2021
interface BufferingDiscipline<T> {
    fun buffering(request: T, elements: Array<T>) : BufferingResult<T>
}